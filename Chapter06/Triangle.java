import java.util.Scanner;

class Triangle {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double base, height, area;

        System.out.print("Enter Base: ");
        base = sc.nextDouble();

        System.out.print("Enter Height: ");
        height = sc.nextDouble();

        area = 0.5 * base * height;

        System.out.println("Area of Triangle = " + area);
    }
}