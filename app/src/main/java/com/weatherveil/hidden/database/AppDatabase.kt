package com.weatherveil.hidden.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [Message::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // ════════════════════════════════════════════════════
        // DATABASE ENCRYPTION KEY
        // This encrypts all stored messages with AES-256
        // Change this to your own secret passphrase
        // ════════════════════════════════════════════════════
        private const val DB_PASSPHRASE = "Vx9#kQ2mP7!nL4rZ"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray =
                    SQLiteDatabase.getBytes(DB_PASSPHRASE.toCharArray())
                val factory = SupportFactory(passphrase)

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "secure_messages.db"
                )
                    .openHelperFactory(factory)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
