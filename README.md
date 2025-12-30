# CookWhatYouHave 🍳

**CookWhatYouHave** is a modern Android application designed to solve the daily culinary dilemma: *"What can I cook with the ingredients I have at home?"*

Built entirely with **Jetpack Compose**, this app allows users to search for recipes based on available ingredients, view detailed instructions, and save their favorite meals for **offline access** using a local database.

> ⚠️ **Note:** This project is currently **Under Development**. Features are being implemented iteratively.

---

## 🏗 Architecture

The application is built following **Clean Architecture** principles and the **MVVM (Model-View-ViewModel)** pattern. This ensures a strict separation of concerns, making the app scalable, testable, and easy to maintain.

---

## 🛠 Tech Stack & Libraries

This project leverages **Modern Android Development (MAD)** standards:

* **Language:** Kotlin
* **Dependency Injection:** Hilt - For decoupled and testable code structure.
* **Concurrency:** Coroutines & Flow - For asynchronous operations and reactive data streams.
* **Network:**
    * Retrofit- Type-safe HTTP client.
    * OkHttp - For network interceptors and logging.
    * Moshi - JSON Parsing.
* **Local Storage:** Room - SQLite abstraction for offline caching.
* **Architecture Components:** ViewModel, Lifecycle, Repository Pattern.

## 🔌 API Reference
This app uses the public API provided by [TheMealDB](https://www.themealdb.com/api.php).

## 📸 Screenshots

## Future Improvements

* [ ] Add Category filters
* [ ] . . . . . .

---
