App Description
The Movie Explorer app is a feature-rich Android application designed to bring the world of cinema directly to your fingertips. With a clean and intuitive user interface, the app provides a seamless experience for discovering, exploring, and enjoying your favorite movies. Whether you're a casual viewer or a movie enthusiast, Movie Explorer offers everything you need to stay updated with the latest trends and dive into detailed information about your favorite films.
Key Features
1.	Home Screen:
o	Trending Movies: Discover the top 5 trending movies, updated daily or weekly, displayed in a visually appealing horizontal scroll.
o	Movies by Category: Browse through movies categorized into "Now Playing," "Popular," "Top-Rated," and "Upcoming." Each category displays movies in a grid layout with posters, providing a quick preview.
2.	Movie Details Screen:
o	Get comprehensive details about each movie, including its title, banner, poster, description, release year, duration, and genre.
o	About Tab: Read an in-depth overview of the movie's storyline.
o	Cast Tab: View a list of cast members with their profile pictures and names for better insight into the movie's talent.
3.	Trailer Player Integration:
o	Watch official trailers directly in the app with a responsive video player.
o	Features include play/pause controls, a seek bar, and full-screen toggle for a complete viewing experience.
4.	Interactive Design:
o	A user-friendly interface with smooth navigation and responsive layouts.
o	Optimized for all screen sizes, ensuring an enjoyable experience on any Android device.
5.	Powered by TMDB:
o	All movie data is fetched using The Movie Database (TMDB) API, ensuring reliable and up-to-date information.

Build/Run Instructions for Movie Explorer App
Follow these steps to build and run the Movie Explorer app on your local machine:
________________________________________
Prerequisites
1.	Android Studio: Install the latest version of Android Studio (Download Here).
2.	JDK: Ensure Java Development Kit (JDK) 8 or higher is installed.
3.	API Key: Get a TMDB API key from TMDB API.
________________________________________
Step-by-Step Instructions
1. Clone the Repository
•	Clone the project from GitHub:
git clone <repository-url>
cd MovieExplorer
2. Open the Project in Android Studio
•	Open Android Studio.
•	Click on File > Open.
•	Navigate to the project folder and select it.
•	Locate the local.properties file in the project root directory (create it if it doesn’t exist).
3. Sync the Project
•	After adding the API key, sync the project with Gradle by clicking "Sync Now" in the notification bar or using File > Sync Project with Gradle Files.
4. Build the Project
•	Build the project to check for any errors:
o	Go to Build > Make Project or press Ctrl + F9.
5. Run the App
•	Connect an Android device or start an emulator.
•	Run the app by clicking the Run button (green triangle) or pressing Shift + F10.

Running the App on a Physical Device
1.	Enable Developer Options on your Android device.
o	Go to Settings > About Phone and tap on Build Number 7 times.
o	Enable USB Debugging in the Developer Options.
2.	Connect your device to the computer via USB.
3.	Select your device in the Run Configurations dropdown in Android Studio.
4.	Click Run to install and launch the app on your device.
Known Issues
•	If the app doesn’t build:
o	Ensure your API key is correctly set in local.properties.
o	Verify you have a stable internet connection for Gradle dependencies.
•	If you encounter a Gradle sync failure:
o	Use File > Invalidate Caches / Restart in Android Studio.


Architecture Explanation: MVP (Model-View-Presenter)
The Movie Explorer app is designed using the MVP (Model-View-Presenter) architecture to ensure scalability, modularity, and testability. This architecture separates the codebase into three distinct layers: Model, View, and Presenter, each with specific responsibilities.
________________________________________
1. Model
The Model layer is responsible for handling the app's data and business logic.
•	Responsibility:
o	Fetching data from the TMDB API using Retrofit.
o	Managing and processing data before passing it to the Presenter.
•	Key Components:
o	Data Models: Represent objects such as movies, cast, and genres.
o	API Service: Retrofit interfaces and networking logic.
o	Repository: Acts as the single source of truth, providing data from APIs or local sources.
________________________________________
2. View
The View layer is responsible for rendering the UI and interacting with the user.
•	Responsibility:
o	Displaying data provided by the Presenter.
o	Handling user inputs (e.g., clicks, scrolls) and delegating them to the Presenter.
o	Keeping the UI code clean and focused only on displaying information.
•	Key Components:
o	Activities and Fragments: Act as the View components (e.g., Home Screen, Movie Details Screen).
o	XML Layouts: Define the visual elements (buttons, lists, grids).
________________________________________
3. Presenter
The Presenter acts as the middle layer between the View and the Model.
•	Responsibility:
o	Fetching data from the Model.
o	Transforming data into a format suitable for the View.
o	Handling interactions between the user and the View.
o	Ensuring the View remains passive and does not directly interact with the Model.
•	Key Components:
o	Separate Presenters for each screen or module (e.g., HomePresenter, MovieDetailsPresenter).
o	Lifecycle awareness to avoid memory leaks (using tools like WeakReference for Views or integrating with Lifecycle-aware components).
________________________________________
Flow of Data
1.	User Interaction:
o	The user interacts with the View (e.g., clicks a movie or scrolls through categories).
o	The View informs the Presenter of the action.
2.	Presenter Logic:
o	The Presenter calls the Model (via a repository) to fetch the necessary data (e.g., movie details, trending movies).
3.	Model Data:
o	The Model fetches data from the TMDB API using Retrofit and returns it to the Presenter.
4.	Updating the View:
o	The Presenter processes the data and updates the View with it (e.g., shows trending movies or movie details).
________________________________________
Why MVP for Movie Explorer?
•	Separation of Concerns:
Each layer has a distinct role, making the code more modular and easier to manage.
•	Testability:
The Presenter contains the logic and can be tested independently of the View or Model.
•	Scalability:
Adding new features (e.g., a "Favorites" section) requires changes only in specific layers, without affecting the entire codebase.
•	Code Reusability:
Presenter and Model logic can be reused across multiple Views (e.g., using the same Presenter logic for similar UI screens).

Libraries and Design Rationale
The following libraries are integrated into the Movie Explorer app to achieve a clean, efficient, and maintainable implementation:
________________________________________
1. Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
•	Purpose: Handles networking tasks and makes API calls to the TMDB API.
•	Features:
o	Simplifies HTTP requests and responses.
o	converter-gson allows parsing JSON responses into Kotlin data classes.
o	converter-scalars is used for raw scalar responses when needed.
•	Rationale: Retrofit is widely regarded as the most developer-friendly and efficient library for networking in Android, ensuring robust API handling.
________________________________________
2. Gson
implementation 'com.google.code.gson:gson:2.10'
•	Purpose: Serializes and deserializes JSON data for communication between the app and the TMDB API.
•	Rationale: Gson seamlessly integrates with Retrofit and supports custom serializers, making it ideal for handling complex JSON objects.
________________________________________
3. OkHttp Logging Interceptor
implementation 'com.squareup.okhttp3:logging-interceptor:4.9.3'
•	Purpose: Logs HTTP requests and responses for debugging and development.
•	Features:
o	Provides detailed logs of API calls, including headers, payloads, and responses.
•	Rationale: Helps identify networking issues and simplifies debugging during development.
________________________________________
4. Picasso
implementation 'com.squareup.picasso:picasso:2.8'
•	Purpose: Loads and caches images for displaying movie posters, banners, and cast profile pictures.
•	Features:
o	Handles image transformations (e.g., resizing, cropping).
o	Caches images to reduce repeated network calls and improve performance.
•	Rationale: Picasso is lightweight and reliable for handling image loading and is easy to integrate into projects.
________________________________________
5. CircleImageView
implementation 'de.hdodenhof:circleimageview:3.1.0'
•	Purpose: Displays circular images for cast profile pictures in the Movie Details screen.
•	Features:
o	Simplifies creating rounded image views without additional custom code.
•	Rationale: Provides an out-of-the-box solution for creating aesthetically pleasing circular images.

Known Issues/Limitations
The app initially aimed to use a YouTube supporting library for embedding trailers using the https://www.youtube.com/watch?v={key} URL format. However, due to compatibility issues and errors with the library, this functionality could not be implemented as planned. As a workaround, a WebView was used to display the YouTube trailer within the app. While this solution ensures trailers are viewable, it may lack advanced features like seamless player controls or a native YouTube experience.

