# FSBM Digital Geology Platform
### A Spring Boot & React Solution for 3D Geological Preservation and Remote Learning

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Three.js](https://img.shields.io/badge/Three.js-000000?style=for-the-badge&logo=three.js&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Jitsi](https://img.shields.io/badge/Jitsi-0074E0?style=for-the-badge&logo=jitsi&logoColor=white)

## 📖 Project Overview

The **FSBM Digital Geology Platform** is a comprehensive full-stack solution designed to modernize the Geology Department at the Faculty of Sciences Ben M'Sik (FSBM). In the face of digital transformation challenges, this platform addresses the critical need for preserving geological heritage and ensuring global accessibility to scientific resources.

By digitizing physical collections and integrating remote learning tools, this project bridges the gap between traditional geological study and modern software engineering, offering a unified environment for students, researchers, and administration.

---

## 🚀 Live Deployment Status

The backend infrastructure is currently live and serving requests. The frontend user interface is undergoing a major refactoring phase to optimize the 3D rendering engine.

| Component | Status | Access / Notes |
| :--- | :---: | :--- |
| **Backend API** | ✅ **Live** | [**Access Swagger UI**](https://goelogy-api.onrender.com/docs) <br> *Fully functional REST API with documentation.* |
| **Frontend UI** | 🔗 **Partner Repo** | [**Access Frontend Repository**](https://github.com/HamzaElhart/geology-platform) <br> *Hosted and maintained by frontend partner.* |

---

## 🏗️ Architecture & Design

### Monolithic Layered Architecture
To ensure deployment simplicity and maintenance efficiency for this academic ecosystem, the system follows a **Monolithic Layered Architecture**. This design ensures cohesive integration between the educational modules, the virtual museum, and the administrative dashboard.

### Security Implementation
Security is paramount for institutional data. The platform implements a robust, stateless security architecture using **Spring Security** combined with **JSON Web Tokens (JWT)**.
* **Authentication:** Validates identity via email/password.
* **Authorization:** Role-based access control (Admin, Teacher, Student) ensures users only interact with permitted endpoints.

![System Architecture Diagram - See Figure 30 in Report]

---

## ✨ Key Features

### 🏛️ Virtual Museum (3D Visualization)
A core innovation of the platform is the **Virtual Museum**, utilized for the preservation of the department's rich geological heritage.
* **Tech:** Built with **Three.js** to render high-fidelity 3D models of minerals, rocks, and fossils directly in the browser.
* **Impact:** Allows remote interaction (zoom, rotate) with fragile specimens that are physically stored in the department.
* ![Uploading image.png…]()


### 🎓 Smart Classroom Management
The platform revolutionizes the traditional learning model by integrating real-time remote capabilities.
* **Conferencing:** Seamless integration with **Jitsi Meet** allows teachers to launch and manage live video classrooms directly from the dashboard.
* **Courseware:** Teachers can structure courses, upload resources (PDF/Docs), and generate unique access codes for student enrollment.

### 🗄️ Geological Data Handling
Designed to handle complex scientific datasets:
* **Specimen Cataloging:** A structured database (**MySQL**) manages extensive metadata for geological collections, including chemical formulas, crystal systems, and texture data.
* **Digital Library:** A centralized repository for scientific articles, e-books, and educational videos, categorized for easy retrieval.

---

## 🔌 API Documentation

The backend exposes a comprehensive set of RESTful endpoints documented via Swagger. Key controllers include:

* `AuthController`: Manages registration and secure login for all roles.
* `ClassroomController`: Handles course creation, student enrollment, and material uploads.
* `SpecimenController`: Manages the CRUD operations for the 3D geological collection and metadata.
* `ConferenceController`: Orchestrates Jitsi meeting links and session timing.

👉 **Test the endpoints live:** [https://goelogy-api.onrender.com/docs](https://goelogy-api.onrender.com/docs)

---

## 🛠️ Installation & Setup (Docker)

To replicate the production environment locally, we utilize **Docker** for containerization.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/mizoham/goelogy-platform-api.git
    cd digital-geology-platform
    ```

2.  **Run with Docker Compose:**
    Ensure Docker Desktop is running. This will spin up the Spring Boot API and the MySQL database container.
    ```bash
    docker-compose up --build
    ```
3.  **Access Local Instance:**
    * API: `http://localhost:9000/api`
    * Swagger: `http://localhost:9000/docs`

---

## 👨‍💻 Tech Stack

**Backend**
* **Java / Spring Boot:** Core framework for API development.
* **Hibernate (ORM):** For efficient database interactions.
* **Spring Security & JWT:** For stateless authentication.

**Data**
* **MySQL:** Relational database management.

**Frontend**
* **React.js:** Component-based UI library.
* **Three.js:** 3D graphics rendering engine.
* **Tailwind CSS:** Utility-first styling framework.

**DevOps & Tools**
* **Docker:** Containerization.
* **Postman:** API testing and validation.
* **Visual Studio Code & IntelliJ IDEA:** Development environments.

---
