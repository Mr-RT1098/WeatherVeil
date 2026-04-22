package com.weatherveil.hidden.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // Who sent it: "Mr.RT" or "Naira"
    val senderId: String = "",

    // Text content (null if media message)
    val text: String? = null,

    // Local file path for images/media
    val mediaPath: String? = null,

    // Remote URL for images/media (Firebase Storage)
    val remoteUrl: String? = null,

    // "text" or "image"
    val messageType: String = "text",

    // Unix timestamp in milliseconds
    val timestamp: Long = System.currentTimeMillis(),

    // If true, this message is NEVER auto-deleted
    val isSaved: Boolean = false,

    // Whether the other person has read it
    val isRead: Boolean = false,

    // Firebase unique ID to prevent duplicates
    val firebaseId: String? = null
)
