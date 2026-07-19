import java.util.Scanner;

class BigNum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Your First Number: ");
        int a = sc.nextInt();

        System.out.print("Enter Your Second Number: ");
        int b = sc.nextInt();

        if (a > b) {
            System.out.println(a + " is Big");
        } else {
            System.out.println(b + " is Big");
        }
    }
}