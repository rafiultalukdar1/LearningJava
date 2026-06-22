public class Arithmetic {

    public static void main(String[] args) {

        int a = 8;
        int b = 4;
        int c = 2;

        int result;

        result = a + b * c;
        System.out.println("a + b * c = " + result);

        result = (a + b) * c;
        System.out.println("(a + b) * c = " + result);

        result = a - b / c;
        System.out.println("a - b / c = " + result);
    }
}