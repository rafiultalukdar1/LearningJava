public class ContinueReturn {

    static void printNumbers() {

        for (int i = 1; i <= 10; i++) {

            if (i == 5) {
                continue;
            }

            if (i == 8) {
                return;
            }

            System.out.println(i);
        }
    }

    public static void main(String[] args) {

        printNumbers();
    }
}