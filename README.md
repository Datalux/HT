# HomeworkTest Android Application

HomeworkTest is an Android application designed to browse and search for photos using the Unsplash API. It serves as a demonstration of modern Android development practices, emphasizing a clean, scalable architecture and a reactive UI built with Jetpack Compose.

## Features

*   **Photo Browsing:** Displays an infinitely scrollable list of photos fetched from the Unsplash API.
*   **Photo Search:** Allows users to search for photos based on keywords.
*   **Dynamic UI Updates:** Utilizes Kotlin StateFlow and Jetpack Compose state management for a reactive UI that updates efficiently as data changes.
*   **Pagination:** Implements pagination to load photos incrementally, optimizing performance and data usage.
*   **Loading & Empty States:** Provides visual feedback to the user during data fetching operations and when no results are found.
*   **Basic Error Handling:** Includes mechanisms to catch and log network errors.
## Architecture

The application adheres to a layered architecture inspired by **Clean Architecture** principles. This approach emphasizes separation of concerns, making the codebase more modular, testable, scalable, and easier to maintain. The primary pattern used for the presentation layer is **MVVM (Model-View-ViewModel)**.
This uni-directional data flow (UI -> ViewModel -> UseCase -> Repository -> DataSource, and data flowing back) is central to the design.

### 1. Presentation Layer (`app/src/main/java/.../presentation`)

This layer is responsible for everything related to the user interface and user interaction. It is kept unaware of the underlying data sources and complex business logic.

*   **UI (Composable Screens & Components):**
  *   Located in `it.datalux.homeworktest.presentation.screen.*` (e.g., `PhotoListScreen.kt`) and `it.datalux.homeworktest.presentation.components.*`.
  *   Built entirely with **Jetpack Compose**, providing a declarative and reactive way to define UI elements.
  *   Screens observe state exposed by ViewModels (typically `StateFlow` or Compose `State`) and render the UI accordingly.
  *   User interactions (e.g., button clicks, search input) are captured by Composables and trigger functions in the ViewModel.
  *   **Navigation:** Handled by **Navigation Compose**, allowing for navigation between different Composable destinations within a single `MainActivity`.

*   **ViewModels (`PhotosViewModel.kt`):**
  *   Located in `it.datalux.homeworktest.presentation.screen.photosList.*`.
  *   Follow the MVVM pattern, acting as state holders and intermediaries between the UI and the Domain layer (UseCases).
  *   They prepare data fetched from UseCases into a UI-consumable format.
  *   Expose UI state (e.g., list of photos, loading status, error messages) using `kotlinx.coroutines.flow.StateFlow` or `androidx.compose.runtime.mutableStateListOf`.
  *   Contain UI-related logic (e.g., managing search mode, handling back button logic for search).
  *   Injected with necessary UseCases via **Hilt (`@HiltViewModel`)**.
  *   Coroutine operations are scoped to `viewModelScope` for proper lifecycle management.

### 2. Domain Layer (`app/src/main/java/.../domain`)

This is the core of the application, containing the business logic and rules. It is independent of both the Presentation and Data layers, meaning it has no knowledge of Android Framework specifics (like `Context` or UI elements) or how data is stored/fetched.

*   **UseCases (`PhotosUseCase.kt`):**
  *   Located in `it.datalux.homeworktest.domain.usecase.*`.
  *   Encapsulate specific pieces of business logic or individual user actions (e.g., "get a list of photos," "search for photos").
  *   They are simple classes, often with a single public `invoke` or `execute` function.
  *   Orchestrate data flow by interacting with Repository interfaces. For instance, `PhotosUseCase` will call methods defined in the `PhotosRepository` interface.
  *   This abstraction allows business logic to be tested independently and makes it reusable.

*   **Domain Models/Entities (`Photo.kt` in the domain layer):**
  *   Located in `it.datalux.homeworktest.domain.entity.*`.
  *   Represent the fundamental data structures and business objects of the application (e.g., a `Photo` entity containing only the fields relevant to the application's business rules and UI display needs).
  *   These are plain Kotlin data classes, free from any platform-specific or data source-specific annotations or dependencies.
  *   They define the "what" of the data, not the "how" it's obtained or presented.

### 3. Data Layer (`app/src/main/java/.../data`)

This layer is responsible for providing data to the application, abstracting away the actual source of the data (network, local database, cache, etc.).

*   **Repositories (`PhotosRepository.kt` - interface, `PhotosRepositoryImpl.kt` - implementation):**
  *   The interface (`PhotosRepository.kt`) is defined in the **Domain Layer** (`it.datalux.homeworktest.domain.*`) following the Dependency Inversion Principle. This allows the Domain layer to define its data requirements without depending on the concrete implementation in the Data layer.
  *   The implementation (`PhotosRepositoryImpl.kt`) resides in the **Data Layer** (`it.datalux.homeworktest.data.photos.repository.*`).
  *   `PhotosRepositoryImpl` is responsible for deciding where to fetch data from (currently, only the network). If caching were implemented, the repository would manage retrieving from cache first, then network.
  *   It implements the methods defined in the `PhotosRepository` interface (e.g., `getPhotosList`, `search`).
  *   Manages pagination logic (e.g., using the `Paginator` utility) when interacting with the Unsplash API.
  *   It interacts with data sources (like `PhotosApi`) and maps data from DTOs to Domain Entities.
  *   `PhotosMockRepositoryImpl.kt` provides a mock implementation for testing and UI previews.

*   **Remote Data Sources:**
  *   **Retrofit Service (`PhotosApi.kt`):** Located in `it.datalux.homeworktest.data.photos.remote.api.*`.
    *   An interface defining the HTTP API endpoints for the Unsplash service using Retrofit annotations (`@GET`, `@Query`, etc.).
  *   **Data Transfer Objects (DTOs):** Located in `it.datalux.homeworktest.data.photos.remote.dto.*` (e.g., `Photo.kt`, `User.kt` in the `dto` package).
    *   These are Kotlin data classes that precisely model the structure of the JSON responses from the Unsplash API.
    *   They are annotated with `kotlinx.serialization.Serializable` (or Gson's `@SerializedName` if Gson is the primary parser) for automatic JSON parsing.
    *   DTOs are specific to the remote data source and can be more detailed or differently structured than Domain Models.
  *   **Network Client (OkHttp & Retrofit instance):** Configured in `NetworkModule.kt` (using Hilt) to provide instances of Retrofit and OkHttp, including features like logging interceptors.

*   **Mappers (e.g., `toPhotoEntity()` extension function):**
  *   Located usually within the data layer (e.g., `it.datalux.homeworktest.data.common.mapper.*`).
  *   Responsible for converting data between different layers, specifically from Data Layer DTOs to Domain Layer Entities (e.g., `data.dto.Photo` to `domain.entity.Photo`) and vice-versa if needed. This decoupling ensures that changes in the API response structure don't directly impact the Domain or Presentation layers, as long as the mapping logic is updated.

*   **Local Data Sources (Potential Future Implementation):**
  *   This would involve components like a **Room Database** for caching data locally.
  *   The Repository would then be extended to first check the local database for data before making a network request.

### Dependency Management (Hilt)

*   **Hilt** is used for compile-time dependency injection, simplifying the process of providing and managing dependencies throughout the application.
*   `@HiltViewModel` annotates ViewModels.
*   `@Module` and `@InstallIn` are used to define Hilt modules (e.g., `AppModule.kt`, `NetworkModule.kt`) that instruct Hilt on how to provide instances of interfaces (like `PhotosRepository`), Retrofit services, or other classes.
*   `@Inject` is used in constructors to request dependencies.

### Key Architectural Benefits:

*   **Testability:** Each layer can be tested independently. ViewModels can be unit tested by mocking UseCases, UseCases by mocking Repositories, and Repositories by mocking data sources.
*   **Maintainability & Scalability:** Clear separation of concerns makes it easier to understand, modify, and add new features without unintended side effects.
*   **Flexibility:** The Data layer can be swapped out (e.g., from network-only to network-with-cache) with minimal impact on the Domain or Presentation layers, as long as the Repository interface contract is maintained.
*   **Reusability:** Domain layer logic (UseCases, Entities) can be potentially reused across different presentation layers (e.g., mobile, web, if the core logic is shared).

## Unit Testing

Unit testing is crucial for ensuring the correctness and reliability of individual components in isolation. Given the layered architecture, each layer can be unit tested by mocking its dependencies. This project includes unit tests for the `PhotosViewModel`.

### 1. Testing ViewModels (`PhotosViewModel.kt`)

The primary example of ViewModel testing in this project can be found in:
`app/src/test/java/it/datalux/homeworktest/PhotosViewModelUnitTest.kt`.

*   **Goal:** Verify that the `PhotosViewModel` correctly processes inputs, manages its state (e.g., `photosList`, `loading`), and interacts with its dependencies (e.g., `PhotosUseCase`).
*   **Approach in `PhotosViewModelUnitTest.kt`:**
  *   **Test Doubles:** Instead of using a mocking library like Mockito, this test suite utilizes a real `PhotosUseCase` instance which is, in turn, initialized with `PhotosMockRepositoryImpl`. This approach tests the ViewModel's integration with a controlled, predictable version of its direct dependency and the repository. This is a valid strategy, especially for simpler interactions or when the mock repository accurately simulates different scenarios.
  *   **Coroutines Testing:** Uses `kotlinx.coroutines.test.runTest` to execute suspend functions and `delay` to allow asynchronous operations to complete for assertion.
  *   **State Verification:** Directly accesses and asserts the state of `photosViewModel.photosList` and other relevant properties.

*   **Key Aspects Demonstrated in `PhotosViewModelUnitTest.kt`:**
  *   **Setup (`@Before`):** Initializes the `PhotosMockRepositoryImpl`, `PhotosUseCase`, and `PhotosViewModel` before each test.


## Project Setup
Add Your Unsplash API Key to the Project

The application requires an API key from Unsplash to fetch photos. This key is configured as a `BuildConfig` field, which is defined in the module-level Gradle file.

* **Open `app/build.gradle.kts`:** 
  * In Android Studio, locate and open the file: `HomeworkTest/app/build.gradle.kts`
* **Locate `buildConfigField`:**
  *   Inside the `android { ... }` block, find the `defaultConfig { ... }` section.
  *   You will see the following line: `buildConfigField("String", "API_KEY", "\"<YOUR-API-KEY-HERE>\"")`
  *   Add your API key here





