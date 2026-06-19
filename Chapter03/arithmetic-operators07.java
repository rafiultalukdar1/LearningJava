public class BinarySearch {

    public static void main(String[] args) {

        int[] numbers = {10, 20, 30, 40, 50, 60, 70};
        int target = 50;

        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (numbers[mid] == target) {
                System.out.println("Found at index: " + mid);
                return;
            }

            if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Not Found");
    }
}