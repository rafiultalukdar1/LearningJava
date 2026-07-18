public class Fibonacci {

    public static void main(String[] args) {

        int first = 0;
        int second = 1;

        System.out.print("First 10 Fibonacci Series: ");

        for (int i = 1; i <= 10; i++) {

            System.out.print(first + " ");

            int next = first + second;
            first = second;
            second = next;
        }
    }
}