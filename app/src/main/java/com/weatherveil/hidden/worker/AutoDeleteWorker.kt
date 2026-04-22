package com.weatherveil.hidden.worker

import android.content.Context
import androidx.work.*
import com.weatherveil.hidden.database.AppDatabase
import java.util.concurrent.TimeUnit

class AutoDeleteWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val db = AppDatabase.getInstance(applicationContext)
            val dao = db.messageDao()

            // Calculate cutoff: 31 days ago in milliseconds
            val cutoffTimestamp = System.currentTimeMillis() - (31L * 24 * 60 * 60 * 1000)

            // Delete all unsaved messages older than 31 days
            dao.deleteExpiredMessages(cutoffTimestamp)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        private const val WORK_TAG = "auto_delete_messages"

        fun scheduleDaily(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiresBatteryNotLow(false)
                .build()

            val request = PeriodicWorkRequestBuilder<AutoDeleteWorker>(
                1, TimeUnit.DAYS
            )
                .setConstraints(constraints)
                .addTag(WORK_TAG)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
