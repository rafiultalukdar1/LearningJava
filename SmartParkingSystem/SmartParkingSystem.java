import java.util.Scanner;

class ParkingSystem {

    String[] slots;
    int totalSlots;

    // Constructor
    ParkingSystem(int totalSlots) {

        this.totalSlots = totalSlots;

        // Dynamic array
        slots = new String[totalSlots];
    }

    // Park a car
    void parkCar(Scanner sc) {

        System.out.print("Enter Car Number: ");
        String carNumber = sc.next();

        // Check duplicate car
        for (int i = 0; i < totalSlots; i++) {

            if (carNumber.equalsIgnoreCase(slots[i])) {

                System.out.println("This car is already parked!");

                return;
            }
        }

        // Find empty slot
        for (int i = 0; i < totalSlots; i++) {

            if (slots[i] == null) {

                slots[i] = carNumber;

                System.out.println(
                    "Car " + carNumber
                    + " parked successfully at Slot " + (i + 1)
                );

                return;
            }
        }

        System.out.println("Parking is full!");
    }

    // Remove a car
    void removeCar(Scanner sc) {

        System.out.print("Enter Car Number: ");
        String carNumber = sc.next();

        for (int i = 0; i < totalSlots; i++) {

            if (carNumber.equalsIgnoreCase(slots[i])) {

                slots[i] = null;

                System.out.println(
                    "Car " + carNumber + " removed successfully."
                );

                return;
            }
        }

        System.out.println("Car not found!");
    }

    // Show available slots
    void showAvailableSlots() {

        int emptySlots = 0;

        for (int i = 0; i < totalSlots; i++) {

            if (slots[i] == null) {

                emptySlots++;
            }
        }

        System.out.println(
            "Available Slots: " + emptySlots
        );
    }

    // Show all parking slots
    void showParkingStatus() {

        System.out.println("\n===== PARKING STATUS =====");

        for (int i = 0; i < totalSlots; i++) {

            if (slots[i] == null) {

                System.out.println(
                    "Slot " + (i + 1) + " : Empty"
                );

                continue;
            }

            System.out.println(
                "Slot " + (i + 1)
                + " : " + slots[i]
            );
        }
    }

    // Calculate parking fee
    void calculateFee(Scanner sc) {

        System.out.print("Enter Parking Hours: ");
        int hours = sc.nextInt();

        int fee = hours * 30;

        System.out.println(
            "Parking Fee: " + fee + " Tk"
        );
    }
}


public class SmartParkingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== SMART PARKING SYSTEM =====");

        // Dynamic parking size
        System.out.print("Enter Total Parking Slots: ");
        int totalSlots = sc.nextInt();

        // Create object
        ParkingSystem parking =
            new ParkingSystem(totalSlots);

        while (true) {

            System.out.println("\n==============================");
            System.out.println("           MAIN MENU");
            System.out.println("==============================");

            System.out.println("1. Park a Car");
            System.out.println("2. Remove a Car");
            System.out.println("3. Available Slots");
            System.out.println("4. Parking Status");
            System.out.println("5. Calculate Parking Fee");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    parking.parkCar(sc);

                    break;

                case 2:

                    parking.removeCar(sc);

                    break;

                case 3:

                    parking.showAvailableSlots();

                    break;

                case 4:

                    parking.showParkingStatus();

                    break;

                case 5:

                    parking.calculateFee(sc);

                    break;

                case 0:

                    System.out.println(
                        "Thank you for using Smart Parking System!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice! Try again."
                    );
            }
        }
    }
}