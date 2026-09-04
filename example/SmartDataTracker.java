import java.util.Scanner;

class DataTracker {

    double dataLimit;
    double[] dailyUsage;
    int daysUsed = 0;

    // Constructor
    DataTracker(double dataLimit, int totalDays) {

        this.dataLimit = dataLimit;
        dailyUsage = new double[totalDays];
    }

    // Add daily usage
    void addUsage(double usage) {

        if (daysUsed >= dailyUsage.length) {

            System.out.println("Monthly tracking limit reached!");
            return;
        }

        if (usage < 0) {

            System.out.println("Invalid data usage!");
            return;
        }

        dailyUsage[daysUsed] = usage;
        daysUsed++;

        System.out.println(
            usage + " MB usage added successfully."
        );
    }

    // Calculate total usage
    double getTotalUsage() {

        double total = 0;

        for (int i = 0; i < daysUsed; i++) {

            total += dailyUsage[i];
        }

        return total;
    }

    // Show remaining data
    void showRemainingData() {

        double total = getTotalUsage();
        double remaining = dataLimit - total;

        System.out.println("\n===== DATA STATUS =====");

        System.out.println(
            "Monthly Limit : " + dataLimit + " MB"
        );

        System.out.println(
            "Used          : " + total + " MB"
        );

        if (remaining > 0) {

            System.out.println(
                "Remaining     : " + remaining + " MB"
            );

        } else {

            System.out.println(
                "Remaining     : 0 MB"
            );

            System.out.println(
                "Warning: Your data limit is finished!"
            );
        }
    }

    // Calculate average usage
    void showAverage() {

        if (daysUsed == 0) {

            System.out.println("No usage data available.");
            return;
        }

        double total = getTotalUsage();

        double average = total / daysUsed;

        System.out.println(
            "Average Daily Usage: " + average + " MB"
        );
    }

    // Find highest usage day
    void highestUsage() {

        if (daysUsed == 0) {

            System.out.println("No usage data available.");
            return;
        }

        int highestDay = 0;

        for (int i = 1; i < daysUsed; i++) {

            if (dailyUsage[i] > dailyUsage[highestDay]) {

                highestDay = i;
            }
        }

        System.out.println("\n===== HIGHEST USAGE =====");

        System.out.println(
            "Day   : " + (highestDay + 1)
        );

        System.out.println(
            "Usage : " + dailyUsage[highestDay] + " MB"
        );
    }

    // Show usage history
    void showHistory() {

        if (daysUsed == 0) {

            System.out.println("No usage history found.");
            return;
        }

        System.out.println("\n===== USAGE HISTORY =====");

        for (int i = 0; i < daysUsed; i++) {

            System.out.println(
                "Day " + (i + 1)
                + " : "
                + dailyUsage[i]
                + " MB"
            );
        }
    }

    // Check usage warning
    void usageWarning() {

        double total = getTotalUsage();

        double percentage =
            (total / dataLimit) * 100;

        System.out.println(
            "\nData Used: " + percentage + "%"
        );

        if (percentage >= 90) {

            System.out.println(
                "WARNING: You are almost out of data!"
            );

        } else if (percentage >= 70) {

            System.out.println(
                "Notice: Your data usage is getting high."
            );

        } else {

            System.out.println(
                "Your data usage is normal."
            );
        }
    }
}


public class SmartDataTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("     SMART DATA TRACKER");
        System.out.println("==============================");

        // Monthly data limit
        System.out.print(
            "Enter monthly data limit (MB): "
        );

        double limit = sc.nextDouble();

        // Number of days
        System.out.print(
            "Enter tracking days: "
        );

        int days = sc.nextInt();

        // Create object
        DataTracker tracker =
            new DataTracker(limit, days);

        // Main menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("            MENU");
            System.out.println("==============================");

            System.out.println("1. Add Daily Usage");
            System.out.println("2. Show Data Status");
            System.out.println("3. Show Average Usage");
            System.out.println("4. Highest Usage Day");
            System.out.println("5. Usage History");
            System.out.println("6. Usage Warning");
            System.out.println("0. Exit");

            System.out.print(
                "\nEnter your choice: "
            );

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print(
                        "Enter today's usage (MB): "
                    );

                    double usage = sc.nextDouble();

                    tracker.addUsage(usage);

                    break;

                case 2:

                    tracker.showRemainingData();

                    break;

                case 3:

                    tracker.showAverage();

                    break;

                case 4:

                    tracker.highestUsage();

                    break;

                case 5:

                    tracker.showHistory();

                    break;

                case 6:

                    tracker.usageWarning();

                    break;

                case 0:

                    System.out.println(
                        "Thank you for using Smart Data Tracker!"
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