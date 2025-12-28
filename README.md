# CookWhatYouHave 🍳

**CookWhatYouHave** is a modern Android application designed to solve the daily culinary dilemma: *"What can I cook with the ingredients I have at home?"*

Instead of browsing through thousands of random recipes, users can simply input a main ingredient (e.g., "Chicken", "Rice"), and the app instantly suggests delicious meals. It also allows users to save their favorite recipes for offline access, ensuring a seamless experience regardless of network connectivity.

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

---
