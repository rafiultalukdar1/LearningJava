class Student {

    String name;
    int age;

    // Parameterized Constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }

    // Copy Constructor
    Student(Student s) {
        name = s.name;
        age = s.age;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age : " + age);
    }

    public static void main(String[] args) {

        Student s1 = new Student("Rafiul", 25);

        Student s2 = new Student(s1);   // Copy Constructor

        System.out.println("First Object:");
        s1.display();

        System.out.println();

        System.out.println("Second Object:");
        s2.display();
    }
}