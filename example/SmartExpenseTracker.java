import java.util.Scanner;

class ExpenseTracker {

    String[] categories;
    double[] amounts;
    int count = 0;

    // Constructor
    ExpenseTracker(int size) {

        categories = new String[size];
        amounts = new double[size];
    }

    // Add expense
    void addExpense(String category, double amount) {

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        if (count >= categories.length) {

            System.out.println("Expense limit reached!");
            return;
        }

        categories[count] = category;
        amounts[count] = amount;

        count++;

        System.out.println("Expense added successfully!");
    }

    // Show all expenses
    void showExpenses() {

        if (count == 0) {

            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n===== EXPENSE HISTORY =====");

        for (int i = 0; i < count; i++) {

            System.out.println(
                (i + 1) + ". "
                + categories[i]
                + " - "
                + amounts[i]
                + " Tk"
            );
        }
    }

    // Calculate total expense
    void showTotal() {

        double total = 0;

        for (int i = 0; i < count; i++) {

            total += amounts[i];
        }

        System.out.println(
            "\nTotal Expense: " + total + " Tk"
        );
    }

    // Find highest expense
    void highestExpense() {

        if (count == 0) {

            System.out.println("No expenses found.");
            return;
        }

        int index = 0;

        for (int i = 1; i < count; i++) {

            if (amounts[i] > amounts[index]) {

                index = i;
            }
        }

        System.out.println("\n===== HIGHEST EXPENSE =====");

        System.out.println(
            "Category : " + categories[index]
        );

        System.out.println(
            "Amount   : " + amounts[index] + " Tk"
        );
    }
}


public class SmartExpenseTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== SMART EXPENSE TRACKER =====");

        System.out.print("How many expenses can you add? ");
        int size = sc.nextInt();

        ExpenseTracker tracker =
            new ExpenseTracker(size);

        while (true) {

            System.out.println("\n==============================");
            System.out.println("            MENU");
            System.out.println("==============================");

            System.out.println("1. Add Expense");
            System.out.println("2. Show Expenses");
            System.out.println("3. Show Total Expense");
            System.out.println("4. Highest Expense");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter category: ");
                    String category = sc.next();

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();

                    tracker.addExpense(category, amount);

                    break;

                case 2:

                    tracker.showExpenses();

                    break;

                case 3:

                    tracker.showTotal();

                    break;

                case 4:

                    tracker.highestExpense();

                    break;

                case 0:

                    System.out.println(
                        "Thank you for using Expense Tracker!"
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