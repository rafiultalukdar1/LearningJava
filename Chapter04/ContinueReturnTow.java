public class ContinueReturnTow {

    static void checkNumbers() {

        int[] numbers = {10, -5, 20, 30, -2, 40, 50};

        for (int i = 0; i < numbers.length; i++) {

            if (numbers[i] < 0) {
                continue;
            }

            if (numbers[i] == 40) {
                return;
            }

            System.out.println(numbers[i]);
        }
    }

    public static void main(String[] args) {

        checkNumbers();
    }
}