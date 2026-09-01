import java.util.Scanner;

class ATM {

    String name;
    int pin;
    double balance;

    String[] transactions = new String[10];
    int transactionCount = 0;

    // Constructor
    ATM(String name, int pin, double balance) {

        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    // Add transaction
    void addTransaction(String text) {

        if (transactionCount < transactions.length) {

            transactions[transactionCount] = text;
            transactionCount++;
        }
    }

    // Check PIN
    boolean checkPin(int enteredPin) {

        return enteredPin == pin;
    }

    // Show balance
    void showBalance() {

        System.out.println(
            "Current Balance: " + balance + " Tk"
        );
    }

    // Deposit money
    void deposit(double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;

        addTransaction("Deposited " + amount + " Tk");

        System.out.println(
            amount + " Tk deposited successfully."
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

        addTransaction("Withdrawn " + amount + " Tk");

        System.out.println(
            amount + " Tk withdrawn successfully."
        );
    }

    // Transfer money
    void transfer(String receiver, double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {

            System.out.println("Insufficient balance!");
            return;
        }

        balance -= amount;

        addTransaction(
            "Transferred " + amount + " Tk to " + receiver
        );

        System.out.println(
            amount + " Tk transferred to " + receiver
        );
    }

    // Mini statement
    void miniStatement() {

        System.out.println("\n===== MINI STATEMENT =====");

        if (transactionCount == 0) {

            System.out.println("No transactions yet.");

            return;
        }

        for (int i = 0; i < transactionCount; i++) {

            System.out.println(
                (i + 1) + ". " + transactions[i]
            );
        }
    }
}


public class SmartATM {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Account creation
        System.out.println("===== SMART ATM =====");

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Set your PIN: ");
        int pin = sc.nextInt();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        // Create ATM object
        ATM atm = new ATM(name, pin, balance);

        System.out.println("\nAccount created successfully!");

        // PIN verification
        int attempts = 0;
        boolean login = false;

        while (attempts < 3) {

            System.out.print("\nEnter PIN to login: ");
            int enteredPin = sc.nextInt();

            if (atm.checkPin(enteredPin)) {

                login = true;

                System.out.println(
                    "Login successful! Welcome " + name
                );

                break;
            }

            attempts++;

            System.out.println(
                "Wrong PIN! Attempts left: " + (3 - attempts)
            );
        }

        // Block account after 3 wrong attempts
        if (!login) {

            System.out.println(
                "\nAccount blocked due to multiple wrong PIN attempts!"
            );

            sc.close();

            return;
        }

        // ATM Menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("          ATM MENU");
            System.out.println("==============================");

            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Mini Statement");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    atm.showBalance();

                    break;

                case 2:

                    System.out.print("Enter deposit amount: ");
                    double deposit = sc.nextDouble();

                    atm.deposit(deposit);

                    break;

                case 3:

                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = sc.nextDouble();

                    atm.withdraw(withdraw);

                    break;

                case 4:

                    System.out.print("Enter receiver name: ");
                    String receiver = sc.next();

                    System.out.print("Enter transfer amount: ");
                    double transfer = sc.nextDouble();

                    atm.transfer(receiver, transfer);

                    break;

                case 5:

                    atm.miniStatement();

                    break;

                case 0:

                    System.out.println(
                        "\nThank you for using Smart ATM!"
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