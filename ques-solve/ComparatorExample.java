import java.util.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class ComparatorExample {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Rafiul", 85));
        students.add(new Student("John", 70));
        students.add(new Student("Alex", 95));

        students.sort((s1, s2) -> s2.marks - s1.marks);

        for (Student s : students) {
            System.out.println(s.name + " : " + s.marks);
        }
    }
}