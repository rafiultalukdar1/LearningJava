import java.util.Scanner;

class Quadratic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first number: ");
        double a = scanner.nextDouble();

        System.out.print("Enter your second number: ");
        double b = scanner.nextDouble();

        System.out.print("Enter your third number: ");
        double c = scanner.nextDouble();

        double d = (b * b) - (4 * a * c);

        if (d > 0) {

            double r1 = (-b + Math.sqrt(d)) / (2 * a);
            double r2 = (-b - Math.sqrt(d)) / (2 * a);

            System.out.println("The roots are: " + r1 + " and " + r2);

        } else if (d == 0) {

            double r = (-b) / (2 * a);

            System.out.println("The root is: " + r);

        } else {

            System.out.println("The roots are not possible.");
        }
    }
}