# NBM Volunteer Mobile Application

This repository contains the source code for the **National Telecom Volunteer Mobile Application** (https://play.google.com/store/apps/details?id=com.dot.nbm&hl=en), a mobile app developed for the National Broadband Mission (NBM). The application allows users to contribute telecom data, such as signal strength and network quality, which helps enhance broadband infrastructure in India by collecting crowdsourced data from volunteers.

## Overview

The application is designed with a focus on gathering real-time telecom data from users across various regions and submitting it to a central database for analysis. The app includes key features such as signal collection, geo-location tracking, and seamless user interaction.

## Key Features

- **Real-Time Signal Collection**: Gathers mobile network data such as signal strength and quality of service (QoS) parameters.
- **Tab-Based Navigation**: Provides users with intuitive tabs for QoS data, about the mission, and app settings.
- **User Permissions Handling**: Manages location and phone state permissions, ensuring compliance with user privacy.
- **Splash Screen**: Includes a splash screen with a timed transition to the main activity.
  
## Files

- **MainActivity.java**: Contains the main application logic, including tabbed navigation and permission handling.
- **PagerAdaptor.java**: Manages fragment transitions within the app, including QoS, About, and Settings tabs.
- **SplashScreenActivity.java**: Displays the splash screen and redirects users to the main activity after a brief delay.

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/himanjim/NBM_Volunteer_Mobile_Application.git
   ```

2. **Build the Project**: 
   Open the project in Android Studio and sync Gradle to build the app.

3. **Run the Application**: 
   Deploy the app on an Android device or emulator to test the functionality.

## Requirements

- Android SDK
- Java Development Kit (JDK)
- Required libraries (as specified in `build.gradle`)

## License

This project is licensed under the MIT License.

---

You can customize or add details as needed to fit your project.
