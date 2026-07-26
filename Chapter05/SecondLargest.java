public class SecondLargest {

    public static void main(String[] args) {

        int[] arr = {12, 45, 8, 67, 34, 67, 25};

        int largest = arr[0];
        int secondLargest = arr[0];

        // Find Largest
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        // Find Second Largest
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }

        System.out.println("Largest Number = " + largest);
        System.out.println("Second Largest Number = " + secondLargest);
    }
}