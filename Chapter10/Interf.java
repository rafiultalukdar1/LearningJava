interface Student {

    int Roll = 8404;
    String Name = "Mitali";

    void Display();
}

class Result implements Student {

    float Mark;

    public void Display() {

        System.out.println("Roll is : " + Roll);
        System.out.println("Name is : " + Name);
    }

    public void GetMark() {

        Mark = 65.45f;
    }

    public void ShowMark() {

        System.out.println("Mark is : " + Mark);
    }
}

public class Interf {

    public static void main(String[] args) {

        Result R = new Result();

        R.Display();
        R.GetMark();
        R.ShowMark();
    }
}