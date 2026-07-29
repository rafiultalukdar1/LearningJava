import java.util.Scanner;

public class AddMethod {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int num1, num2, addition;

        System.out.print("Enter 1st number: ");
        num1 = input.nextInt();

        System.out.print("Enter 2nd number: ");
        num2 = input.nextInt();

        addition = add(num1, num2);

        System.out.println("Addition is: " + addition);
    }


    public static int add(int num1, int num2) {

        int result = num1 + num2;

        return result;
    }
}