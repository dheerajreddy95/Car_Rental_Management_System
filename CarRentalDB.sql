CREATE DATABASE CarRentalDB;
USE CarRentalDB;

CREATE TABLE Cars (
    carId VARCHAR(10) PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    type VARCHAR(50),
    basePricePerDay DOUBLE,
    isAvailable BOOLEAN DEFAULT TRUE
);

CREATE TABLE Customers (
    customerId VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE Rentals (
    rentalId INT AUTO_INCREMENT PRIMARY KEY,
    carId VARCHAR(10),
    customerId VARCHAR(10),
    days INT,
    FOREIGN KEY (carId) REFERENCES Cars(carId),
    FOREIGN KEY (customerId) REFERENCES Customers(customerId)
);
