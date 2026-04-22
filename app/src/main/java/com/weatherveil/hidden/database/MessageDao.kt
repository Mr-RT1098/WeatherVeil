package com.weatherveil.hidden.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    fun getAllMessages(): LiveData<List<Message>>

    @Query("SELECT * FROM messages ORDER BY timestamp ASC")
    suspend fun getAllMessagesOnce(): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message): Long

    @Update
    suspend fun update(message: Message)

    @Delete
    suspend fun delete(message: Message)

    @Query("DELETE FROM messages WHERE id = :id")
    suspend fun deleteById(id: Long)

    // Auto-delete: delete all unsaved messages older than 31 days
    @Query("""
        DELETE FROM messages 
        WHERE isSaved = 0 
        AND timestamp < :cutoffTimestamp
    """)
    suspend fun deleteExpiredMessages(cutoffTimestamp: Long)

    // Toggle save status
    @Query("UPDATE messages SET isSaved = :saved WHERE id = :id")
    suspend fun setSaved(id: Long, saved: Boolean)

    // Mark messages as read
    @Query("UPDATE messages SET isRead = 1 WHERE senderId != :currentUser")
    suspend fun markAllRead(currentUser: String)

    // Count unread messages
    @Query("SELECT COUNT(*) FROM messages WHERE isRead = 0 AND senderId != :currentUser")
    suspend fun countUnread(currentUser: String): Int
}
