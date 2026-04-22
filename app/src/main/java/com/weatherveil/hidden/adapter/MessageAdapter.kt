package com.weatherveil.hidden.adapter

import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.weatherveil.R
import com.weatherveil.hidden.database.Message
import java.text.SimpleDateFormat
import java.util.*

class MessageAdapter(
    private val currentUser: String,
    private val onLongPress: (Message) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: List<Message> = emptyList()

    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    inner class SentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessage: TextView = view.findViewById(R.id.tvMessageText)
        val tvTime: TextView = view.findViewById(R.id.tvMessageTime)
        val ivSaved: ImageView = view.findViewById(R.id.ivSavedIndicator)
        val bubbleLayout: View = view.findViewById(R.id.messageBubble)
    }

    inner class ReceivedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessage: TextView = view.findViewById(R.id.tvMessageText)
        val tvTime: TextView = view.findViewById(R.id.tvMessageTime)
        val ivSaved: ImageView = view.findViewById(R.id.ivSavedIndicator)
        val bubbleLayout: View = view.findViewById(R.id.messageBubble)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == currentUser) VIEW_TYPE_SENT
        else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SENT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_sent, parent, false)
            SentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_received, parent, false)
            ReceivedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = messages[position]
        val timeStr = SimpleDateFormat("HH:mm", Locale.getDefault())
            .format(Date(msg.timestamp))

        val dayStr = getDayLabel(msg.timestamp, position)

        when (holder) {
            is SentViewHolder -> {
                holder.tvMessage.text = msg.text ?: "[Media]"
                holder.tvTime.text = timeStr
                holder.ivSaved.visibility = if (msg.isSaved) View.VISIBLE else View.GONE
                holder.itemView.setOnLongClickListener {
                    onLongPress(msg)
                    true
                }
            }
            is ReceivedViewHolder -> {
                holder.tvMessage.text = msg.text ?: "[Media]"
                holder.tvTime.text = timeStr
                holder.ivSaved.visibility = if (msg.isSaved) View.VISIBLE else View.GONE
                holder.itemView.setOnLongClickListener {
                    onLongPress(msg)
                    true
                }
            }
        }
    }

    override fun getItemCount() = messages.size

    fun updateMessages(newMessages: List<Message>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize() = messages.size
            override fun getNewListSize() = newMessages.size
            override fun areItemsTheSame(oldPos: Int, newPos: Int) =
                messages[oldPos].id == newMessages[newPos].id
            override fun areContentsTheSame(oldPos: Int, newPos: Int) =
                messages[oldPos] == newMessages[newPos]
        })
        messages = newMessages
        diffResult.dispatchUpdatesTo(this)
    }

    private fun getDayLabel(timestamp: Long, position: Int): String? {
        if (position == 0) return formatDay(timestamp)
        val prevTimestamp = messages[position - 1].timestamp
        val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return if (sdf.format(Date(timestamp)) != sdf.format(Date(prevTimestamp))) {
            formatDay(timestamp)
        } else null
    }

    private fun formatDay(timestamp: Long): String {
        val cal = Calendar.getInstance()
        val today = Calendar.getInstance()
        cal.timeInMillis = timestamp
        return when {
            cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> "Today"
            cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) - 1 -> "Yesterday"
            else -> SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(timestamp))
        }
    }
}
