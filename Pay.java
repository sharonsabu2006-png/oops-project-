import java.util.Scanner;

class Pay
{
    int age;
    float bp, p, nbp;

    Pay(int age, float bp)
    {
        this.age = age;
        this.bp = bp;
    }

    void input()
    {
        if (age > 56)
            p = 0.20f;
        else if (age >= 45)
            p = 0.15f;
        else
            p = 0.10f;
    }

    void calc()
    {
        nbp = bp + (bp * p);
    }

    // Method 1
    void display()
    {
        System.out.println("New Basic Pay = " + nbp);
    }

    // Overloaded Method
    void display(String msg)
    {
        System.out.println(msg + nbp);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter age and basic pay:");
        int a = sc.nextInt();
        float b = sc.nextFloat();

        Pay obj = new Pay(a, b);

        obj.input();
        obj.calc();

        obj.display();                     // Calls first method
        obj.display("Updated Pay = ");     // Calls overloaded method

        sc.close();
    }
}
