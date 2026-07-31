class MethodOverloading {

    // Method 1
    static int add(int a, int b) {
        return a + b;
    }

    // Method 2
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method 3
    static double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {

        System.out.println("Addition of 2 Integers : " + add(10, 20));
        System.out.println("Addition of 3 Integers : " + add(10, 20, 30));
        System.out.println("Addition of 2 Doubles  : " + add(10.5, 20.5));

    }
}