# WeatherVeil 🌦️🔒

> A fully functional weather app on the outside — a private, encrypted messaging system on the inside.

---

## What is WeatherVeil?

WeatherVeil is a dual-purpose Android application built with Kotlin. To any casual observer, it looks and behaves exactly like a normal weather app. But if you know the secret — it becomes a private, AES-256 encrypted messaging platform.

This project was built as a personal exploration of Android development, encryption, and security-first design thinking.

---

## Features

### Weather Layer (Public Face)
- Real-time weather data powered by the **OpenWeatherMap API**
- Search any city and get current temperature, conditions, and more
- Clean, intuitive UI that looks completely standard

### Ghost Layer (Hidden Messaging)
- Triggered by typing **`ghost`** in the weather search bar
- Full private messaging interface revealed only to those who know
- Messages stored locally using **SQLCipher** (AES-256 encryption)
- Messages synced via **Firebase Realtime Database**
- **Screenshot blocking** enabled — prevents accidental or forced screen captures
- **WorkManager** used for background sync tasks

---

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| Platform | Android (min SDK varies) |
| Build System | Gradle (AGP 8.1.2) |
| Weather API | OpenWeatherMap |
| Backend | Firebase Realtime Database |
| Local Storage | SQLCipher (AES-256) |
| Background Work | WorkManager |
| Auth | Custom AuthManager |

---

## Project Structure

```
WeatherVeil/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │       ├── AuthManager.kt       ← Credentials live here
│           │       ├── WeatherActivity.kt
│           │       ├── GhostActivity.kt
│           │       └── ...
│           └── res/
├── build.gradle
├── gradle.properties
└── settings.gradle
```

---

## Setup — Run Your Own Version

### Prerequisites
- Android Studio (Hedgehog or newer recommended)
- A free [OpenWeatherMap API key](https://openweathermap.org/api)
- A Firebase project (free Spark plan is enough)
- JDK 17+

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/Mr-RT1098/WeatherVeil.git
cd WeatherVeil
```

---

### Step 2 — Configure Your Credentials in AuthManager

Open the file:
```
app/src/main/java/.../AuthManager.kt
```

You will find something like this:

```kotlin
object AuthManager {
    const val USERNAME = "your_username_here"
    const val PASSWORD = "your_password_here"
}
```

Replace the values with your own chosen username and password. These are the credentials used to access the Ghost (hidden messaging) layer. **Keep them secret — do not commit real credentials to a public repo.**

> ⚠️ **Important:** Since this is a public repo, consider moving these values to a `local.properties` file or using Android's `BuildConfig` fields injected at build time, so they never appear in version control.

---

### Step 3 — Add Your OpenWeatherMap API Key

In your project, find where the API key is used (likely in a constants file or directly in the weather fetch logic) and replace the placeholder:

```kotlin
const val WEATHER_API_KEY = "your_openweathermap_api_key_here"
```

Get your free key at: https://openweathermap.org/api

---

### Step 4 — Connect Your Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/) and create a new project
2. Add an Android app — use your own package name
3. Download the `google-services.json` file Firebase gives you
4. Place it in the `app/` directory (replacing any existing one)
5. In Firebase Console → Realtime Database → Create database
6. Set up Security Rules (see the Security section below)

---

### Step 5 — Build and Run

Open the project in Android Studio, let Gradle sync, then hit **Run**. Install on a physical device or emulator running Android 8.0+.

---

## How the Ghost Mode Works

1. Open WeatherVeil — it looks like a normal weather app
2. In the search bar, type exactly: **`ghost`**
3. The app detects this keyword and silently switches to the messaging interface
4. You are prompted for your AuthManager credentials
5. Upon successful login — the private messaging layer is revealed

---

- Move credentials to `local.properties`:

```properties
# local.properties (this file should be in .gitignore)
ghost.username=mySecretUser
ghost.password=mySecretPassword
```

- Read them at build time in `build.gradle`:

```gradle
buildConfigField "String", "GHOST_USERNAME", "\"${localProperties['ghost.username']}\""
buildConfigField "String", "GHOST_PASSWORD", "\"${localProperties['ghost.password']}\""
```

- Access in code:

```kotlin
val username = BuildConfig.GHOST_USERNAME
val password = BuildConfig.GHOST_PASSWORD
```

## Security Checklist

- [ ] Firebase rules locked down (not open read/write)
- [ ] `google-services.json` in `.gitignore`
- [ ] AuthManager credentials moved out of source code
- [ ] OpenWeatherMap API key not hardcoded in a public file
- [ ] Firebase Authentication enabled (recommended — so only signed-in users can access the database)

---

## Contributing

Pull requests are welcome. For major changes, open an issue first to discuss what you'd like to change.

---

## License

This project is open source and available under the [MIT License](LICENSE).

---

## Author

Built by [Mr-RT1098](https://github.com/Mr-RT1098) — a CS student exploring Android development, encryption, and security-first design.
