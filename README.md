# Hospital Management System

## Overview

This project is a Hospital Management System designed to assist local hospitals with their administrative duties. The system provides staff members with tools to manage patient information efficiently. Staff members can add, remove, and view patient details, ensuring that all required information is stored securely. The application is designed to be user-friendly, cost-effective, and simple to use, serving as a middle ground between expensive, complex systems and low-quality alternatives.

## Features

- **User Authentication**: Staff members must sign in with valid credentials to access the system. New users can sign up with an administrator code.
- **Patient Management**: 
  - **Add Patient**: Staff can add new patients with detailed information, including name, gender, age, condition, and more.
  - **Remove Patient**: Patients can be removed from the system using their ID and an admin code to prevent accidental deletions.
  - **View Patient Information**: Staff can view a list of all patients and access detailed information about each patient.
- **Help System**: A comprehensive help menu is available to guide users through the application's features.
- **Data Validation**: The system includes robust data validation to ensure that all input data is correct and meets the required criteria.
- **Permanent Data Storage**: Patient and staff information is stored in a secure database, ensuring data integrity and accessibility.

## Motivation & Research

The project was inspired by existing hospital management systems like **Oracle Health** and **Epic Systems Corporation**. While these systems are feature-rich, they are often costly and have a steep learning curve. This application aims to provide a simpler, more affordable alternative for smaller hospitals or clinics.

### Oracle Health
Oracle Health offers a wide range of features, including patient engagement tools and clinical digital assistants. However, these systems are complex and expensive.

### Epic Systems Corporation
Epic Systems is known for its integrated software that connects hospitals, clinics, and health plans. Their systems are highly effective but also come with a high cost and complexity.

### How This Application Differs
This application is designed to be simple, cost-effective, and easy to use, making it accessible for smaller healthcare facilities that may not have the resources for larger systems.

## Specifications

### Program Function
- The system is a standalone application.
- Each user has a unique username and password.
- Staff members must be verified through a database to access the system.
- Authenticated staff can add, remove, and view patient information.
- Patient information is displayed in a coherent and user-friendly manner.

### User Interface
- **Sign-In Interface**: Users input their credentials to access the system.
- **Sign-Up Interface**: New users can register with an administrator code.
- **Main Interface**: Displays patient information and provides options to add or remove patients.
- **Help Interface**: Offers guidance on using the application.
- **Add Patient Interface**: Allows staff to input detailed patient information.
- **Remove Patient Interface**: Requires patient ID and admin code to remove a patient.

### Data Storage
- **Staff Table (tblStaff)**: Stores staff information (userID, email, password).
- **Patients Table (tblPatients)**: Stores patient information (Patient-ID, firstName, surname, gender, age, condition, etc.).
- **Help Table (tblHelp)**: Stores help topics and descriptions.

## Hardware and Software Requirements

### Programmer Requirements
- **Hardware**: 1GHz or faster processor, 4GB RAM, 15GB hard disk space.
- **Software**: Windows 10/11, Java 20 or higher, Java IDE (e.g., Visual Studio Code, NetBeans), MS Access 365.

### User Requirements
- **Hardware**: 1GHz or faster processor, 3GB RAM, 10GB hard disk space.
- **Software**: Windows 10/11, Java 20 or higher (for JAR file execution), or a Java IDE for running the code.

## Advanced Techniques

### JavaFX
The application uses JavaFX for its graphical user interface (GUI). JavaFX allows for the creation of aesthetically pleasing and highly customizable interfaces. The use of FXML files and CSS styling ensures that the GUI is both functional and visually appealing.

### Maven
Maven is used for build automation, making the project easily scalable and manageable. It allows for efficient dependency management and ensures that the application can be built and run on any machine with relative ease.

### GitHub Repository
The project is hosted on GitHub, allowing for easy collaboration and remote development. The repository includes all documentation and over 50 commits, showcasing the development process.

### SVG Application
The application uses SVG images, which can be scaled without losing quality. This ensures that the interface remains clear and user-friendly, even when resized.

## Testing

### Functional Testing
Functional testing was conducted to ensure that the application works as intended. Test cases included adding and removing patients, as well as verifying data validation and authentication processes.

### Test Results
- **Sign-Up Test**: Ensures that only valid email addresses are accepted.
- **Add Patient Test**: Validates that patient age is within acceptable ranges (0-120).

## Evaluation

### Strengths
- User-friendly GUI with aesthetic design.
- Infinite patient capacity with add/remove functionality.
- Secure data access limited to verified staff members.
- Efficient display of patient information.

### Weaknesses
- Performance impact on lower-end devices due to multiple scene openings.
- Limited depth in the help menu.
- No email validation during sign-up.
- Database does not check if a patient has already been removed.
- No edit patient function.

### Improvements
- Consolidate FXML files to improve performance.
- Enhance the help menu with more detailed guides.
- Implement email validation during sign-up.
- Add an edit patient function.
- Improve logging using the built-in Logger class.

## Conclusion

This Hospital Management System provides a simple, cost-effective solution for managing patient information in smaller healthcare facilities. While there are areas for improvement, the application offers a robust set of features that streamline administrative tasks and ensure data security.

---

For more details, visit the [GitHub repository](https://github.com/GuyMcKechnie/HospitalApplication).
