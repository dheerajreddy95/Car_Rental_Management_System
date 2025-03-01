import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.loadCarsFromDatabase();  

        Scanner scanner = new Scanner(System.in);
        rentalSystem.menu();
        scanner.close();
    }
}
