class Person {
    String name = "Rafiul";
}

class Student extends Person {

    void display() {
        System.out.println(super.name);
    }
}

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.display();
    }
}