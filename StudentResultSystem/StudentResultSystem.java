interface ResultSystem {

    void calculateResult();
}


// Super Class
class Student {

    int roll;
    String name;

    // Constructor
    Student(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    // Method Overloading
    void showInfo() {

        System.out.println("Student Name : " + name);
        System.out.println("Roll Number  : " + roll);
    }

    void showInfo(String title) {

        System.out.println("\n===== " + title + " =====");
        System.out.println("Student Name : " + name);
        System.out.println("Roll Number  : " + roll);
    }
}


// Sub Class
class Result extends Student implements ResultSystem {

    int[] marks;

    Result(int roll, String name, int[] marks) {

        super(roll, name);
        this.marks = marks;
    }


    // Method Overriding
    @Override
    public void calculateResult() {

        int total = 0;

        for (int mark : marks) {

            total += mark;
        }

        double average = (double) total / marks.length;

        System.out.println("Total Marks  : " + total);
        System.out.println("Average      : " + average);

        if (average >= 80) {

            System.out.println("Grade        : A+");
            System.out.println("Result       : Excellent");

        } else if (average >= 70) {

            System.out.println("Grade        : A");
            System.out.println("Result       : Very Good");

        } else if (average >= 60) {

            System.out.println("Grade        : B");

        } else if (average >= 50) {

            System.out.println("Grade        : C");

        } else if (average >= 40) {

            System.out.println("Grade        : D");

        } else {

            System.out.println("Grade        : F");
            System.out.println("Result       : Failed");
        }
    }


    void showMarks() {

        System.out.println("\nSubject Marks:");

        for (int i = 0; i < marks.length; i++) {

            System.out.println(
                "Subject " + (i + 1) + " : " + marks[i]
            );

            if (marks[i] < 40) {

                System.out.println("Status      : Failed");

                continue;
            }

            System.out.println("Status      : Passed");
        }
    }
}


// Main Class
public class StudentResultSystem {

    public static void main(String[] args) {

        int[] marks = {85, 72, 90, 68, 80};

        Result student =
            new Result(101, "Rafi", marks);

        student.showInfo("STUDENT RESULT");

        student.showMarks();

        System.out.println("\nFinal Result:");

        student.calculateResult();
    }
}