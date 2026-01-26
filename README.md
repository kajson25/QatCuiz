# QatCuiz: Cat Knowledge & Quiz App

QatCuiz is a modern Android application designed for feline enthusiasts. Built with a high-performance stack and a strict MVI architecture, the app allows users to explore a massive directory of cat breeds, test their knowledge in a dynamic quiz environment, and compete for the top spot on a global leaderboard.

## 🚀 Key Features

### 1. Breed Exploration Catalog
- **Searchable Breed List**: Browse a comprehensive list of cat breeds retrieved via TheCatAPI.
- **Detailed Previews**: Explore temperament, origins, lifespan, and physical traits.
- **Rich Media Support**: Each breed features an interactive image gallery and high-resolution photo viewer with swipe navigation (Pager).
- **Single Source of Truth**: All data is cached locally using **Room Database**, ensuring a fast, offline-first experience.

### 2. The Quiz System
- **Dynamic Question Generation**: Questions are procedurally generated from the local database, ensuring no two games are the same.
- **Three Unique Categories**:
    - **Guess the Fact**: Identify breeds or temperaments from a picture.
    - **Guess the Cat**: Find the correct cat based on a specific trait or description.
    - **Left or Right**: Comparative analysis of breed weight and lifespan.
- **Timed Challenges**: Players have 5 minutes to complete 20 questions.
- **Score Analytics**: Real-time scoring using the university-provided complexity formula:
  $$UBP = BTO \times 2.5 \times (1 + \frac{PVT + 120}{MVT})$$

### 3. Account & Social Integration
- **Global Leaderboard**: Publish your results and view rankings from other players globally via a custom API service.
- **Profile History**: Detailed logs of your local gameplay history and best positions.
- **Multi-Account Support**: Manage multiple local profiles simultaneously with easy switching, similar to modern social apps.
- **Local Persistence**: User preferences and profile details are secured using **Jetpack DataStore**.

## 🛠 Tech Stack

*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Modern Declarative UI)
*   **Architecture**: MVI (Model-View-Intent)
*   **Dependency Injection**: Hilt (Dagger)
*   **Networking**: Retrofit, OkHttp, KotlinX Serialization
*   **Persistence**: Room (Relational Database), DataStore (KeyValue)
*   **Concurrency**: Coroutines & Flow (StateFlow/SharedFlow)
*   **Image Loading**: Coil
*   **Theming**: Material Design 3 (Supports dynamic colors and Dark Mode)

## 🏗 Project Structure

The project follows a modular, feature-based package structure for high maintainability:
- `cat/`: Repository and ViewModel logic for breed discovery.
- `quiz/`: Question generation logic, scoring, and the interactive UI components.
- `user/`: Datastore management, local profile storage, and multi-account handling.
- `database/`: Room DAOs, entities, and database configuration.
- `networking/`: Retrofit providers and API service definitions.
- `navigation/`: Jetpack Navigation configuration for seamless screen transitions.

## 🧪 Testing
The project includes a suite of unit tests ensuring the reliability of core components:
- **ViewModels**: Logic for UI state management.
- **Repositories**: Verified data flow between API and Local DB.
- **Quiz Generator**: Randomized logic verification.
- **Compose**: Visual unit tests for select screens.

## 📦 Setup and Execution

1. **Clone the repository**:
   ```bash
   git clone https://github.com/YourUsername/Catapult-Android-Quiz-App.git
   ```
2. **API Keys**: Ensure you have an active internet connection. The app uses TheCatAPI (configured in `NetworkModule.kt`).
3. **Environment**: Use **Android Studio Koala/Ladybug** or newer.
4. **Running**: Click the "Run" button or use:
   ```bash
   ./gradlew installDebug
   ```

## 📄 License
Developed for academic purposes for the Mobilne Aplikacije (OS2024) course at Union University, School of Computing (RAF).
