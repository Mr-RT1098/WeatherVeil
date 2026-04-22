# WeatherVeil - Setup Guide

## What This App Is
A fully functional weather app that hides a private, encrypted 2-person messaging
system. To anyone looking at your phone, it's just a weather app.

---

## 🔐 Default Credentials (CHANGE THESE BEFORE BUILDING!)

| User  | Username   | Password     | Change in...         |
|-------|-----------|-------------|----------------------|
| You   | phantom   | Sky#2947!x  | AuthManager.kt       |
| Friend| shadow    | Rain@8361!z | AuthManager.kt       |

**File to edit:** `app/src/main/java/com/weatherveil/utils/AuthManager.kt`

---

## 🗝️ Secret Trigger

Type **"ghost"** in the weather search bar → Hidden login opens
(case-insensitive, works whether you press search or just type all 5 letters)

To change the trigger word: edit `SECRET_CODE` in `WeatherActivity.kt`

---

## 🌤️ Weather API Setup

1. Go to https://openweathermap.org → Sign up free
2. Go to API Keys → copy your key
3. Open `WeatherActivity.kt`
4. Replace `"YOUR_OPENWEATHERMAP_API_KEY"` with your key
5. App will show demo data until you do this (still looks realistic)

---

## 🛠️ How to Build

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17+
- Android SDK 34

### Steps
1. Open Android Studio
2. File → Open → Select the `WeatherVeil` folder
3. Wait for Gradle sync to complete (~2-5 minutes first time)
4. Edit `AuthManager.kt` with your chosen usernames/passwords
5. Edit `AppDatabase.kt` — change `DB_PASSPHRASE` to something unique
6. Add your OpenWeatherMap API key to `WeatherActivity.kt`
7. Connect your Android phone (enable USB debugging in Developer Options)
8. Press the green ▶ Run button

---

## 💬 How Messaging Works (2 people, 1 phone vs 2 phones)

### Same Phone (both users share 1 phone)
Works immediately. Both users log in with their credentials and see the same message history.

### Two Different Phones (most common use case)
Requires Firebase setup:

1. Go to https://console.firebase.google.com
2. Create a new project → Add Android app
3. Package name: `com.weatherveil`
4. Download `google-services.json` → put in `app/` folder
5. In `app/build.gradle`, uncomment the Firebase lines
6. In `build.gradle` (root), uncomment the Google Services plugin
7. Rebuild the app

**Note:** With Firebase, messages are encrypted BEFORE being sent, so Firebase only
stores encrypted ciphertext. They cannot read your messages.

---

## 🛡️ Security Features

| Feature | Implementation |
|---------|---------------|
| Encrypted local database | SQLCipher AES-256 |
| Encrypted session storage | EncryptedSharedPreferences |
| Screenshot blocked | FLAG_SECURE |
| Auto-delete after 31 days | WorkManager daily job |
| No backup to cloud | backup_rules.xml excludes all hidden data |
| Hidden from app switcher | No launcher icon for hidden activities |
| Panic exit | Press Back 3× quickly = instant lock + exit |
| Save important messages | Long-press → Save → Never auto-deleted |

---

## 📁 Project Structure

```
app/src/main/java/com/weatherveil/
├── weather/
│   ├── WeatherActivity.kt     ← Main app + secret trigger
│   ├── WeatherApiService.kt   ← OpenWeatherMap API
│   ├── WeatherModels.kt       ← Data classes
│   └── ForecastAdapter.kt     ← Forecast list
├── hidden/
│   ├── LoginActivity.kt       ← Secret login screen
│   ├── ChatActivity.kt        ← Messaging screen
│   ├── adapter/
│   │   └── MessageAdapter.kt  ← Chat bubbles
│   ├── database/
│   │   ├── AppDatabase.kt     ← Encrypted Room DB
│   │   ├── Message.kt         ← Message data model
│   │   └── MessageDao.kt      ← DB queries
│   └── worker/
│       └── AutoDeleteWorker.kt← 31-day auto-delete
└── utils/
    └── AuthManager.kt         ← 2-user credentials + session
```

---

## ⚠️ Important Notes

1. **Change ALL default passwords** before installing on real devices
2. **Change DB_PASSPHRASE** in AppDatabase.kt before first run
   (changing it later will make existing messages unreadable)
3. The app appears as "WeatherVeil" in all system menus and recent apps
4. Media shared in chat is stored in app's private directory - invisible to file browsers
5. Messages and media are deleted after 31 days unless saved

---

## 🚀 Optional Enhancements You Can Add Later

- Voice messages (Android AudioRecord API)
- Message reactions (update Message entity with `reaction` field)
- Timer messages that delete after viewing (add `viewOnce` boolean)
- Custom notification sounds (only vibrate in hidden section)
- Fingerprint unlock (BiometricPrompt API)
