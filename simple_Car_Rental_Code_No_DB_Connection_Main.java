import java.util.*;
class Car {
    private final String carId;
    private final String brand;
    private final String model;
    private final String type;
    private final double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, String type, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public double calculatePrice(int rentalDays) { return basePricePerDay * rentalDays; }
    public boolean isAvailable() { return isAvailable; }
    public void rent() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }
}

class Customer {
    private final String customerId;
    private final String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
}

class Rental {
    private final Car car;
    private final Customer customer;
    private final int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }
    public int getDays() { return days; }
}

class CarRentalSystem {
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Rental> rentals = new ArrayList<>();

    public List<Rental> getRentals() {
        return new ArrayList<>(rentals);
    }

    public void addCar(Car car) { cars.add(car); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
            System.out.println("\nCar rented successfully.");
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    private void returnCar(Car car) {
        car.returnCar();
        System.out.println("Car returned successfully.");
    }

    private void returnCarProcess(Scanner scanner) {
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
        returnCar(carToReturn);
    } else {
        System.out.println("No car rented by this customer, or the car is already returned.");
    }
}


    public void menu() {
        while (true) {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
          Scanner scanner = new Scanner(System.in);
           int choice = scanner.nextInt();
        
            scanner.nextLine();

            switch (choice) {
                case 1 -> rentCarProcess(scanner);
                case 2 -> returnCarProcess(scanner);
                case 3 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void rentCarProcess(Scanner scanner) {
        System.out.println("\n== Rent a Car ==");
        
        System.out.println("\nSelect Car Type:");
        List<String> carTypes = Arrays.asList("Sedan", "SUV", "Convertible", "Sports Car", "Luxury Car", "Electric Car (EV)", "Pickup Truck", "Hypercar", "Muscle Car");
        for (int i = 0; i < carTypes.size(); i++) {
            System.out.println((i + 1) + ". " + carTypes.get(i));
        }
        System.out.print("Enter your choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();
        
        if (typeChoice < 1 || typeChoice > carTypes.size()) {
            System.out.println("Invalid car type.");
            return;
        }
        
        String selectedType = carTypes.get(typeChoice - 1);
        System.out.println("\nAvailable " + selectedType + " cars:");
        
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() && car.getType().equalsIgnoreCase(selectedType)) {
                availableCars.add(car);
                System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
            }
        }

        if (availableCars.isEmpty()) {
            System.out.println("No available cars of this type.");
            return;
        }
        
        System.out.println();
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("\nEnter the car ID you want to rent: ");
        String carId = scanner.nextLine();

        System.out.print("Enter the number of days for rental: ");
        int rentalDays = scanner.nextInt();
        scanner.nextLine();

        Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
        addCustomer(newCustomer);

        for (Car car : availableCars) {
            if (car.getCarId().equals(carId)) {
                System.out.println("\nTotal Price: $" + car.calculatePrice(rentalDays));
                System.out.print("Confirm rental (Y/N): ");
                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    rentCar(car, newCustomer, rentalDays);
                } else {
                    System.out.println("Rental canceled.");
                }
                return;
            }
        }
        System.out.println("Invalid car selection.");
    }

}

public class simple_Car_Rental_Code_No_DB_Connection_Main {
    public static void main(String[] args) {
        
        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.addCar(new Car("C001", "Maruti", "Swift", "Sedan", 50.0));
        rentalSystem.addCar(new Car("C002", "Toyota", "Corolla", "Sedan", 50.0));
        rentalSystem.addCar(new Car("C003", "Honda", "Civic", "Sedan", 50.0));
        rentalSystem.addCar(new Car("C004", "Hyundai", "Elantra", "Sedan", 50.0));
        rentalSystem.addCar(new Car("C005", "Mahindra", "Thar", "SUV", 70.0));
        rentalSystem.addCar(new Car("C006", "Toyota", "RAV4", "SUV", 70.0));
        rentalSystem.addCar(new Car("C007", "Ford", "Explorer", "SUV", 70.0));
        rentalSystem.addCar(new Car("C008", "Jeep", "Cherokee", "SUV", 70.0));
        rentalSystem.addCar(new Car("C009", "Mazda", "MX-5 Miata", "Convertible", 60.0));
        rentalSystem.addCar(new Car("C010", "BMW", "4 Series Convertible", "Convertible", 60.0));
        rentalSystem.addCar(new Car("C011", "Audi", "A5 Cabriolet", "Convertible", 60.0));
        rentalSystem.addCar(new Car("C012", "Porsche", "911", "Sports Car", 90.0));
        rentalSystem.addCar(new Car("C013", "Chevrolet", "Corvette", "Sports Car", 90.0));
        rentalSystem.addCar(new Car("C014", "Nissan", "370Z", "Sports Car", 90.0));
        rentalSystem.addCar(new Car("C015", "Mercedes-Benz", "S-Class", "Luxury Car", 200.0));
        rentalSystem.addCar(new Car("C016", "BMW", "7 Series", "Luxury Car", 200.0));
        rentalSystem.addCar(new Car("C017", "Audi", "A8", "Luxury Car", 200.0));
        rentalSystem.addCar(new Car("C018", "Lexus", "LS", "Luxury Car", 200.0));
        rentalSystem.addCar(new Car("C019", "Phantom","VIII", "Luxury Car", 200.0));
        rentalSystem.addCar(new Car("C020", "Tesla", "Model S", "Electric Car (EV)", 100.0));
        rentalSystem.addCar(new Car("C021", "Nissan", "Leaf", "Electric Car (EV)", 100.0));
        rentalSystem.addCar(new Car("C022", "Chevrolet", "Bolt EV", "Electric Car (EV)", 100.0));
        rentalSystem.addCar(new Car("C023", "Ford", "F-150", "Pickup Truck", 110.0));   
        rentalSystem.addCar(new Car("C024", "Chevrolet", "Silverado", "Pickup Truck", 110.0));
        rentalSystem.addCar(new Car("C025", "Ram", "1500", "Pickup Truck", 110.0));
        rentalSystem.addCar(new Car("C026", "Ford", "GT", "Hypercar", 1800.0));
        rentalSystem.addCar(new Car("C027", "Bugatti", "Chiron", "Hypercar", 180.0));
        rentalSystem.addCar(new Car("C028", "Koenigsegg", "Jesko", "Hypercar", 180.0));
        rentalSystem.addCar(new Car("C029", "McLaren", "Speedtail", "Hypercar", 180.0));
        rentalSystem.addCar(new Car("C030", "Ford", "Mustang GT", "Muscle Car", 180.0));
        rentalSystem.addCar(new Car("C031", "Chevrolet", "Camaro", "Muscle Car", 180.0));
        rentalSystem.addCar(new Car("C032", "Dodge", "Challenger", "Muscle Car", 180.0));
        rentalSystem.addCar(new Car("C033", "Plymouth", "Barracuda", "Muscle Car", 180.0));
        rentalSystem.menu();
    }
}
