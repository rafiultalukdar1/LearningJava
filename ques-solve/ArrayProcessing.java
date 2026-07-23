import java.util.Scanner;

class ArrayProcessing {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];
        int sum = 0;

        System.out.println("Enter 5 numbers:");

        for (int i = 0; i < 5; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Array Elements: ");

        for (int i = 0; i < 5; i++) {
            System.out.print(arr[i] + " ");
            sum += arr[i];
        }

        System.out.println();
        System.out.println("Sum = " + sum);
    }
}