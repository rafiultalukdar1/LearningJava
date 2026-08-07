// package College;

public class Student {

    int id;
    String name;

    public void GetData() {

        id = 101;
        name = "Rahim";
    }

    public void Display() {

        System.out.println("Student ID : " + id);
        System.out.println("Student Name : " + name);
    }

    public static void main(String[] args) {

        Student s = new Student();

        s.GetData();
        s.Display();
    }
}