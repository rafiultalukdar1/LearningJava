class Box<T> {

    T value;

    void setValue(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }
}

public class GenericClass {

    public static void main(String[] args) {

        Box<String> box = new Box<>();

        box.setValue("Java");

        System.out.println(box.getValue());
    }
}