class Animal {

    void sound() {
        System.out.println("Animal makes a sound.");
    }
}

// Sub Class
class Dog extends Animal {

    void bark() {
        System.out.println("Dog barks.");
    }
}

public class SuperSubClass {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.sound();   // Super Class Method
        d.bark();    // Sub Class Method
    }
}