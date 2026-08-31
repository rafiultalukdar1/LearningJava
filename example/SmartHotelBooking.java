import java.util.Scanner;

class Hotel {

    String[] roomGuests;
    double[] roomPrices;
    int totalRooms;

    // Constructor
    Hotel(int totalRooms) {

        this.totalRooms = totalRooms;

        roomGuests = new String[totalRooms];
        roomPrices = new double[totalRooms];

        // Different room prices
        for (int i = 0; i < totalRooms; i++) {

            roomPrices[i] = 1500 + (i * 500);
        }
    }

    // Show all rooms
    void showRooms() {

        System.out.println("\n===== ROOM STATUS =====");

        for (int i = 0; i < totalRooms; i++) {

            if (roomGuests[i] == null) {

                System.out.println(
                    "Room " + (i + 1)
                    + " : Available"
                    + " | Price: " + roomPrices[i] + " Tk/day"
                );

                continue;
            }

            System.out.println(
                "Room " + (i + 1)
                + " : Booked by " + roomGuests[i]
                + " | Price: " + roomPrices[i] + " Tk/day"
            );
        }
    }

    // Book room
    void bookRoom(Scanner sc) {

        System.out.print("\nEnter room number: ");
        int room = sc.nextInt();

        if (room < 1 || room > totalRooms) {

            System.out.println("Invalid room number!");
            return;
        }

        int index = room - 1;

        if (roomGuests[index] != null) {

            System.out.println("Sorry! This room is already booked.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter guest name: ");
        String name = sc.nextLine();

        roomGuests[index] = name;

        System.out.println(
            "Room " + room + " booked successfully!"
        );
    }

    // Cancel booking
    void cancelBooking(Scanner sc) {

        System.out.print("\nEnter room number: ");
        int room = sc.nextInt();

        if (room < 1 || room > totalRooms) {

            System.out.println("Invalid room number!");
            return;
        }

        int index = room - 1;

        if (roomGuests[index] == null) {

            System.out.println("This room is already available.");
            return;
        }

        System.out.println(
            "Booking cancelled for " + roomGuests[index]
        );

        roomGuests[index] = null;
    }

    // Calculate bill
    void calculateBill(Scanner sc) {

        System.out.print("\nEnter room number: ");
        int room = sc.nextInt();

        if (room < 1 || room > totalRooms) {

            System.out.println("Invalid room number!");
            return;
        }

        int index = room - 1;

        if (roomGuests[index] == null) {

            System.out.println("This room is not booked.");
            return;
        }

        System.out.print("Enter number of days: ");
        int days = sc.nextInt();

        if (days <= 0) {

            System.out.println("Invalid number of days!");
            return;
        }

        double bill = roomPrices[index] * days;

        System.out.println("\n===== HOTEL BILL =====");

        System.out.println(
            "Guest      : " + roomGuests[index]
        );

        System.out.println(
            "Room       : " + room
        );

        System.out.println(
            "Price/Day  : " + roomPrices[index] + " Tk"
        );

        System.out.println(
            "Days       : " + days
        );

        System.out.println(
            "Total Bill : " + bill + " Tk"
        );
    }

    // Count available rooms
    void availableRooms() {

        int count = 0;

        for (int i = 0; i < totalRooms; i++) {

            if (roomGuests[i] == null) {

                count++;
            }
        }

        System.out.println(
            "\nAvailable Rooms: " + count
        );
    }
}


public class SmartHotelBooking {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("    SMART HOTEL BOOKING SYSTEM");
        System.out.println("================================");

        // Dynamic number of rooms
        System.out.print("Enter total rooms: ");
        int totalRooms = sc.nextInt();

        // Create hotel object
        Hotel hotel = new Hotel(totalRooms);

        // Main menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("            MENU");
            System.out.println("==============================");

            System.out.println("1. Show Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Calculate Bill");
            System.out.println("5. Available Rooms");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    hotel.showRooms();

                    break;

                case 2:

                    hotel.bookRoom(sc);

                    break;

                case 3:

                    hotel.cancelBooking(sc);

                    break;

                case 4:

                    hotel.calculateBill(sc);

                    break;

                case 5:

                    hotel.availableRooms();

                    break;

                case 0:

                    System.out.println(
                        "\nThank you for using Smart Hotel!"
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