public class ElseIfExample {

    public static void main(String[] args) {

        int mark = 75;

        if (mark >= 90) {
            System.out.println("A");
        } else if (mark >= 80) {
            System.out.println("A-");
        } else if (mark >= 70) {
            System.out.println("B");
        } else {
            System.out.println("F");
        }
    }
}