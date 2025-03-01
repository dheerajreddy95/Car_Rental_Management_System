import java.sql.*;
import java.util.*;

class CarRentalSystem {
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Rental> rentals = new ArrayList<>();

    public void loadCarsFromDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cars")) {

            while (rs.next()) {
                cars.add(new Car(
                        rs.getString("carId"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getDouble("basePricePerDay")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCarToDatabase(Car car) {
        String sql = "INSERT INTO Cars (carId, brand, model, type, basePricePerDay, isAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getCarId());
            pstmt.setString(2, car.getBrand());
            pstmt.setString(3, car.getModel());
            pstmt.setString(4, car.getType());
            pstmt.setDouble(5, car.calculatePrice(1)); // Storing base price
            pstmt.setBoolean(6, car.isAvailable());

            pstmt.executeUpdate();
            System.out.println("Car added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));

            String sql = "INSERT INTO Rentals (carId, customerId, days) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, car.getCarId());
                pstmt.setString(2, customer.getCustomerId());
                pstmt.setInt(3, days);
                pstmt.executeUpdate();

                System.out.println("\nCar rented successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCarProcess(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        Rental rentalToReturn = null;
        for (Rental rental : rentals) {
            if (rental.getCustomer().getName().equalsIgnoreCase(customerName) && !rental.getCar().isAvailable()) {
                rentalToReturn = rental;
                break;
            }
        }

        if (rentalToReturn != null) {
            Car carToReturn = rentalToReturn.getCar();
            carToReturn.returnCar();
            updateCarAvailability(carToReturn.getCarId(), true);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("No car rented by this customer, or the car is already returned.");
        }
    }

    private void updateCarAvailability(String carId, boolean availability) {
        String sql = "UPDATE Cars SET isAvailable = ? WHERE carId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, availability);
            pstmt.setString(2, carId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
