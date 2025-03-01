# 🚗 Car Rental Management System

## Efficient Vehicle Booking & Rental Management with Java, JDBC & MySQL

### 📌 Project Overview
The Car Rental Management System is a Java-based application designed to streamline car rental services. It allows customers to rent cars, manage bookings, and track vehicle availability. The system leverages JDBC (Java Database Connectivity) to store and retrieve information from a MySQL database, making it a scalable and efficient solution for rental businesses.

### 🚀 Features
✔ **Customer Management** – Store and retrieve customer details, including rental history.  
✔ **Car Inventory Management** – Track available and rented cars, models, and pricing.  
✔ **Car Booking & Returns** – Manage car rentals, returns, and billing.  
✔ **Data Storage with MySQL** – Store customer and vehicle details securely.  
✔ **JDBC Integration** – Java Database Connectivity for seamless database operations.  
✔ **Data Visualization (Optional)** – Insights into rental trends, revenue, and car utilization.

### 📊 Dataset & Database Schema
The MySQL database consists of the following key tables:

#### **Customers Table**
| CustomerID | Name       | Contact       | LicenseNumber |
|------------|-----------|---------------|---------------|
| 101        | John Doe  | 9876543210    | AB12345       |
| 102        | Alice Smith | 8765432109  | CD67890       |

#### **Cars Table**
| CarID | Model          | Year | PricePerDay | AvailabilityStatus |
|--------|---------------|------|-------------|---------------------|
| 5      | Tesla Model 3 | 2023 | $100        | Available          |
| 8      | Ford Mustang  | 2022 | $150        | Rented             |

#### **Rentals Table**
| RentalID | CustomerID | CarID | StartDate  | EndDate    | TotalPrice |
|----------|------------|-------|------------|------------|------------|
| 201      | 101        | 5     | 2024-01-15 | 2024-01-18 | $300       |
| 202      | 102        | 8     | 2024-02-05 | 2024-02-10 | $500       |

### 🔧 Technologies Used
🟢 **Java** – Core programming language for business logic.  
🟢 **JDBC (Java Database Connectivity)** – For MySQL database interactions.  
🟢 **MySQL** – Storing rental, customer, and vehicle data.  
🟢 **Swing / JavaFX (Optional)** – GUI for a better user experience.  


## 🏗️ Installation & Setup

### 🔹 Prerequisites
✔ **Java (JDK 11 or later)**  
✔ **MySQL (Ensure MySQL Server is running)**  
✔ **JDBC Driver (MySQL Connector)**  

### 🔹 Installation Steps
#### 1️⃣ Clone this repository:
```bash
git clone https://github.com/dheerajreddy95/Car_Rental_Management_System.git  
cd Car_Rental_Management_System
```

#### 2️⃣ Set up the MySQL database:
- Import the `car_rental.sql` file to create tables.
- Update `database.properties` with your MySQL credentials.

#### 3️⃣ Compile and Run the Java program:
```bash
javac CarRentalSystem.java  
java CarRentalSystem  
```

---

## 📌 Key Insights 
🔄 **JDBC ensures smooth database operations** – Fast and reliable queries.  
⚡ **Scalable system** – Can be expanded with GUI & online booking features.  

---

## 🚀 Future Enhancements
🔹 **Graphical User Interface (GUI)** – Using JavaFX for an interactive UI.  
🔹 **Automated Notifications** – Email/SMS confirmations for bookings.  
🔹 **Real-time Analytics Dashboard** – Monitor rental trends dynamically.  
🔹 **Mobile App Integration** – Expanding to Android/iOS.  

---

## 📚 References
📌 **JDBC & MySQL Documentation** – Oracle   
📌 **Java Database Connection Guide** – TutorialsPoint  

---

## 📌 Why This README is Useful?
✅ **Clear project overview**  
✅ **Step-by-step setup guide**   
✅ **Modeling future improvements**
