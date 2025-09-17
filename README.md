# Study Zen

**Project:** Study Zen
**Status:** In Development
**Team:** Projeto Integrador 6 - Engenharia de Software, PUC Campinas

---

## About The Project

Study Zen is a mobile application designed to help university students improve focus and academic performance by mitigating digital distractions. The application provides a gamified environment where students are rewarded for avoiding selected applications during their scheduled class times. This project serves as the partial fulfillment of the requirements for the "Projeto Integrador 6" course in the Software Engineering program.

---

## Table of Contents

1.  [Key Features](#key-features)
2.  [Technology Stack](#technology-stack)
3.  [Project Structure](#project-structure)
4.  [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation & Configuration](#installation--configuration)
    * [Running the Project](#running-the-project)
5.  [Contributing](#contributing)
6.  [Authors](#authors)
7.  [License](#license)

---

## Key Features

-   **User Authentication:** Secure user registration, login, and password recovery.
-   **Schedule Management:** Functionality for users to create, update, and manage their class schedules.
-   **Application Blocker:** A system for users to select specific applications to be blocked during focus periods.
-   **Gamification System:** Users earn points for successfully completed focus sessions.
-   **Dashboard & Leaderboard:** A user dashboard to track progress and a university-wide leaderboard to foster healthy competition.

---

## Technology Stack

### Backend
-   **Runtime:** Node.js
-   **Framework:** Express.js
-   **Database:** MongoDB
-   **Containerization:** Docker

### Frontend (Mobile)
-   **Framework:** React Native

---

## Getting Started

Follow these instructions to set up and run the project in a local development environment.

### Prerequisites

Ensure you have the following software installed on your machine:
-   Node.js (v18.x or later)
-   npm or yarn
-   Docker and Docker Compose
-   A configured environment for React Native development. Follow the official guide for **"React Native CLI Quickstart"**.

### Installation & Configuration

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/Brunnosh/Projeto-Integrador-6.git
    cd Projeto-Integrador-6
    ```

2.  **Configure Backend Environment:**
    ```sh
    cd backend
    npm install
    ```
    Create a `.env` file by copying the example template. This file will hold your environment variables.
    ```sh
    cp .env.example .env
    ```
    After creating the file, update the variables inside `.env` (e.g., `MONGO_URI`, `JWT_SECRET`, `PORT`).

3.  **Configure Mobile Environment:**
    ```sh
    cd ../mobile
    npm install
    ```

### Running the Project

1.  **Start the Backend Server:**
    From the `/backend` directory, run the services using Docker Compose.
    ```sh
    docker-compose up --build -d
    ```
    The server will be available at the port defined in your `.env` file.

2.  **Start the Mobile Application:**
    From the `/mobile` directory, run the application on the desired platform.
    ```sh
    # For Android
    npx react-native run-android

    # For iOS (macOS required)
    npx react-native run-ios
    ```

---

## Contributing

Contributions from the project team are welcome. Please follow these guidelines:

1.  **Branching:** Create a new branch for each feature or bugfix from the `develop` branch. Use a descriptive naming convention (e.g., `feature/user-authentication` or `fix/login-bug`).
2.  **Commits:** Write clear and concise commit messages. Reference the corresponding Jira issue key if applicable (e.g., `feat(auth): SZ-15 Implement user login endpoint`).
3.  **Pull Requests (PRs):** Once a feature is complete, open a Pull Request against the `develop` branch. Ensure your code has been tested and follows the project's coding standards. A PR must be reviewed and approved by at least one other team member before being merged.

---

## Authors

* [cite_start]Arthur José Silva Maluf [cite: 4]
* [cite_start]Bruno Tasso Savoia [cite: 5]
* [cite_start]Marcos do Amaral Miotto [cite: 6]
* [cite_start]Gustavo Kenji Mendonça Kaneko [cite: 7]
* [cite_start]Vitor Hugo Amaro Aristides [cite: 8]

---

## License

This project is proprietary and intended for academic purposes. All rights are reserved by the authors.
