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
│           │       ├── AuthManager.kt
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

You will find:

```kotlin
object AuthManager {
    const val USERNAME = "your_username_here"
    const val PASSWORD = "your_password_here"
}
```

Replace with your own username and password. These are the credentials used to access the Ghost layer.

---

### Step 3 — Add Your OpenWeatherMap API Key

Find where the API key is used and replace the placeholder:

```kotlin
const val WEATHER_API_KEY = "your_openweathermap_api_key_here"
```

Get your free key at: https://openweathermap.org/api

---

### Step 4 — Connect Your Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com/) and create a new project
2. Add an Android app — use your own package name
3. Download the `google-services.json` file Firebase gives you
4. Place it in the `app/` directory
5. In Firebase Console → Realtime Database → Create database

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

## Security

WeatherVeil is built with a security-first mindset:

- **AES-256 Encryption via SQLCipher** — All messages are encrypted locally. Even with physical device access, messages cannot be read without the key.
- **Screenshot Blocking** — The app prevents screen captures inside the Ghost layer, protecting sensitive conversations from shoulder-surfing and screen recording.
- **Firebase Realtime Database** — Messages are synced securely in the cloud with Firebase's infrastructure.
- **Custom Authentication** — The Ghost layer is protected by a custom credential system via AuthManager, keeping the hidden interface inaccessible to unintended users.
- **WorkManager** — Background operations are handled safely, keeping network activity isolated from the UI layer.

---

## Contributing

Pull requests are welcome. For major changes, open an issue first to discuss what you'd like to change.

---

## License

This project is open source and available under the [MIT License](LICENSE).

---

## Author

Built by [Mr-RT1098](https://github.com/Mr-RT1098) — a CS student exploring Android development, encryption, and security-first design.
