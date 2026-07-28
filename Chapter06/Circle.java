import java.util.Scanner;

class Circle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double radius, area;

        System.out.print("Enter Radius: ");
        radius = sc.nextDouble();

        area = 3.1416 * radius * radius;

        System.out.println("Area of Circle = " + area);
    }
}