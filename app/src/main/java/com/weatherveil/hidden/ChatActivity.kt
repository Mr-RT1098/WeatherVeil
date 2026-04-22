package com.weatherveil.hidden

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.weatherveil.R
import com.weatherveil.hidden.adapter.MessageAdapter
import com.weatherveil.hidden.database.AppDatabase
import com.weatherveil.hidden.database.Message
import com.weatherveil.utils.AuthManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileOutputStream
import java.util.*

class ChatActivity : AppCompatActivity() {

    private lateinit var rvMessages: RecyclerView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var btnAttach: ImageButton
    private lateinit var btnLogout: ImageButton
    private lateinit var tvChatTitle: TextView
    private lateinit var tvChatSubtitle: TextView

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var database: AppDatabase
    private lateinit var currentUser: String

    private val firebaseDb = FirebaseDatabase.getInstance().getReference("chats")
    private val firebaseStorage = FirebaseStorage.getInstance().getReference("chat_media")

    // Panic: back × 3 in 2 seconds
    private var backPressCount = 0
    private var lastBackPress = 0L

    companion object {
        private const val PICK_IMAGE_REQUEST = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Block screenshots
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        setContentView(R.layout.activity_chat)

        // Verify session
        currentUser = AuthManager.getLoggedInUser(this) ?: run {
            finish()
            return
        }

        database = AppDatabase.getInstance(this)
        initViews()
        setupMessageList()
        observeLocalMessages()
        listenForRemoteMessages()

        // Mark messages as read
        lifecycleScope.launch {
            database.messageDao().markAllRead(currentUser)
        }
    }

    private fun initViews() {
        rvMessages = findViewById(R.id.rvMessages)
        etMessage = findViewById(R.id.etMessageInput)
        btnSend = findViewById(R.id.btnSendMessage)
        btnAttach = findViewById(R.id.btnAttach)
        btnLogout = findViewById(R.id.btnLogout)
        tvChatTitle = findViewById(R.id.tvChatTitle)
        tvChatSubtitle = findViewById(R.id.tvChatSubtitle)

        tvChatTitle.text = "Private Chat"
        tvChatSubtitle.text = "Logged in as: ${AuthManager.getDisplayName(currentUser)}"

        btnSend.setOnClickListener { sendTextMessage() }
        btnAttach.setOnClickListener { pickImage() }
        btnLogout.setOnClickListener { confirmLogout() }
    }

    private fun setupMessageList() {
        messageAdapter = MessageAdapter(currentUser) { message ->
            showMessageOptions(message)
        }

        rvMessages.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(this@ChatActivity).apply {
                stackFromEnd = true
            }
        }
    }

    private fun observeLocalMessages() {
        database.messageDao().getAllMessages().observe(this) { messages ->
            messageAdapter.updateMessages(messages)
            if (messages.isNotEmpty()) {
                rvMessages.scrollToPosition(messages.size - 1)
            }
        }
    }

    private fun listenForRemoteMessages() {
        firebaseDb.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val remoteMsg = snapshot.getValue(RemoteMessage::class.java) ?: return
                if (remoteMsg.senderId == currentUser) return // Ignore own messages as they're added locally

                lifecycleScope.launch {
                    val existing = database.messageDao().getAllMessagesOnce()
                    if (existing.none { it.firebaseId == snapshot.key }) {
                        val message = Message(
                            senderId = remoteMsg.senderId,
                            text = remoteMsg.text,
                            messageType = remoteMsg.messageType,
                            remoteUrl = remoteUrlFix(remoteMsg.remoteUrl),
                            timestamp = remoteMsg.timestamp,
                            firebaseId = snapshot.key
                        )
                        database.messageDao().insert(message)
                    }
                }
            }
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun remoteUrlFix(url: String?): String? {
        // In a real app, you might download the image here to local storage
        return url
    }

    private fun sendTextMessage() {
        val text = etMessage.text.toString().trim()
        if (text.isEmpty()) return

        etMessage.setText("")

        val firebaseKey = firebaseDb.push().key ?: return
        val message = Message(
            senderId = currentUser,
            text = text,
            messageType = "text",
            timestamp = System.currentTimeMillis(),
            firebaseId = firebaseKey
        )

        lifecycleScope.launch {
            database.messageDao().insert(message)
            // Push to Firebase
            val remote = RemoteMessage(currentUser, text, null, "text", message.timestamp)
            firebaseDb.child(firebaseKey).setValue(remote)
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri -> uploadAndSendImage(uri) }
        }
    }

    private fun uploadAndSendImage(uri: Uri) {
        lifecycleScope.launch {
            try {
                // 1. Save locally first
                val fileName = "img_${System.currentTimeMillis()}.jpg"
                val privateDir = File(filesDir, "hidden_media")
                privateDir.mkdirs()
                val destFile = File(privateDir, fileName)

                contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(destFile).use { output ->
                        input.copyTo(output)
                    }
                }

                val firebaseKey = firebaseDb.push().key ?: return@launch
                val localMessage = Message(
                    senderId = currentUser,
                    mediaPath = destFile.absolutePath,
                    messageType = "image",
                    timestamp = System.currentTimeMillis(),
                    firebaseId = firebaseKey
                )
                database.messageDao().insert(localMessage)

                // 2. Upload to Firebase Storage
                val storageRef = firebaseStorage.child("$firebaseKey.jpg")
                storageRef.putFile(uri).await()
                val downloadUrl = storageRef.downloadUrl.await().toString()

                // 3. Push metadata to Realtime Database
                val remote = RemoteMessage(currentUser, null, downloadUrl, "image", localMessage.timestamp)
                firebaseDb.child(firebaseKey).setValue(remote)

            } catch (e: Exception) {
                Toast.makeText(this@ChatActivity, "Failed to send image: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showMessageOptions(message: Message) {
        val options = mutableListOf<String>()
        if (message.isSaved) {
            options.add("⭐ Unsave message")
        } else {
            options.add("⭐ Save message (won't auto-delete)")
        }
        if (message.senderId == currentUser) {
            options.add("🗑️ Delete message")
        }

        AlertDialog.Builder(this, R.style.DarkAlertDialog)
            .setItems(options.toTypedArray()) { _, which ->
                when (options[which]) {
                    "⭐ Save message (won't auto-delete)" -> toggleSave(message, true)
                    "⭐ Unsave message" -> toggleSave(message, false)
                    "🗑️ Delete message" -> deleteMessage(message)
                }
            }
            .show()
    }

    private fun toggleSave(message: Message, save: Boolean) {
        lifecycleScope.launch {
            database.messageDao().setSaved(message.id, save)
            val txt = if (save) "Message saved ⭐" else "Save removed"
            Toast.makeText(this@ChatActivity, txt, Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteMessage(message: Message) {
        AlertDialog.Builder(this, R.style.DarkAlertDialog)
            .setTitle("Delete message?")
            .setMessage("This will only delete it from your device.")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    database.messageDao().deleteById(message.id)
                    message.mediaPath?.let { File(it).delete() }
                    // Optional: Delete from Firebase if sender
                    if (message.senderId == currentUser && message.firebaseId != null) {
                        firebaseDb.child(message.firebaseId).removeValue()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmLogout() {
        AlertDialog.Builder(this, R.style.DarkAlertDialog)
            .setTitle("Lock app?")
            .setMessage("You'll need to log in again.")
            .setPositiveButton("Lock") { _, _ ->
                AuthManager.clearSession(this)
                finish()
                overridePendingTransition(0, 0)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onBackPressed() {
        val now = System.currentTimeMillis()
        if (now - lastBackPress < 2000) {
            backPressCount++
            if (backPressCount >= 3) {
                AuthManager.clearSession(this)
                finishAffinity()
                return
            }
        } else {
            backPressCount = 1
        }
        lastBackPress = now
        super.onBackPressed()
    }

    // DTO for Firebase
    data class RemoteMessage(
        val senderId: String = "",
        val text: String? = null,
        val remoteUrl: String? = null,
        val messageType: String = "text",
        val timestamp: Long = 0
    )
}
