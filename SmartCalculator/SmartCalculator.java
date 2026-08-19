import java.util.Scanner;

// Calculator Class
class Calculator {

    // Constructor
    Calculator() {
        System.out.println("Smart Calculator Started!");
    }

    // Addition
    double calculate(double a, double b) {
        return a + b;
    }

    // Subtraction
    double calculate(double a, double b, char operator) {

        if (operator == '-') {
            return a - b;
        }

        return 0;
    }

    // Multiplication
    double multiply(double a, double b) {
        return a * b;
    }

    // Division
    double divide(double a, double b) {

        if (b == 0) {
            return 0;
        }

        return a / b;
    }
}


// Main Class
public class SmartCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Object creation
        Calculator calc = new Calculator();

        // Infinite menu
        while (true) {

            System.out.println("\n==============================");
            System.out.println("       SMART CALCULATOR");
            System.out.println("==============================");

            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Even / Odd");
            System.out.println("6. Prime Check");
            System.out.println("7. Factorial");
            System.out.println("8. Average");
            System.out.println("9. Maximum Number");
            System.out.println("10. Reverse Number");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();


            // Switch
            switch (choice) {

                // Addition
                case 1:

                    System.out.print("Enter first number: ");
                    double a = sc.nextDouble();

                    System.out.print("Enter second number: ");
                    double b = sc.nextDouble();

                    System.out.println(
                        "Result = " + calc.calculate(a, b)
                    );

                    break;


                // Subtraction
                case 2:

                    System.out.print("Enter first number: ");
                    a = sc.nextDouble();

                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();

                    System.out.println(
                        "Result = " + calc.calculate(a, b, '-')
                    );

                    break;


                // Multiplication
                case 3:

                    System.out.print("Enter first number: ");
                    a = sc.nextDouble();

                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();

                    System.out.println(
                        "Result = " + calc.multiply(a, b)
                    );

                    break;


                // Division
                case 4:

                    System.out.print("Enter first number: ");
                    a = sc.nextDouble();

                    System.out.print("Enter second number: ");
                    b = sc.nextDouble();

                    if (b == 0) {

                        System.out.println(
                            "Error: Cannot divide by zero!"
                        );

                        continue;
                    }

                    System.out.println(
                        "Result = " + calc.divide(a, b)
                    );

                    break;


                // Even / Odd
                case 5:

                    System.out.print("Enter a number: ");
                    int num = sc.nextInt();

                    if (num % 2 == 0) {

                        System.out.println(
                            num + " is Even."
                        );

                    } else {

                        System.out.println(
                            num + " is Odd."
                        );
                    }

                    break;


                // Prime Check
                case 6:

                    System.out.print("Enter a number: ");
                    num = sc.nextInt();

                    if (num < 2) {

                        System.out.println(
                            num + " is not Prime."
                        );

                        break;
                    }

                    boolean prime = true;

                    for (int i = 2; i < num; i++) {

                        if (num % i == 0) {

                            prime = false;
                            break;
                        }
                    }

                    if (prime) {

                        System.out.println(
                            num + " is Prime."
                        );

                    } else {

                        System.out.println(
                            num + " is not Prime."
                        );
                    }

                    break;


                // Factorial
                case 7:

                    System.out.print("Enter a number: ");
                    num = sc.nextInt();

                    long factorial = 1;

                    for (int i = 1; i <= num; i++) {

                        factorial *= i;
                    }

                    System.out.println(
                        "Factorial = " + factorial
                    );

                    break;


                // Average
                case 8:

                    System.out.print(
                        "How many numbers? "
                    );

                    int n = sc.nextInt();

                    double sum = 0;

                    for (int i = 1; i <= n; i++) {

                        System.out.print(
                            "Enter number " + i + ": "
                        );

                        sum += sc.nextDouble();
                    }

                    double average = sum / n;

                    System.out.println(
                        "Average = " + average
                    );

                    break;


                // Maximum
                case 9:

                    System.out.print(
                        "How many numbers? "
                    );

                    n = sc.nextInt();

                    System.out.print(
                        "Enter number 1: "
                    );

                    double max = sc.nextDouble();

                    for (int i = 2; i <= n; i++) {

                        System.out.print(
                            "Enter number " + i + ": "
                        );

                        double value = sc.nextDouble();

                        if (value > max) {

                            max = value;
                        }
                    }

                    System.out.println(
                        "Maximum = " + max
                    );

                    break;


                // Reverse Number
                case 10:

                    System.out.print(
                        "Enter a number: "
                    );

                    num = sc.nextInt();

                    int reverse = 0;

                    while (num != 0) {

                        int digit = num % 10;

                        reverse = reverse * 10 + digit;

                        num = num / 10;
                    }

                    System.out.println(
                        "Reverse = " + reverse
                    );

                    break;


                // Exit
                case 0:

                    System.out.println(
                        "\nThank you for using Smart Calculator!"
                    );

                    sc.close();

                    return;


                // Invalid Choice
                default:

                    System.out.println(
                        "Invalid choice! Try again."
                    );
            }
        }
    }
}