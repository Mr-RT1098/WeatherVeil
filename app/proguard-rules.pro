# Keep SQLCipher
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }

# Keep Room
-keep class androidx.room.** { *; }

# Keep app models
-keep class com.weatherveil.hidden.database.** { *; }
-keep class com.weatherveil.weather.** { *; }

# Keep auth (important: do not obfuscate credential class names)
-keep class com.weatherveil.utils.AuthManager { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }

# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Exceptions

# WorkManager
-keep class androidx.work.** { *; }
-keep class com.weatherveil.hidden.worker.** { *; }
