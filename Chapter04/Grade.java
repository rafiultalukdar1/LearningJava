import java.util.Scanner;

public class Grade {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        switch (marks / 10) {

            case 10:
            case 9:
                System.out.println("Grade: A");
                break;

            case 8:
                System.out.println("Grade: A-");
                break;

            case 7:
                System.out.println("Grade: B");
                break;

            case 6:
                System.out.println("Grade: C");
                break;

            case 5:
                System.out.println("Grade: D");
                break;

            case 4:
                System.out.println("Grade: E");
                break;

            default:
                System.out.println("Grade: F");
                break;
        }

        sc.close();
    }
}