public class PrimeNbr100 {

    public static void main(String[] args) {

        System.out.println("Prime numbers from 1 to 100 are:");

        for (int i = 2; i <= 100; i++) {

            int prime = 1;

            for (int loop = 2; loop < i; loop++) {

                if (i % loop == 0) {
                    prime = 0;
                    break;
                }
            }

            if (prime == 1) {
                System.out.print(i + " ");
            }
        }
    }
}