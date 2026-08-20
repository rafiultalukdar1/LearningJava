import java.util.Scanner;

class DigitalWallet {

    String ownerName;
    int pin;
    double balance;

    String[] history = new String[20];
    int transactionCount = 0;

    // Constructor
    DigitalWallet(String ownerName, int pin, double balance) {

        this.ownerName = ownerName;
        this.pin = pin;
        this.balance = balance;
    }

    // Add transaction to history
    void addHistory(String transaction) {

        if (transactionCount < history.length) {

            history[transactionCount] = transaction;
            transactionCount++;
        }
    }

    // Show balance
    void checkBalance() {

        System.out.println("\nOwner Name : " + ownerName);
        System.out.println("Balance    : " + balance + " Tk");
    }

    // Add money
    void addMoney(double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;

        addHistory("Money Added: +" + amount + " Tk");

        System.out.println(amount + " Tk added successfully.");
    }

    // Send money
    void sendMoney(String receiver, double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {

            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;

        addHistory("Sent " + amount + " Tk to " + receiver);

        System.out.println(
            amount + " Tk sent successfully to " + receiver
        );
    }

    // Withdraw money
    void withdraw(double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {

            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;

        addHistory("Withdraw: -" + amount + " Tk");

        System.out.println(
            amount + " Tk withdrawn successfully."
        );
    }

    // Change PIN
    void changePin(int oldPin, int newPin) {

        if (oldPin != pin) {

            System.out.println("Wrong old PIN!");
            return;
        }

        pin = newPin;

        addHistory("PIN changed successfully");

        System.out.println("PIN changed successfully.");
    }

    // Show transaction history
    void showHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactionCount == 0) {

            System.out.println("No transactions found.");
            return;
        }

        for (int i = 0; i < transactionCount; i++) {

            System.out.println((i + 1) + ". " + history[i]);
        }
    }
}

public class DigitalWalletApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // User information input
        System.out.println("===== CREATE YOUR DIGITAL WALLET =====");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Set a 4 digit PIN: ");
        int pin = sc.nextInt();

        System.out.print("Enter starting balance: ");
        double balance = sc.nextDouble();

        // Create wallet object
        DigitalWallet wallet =
            new DigitalWallet(name, pin, balance);

        System.out.println("\nWallet created successfully!");

        // Main menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("     DIGITAL WALLET SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Check Balance");
            System.out.println("2. Add Money");
            System.out.println("3. Send Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Change PIN");
            System.out.println("6. Transaction History");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    wallet.checkBalance();

                    break;

                case 2:

                    System.out.print("Enter amount: ");
                    double addAmount = sc.nextDouble();

                    wallet.addMoney(addAmount);

                    break;

                case 3:

                    sc.nextLine();

                    System.out.print("Enter receiver name: ");
                    String receiver = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double sendAmount = sc.nextDouble();

                    wallet.sendMoney(receiver, sendAmount);

                    break;

                case 4:

                    System.out.print("Enter amount: ");
                    double withdrawAmount = sc.nextDouble();

                    wallet.withdraw(withdrawAmount);

                    break;

                case 5:

                    System.out.print("Enter old PIN: ");
                    int oldPin = sc.nextInt();

                    System.out.print("Enter new PIN: ");
                    int newPin = sc.nextInt();

                    wallet.changePin(oldPin, newPin);

                    break;

                case 6:

                    wallet.showHistory();

                    break;

                case 0:

                    System.out.println(
                        "\nThank you for using Digital Wallet!"
                    );

                    sc.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice! Please try again."
                    );
            }
        }
    }
}