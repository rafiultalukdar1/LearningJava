import java.util.Scanner;

public class RectangleArea {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Length: ");
        int length = sc.nextInt();

        System.out.print("Enter Width: ");
        int width = sc.nextInt();

        int area = length * width;

        System.out.println("Area of Rectangle = " + area);
    }
}