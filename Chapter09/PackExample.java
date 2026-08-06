// package Pack;

public class PackExample {

    int roll;
    String name;
    float mark;

    public void GetData() {

        roll = 12345;
        name = "Wahid";
        mark = 55.5f;
    }

    public void Display() {

        System.out.println("Roll is : " + roll);
        System.out.println("Name is : " + name);
        System.out.println("Mark is : " + mark);
    }

    public static void main(String[] args) {

        PackExample p = new PackExample();

        p.GetData();
        p.Display();
    }
}