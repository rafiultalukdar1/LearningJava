import java.util.Scanner;

public class CelsiusToFahrenheit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Temperature in Celsius: ");
        double celsius = sc.nextDouble();

        double fahrenheit = (9.0 / 5.0) * celsius + 32;

        System.out.println("Temperature in Fahrenheit = " + fahrenheit);

        sc.close();
    }
}