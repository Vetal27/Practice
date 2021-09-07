
public class Practice {
    double a;
    double b;
    double c;
    double x1;
    double x2;

    Practice(double A, double B, double C) throws ArithmeticException
    {
       // if (A == 0)
        //    {throw new ArithmeticException("Це не квадратне рівняння");}
       // else
        {a = A;}
        b = B;
        c = C;
    }

    public void Desicion () throws ArithmeticException
    {
        try {
            double d = b * b - 4 * a * c;
            if (d < 0)
                { throw new ArithmeticException(); }
            try {
                if (a == 0)
                {throw new ArithmeticException();}
                if (d == 0) {
                    x1 = x2 = (-b) / (2 * a);
                }
                    x1 = (-b - Math.sqrt(d)) / (2 * a);
                    x2 = (-b + Math.sqrt(d)) / (2 * a);
            }
            catch (ArithmeticException e) {
                System.out.println(e);
                System.out.println("Число а має бути не нуль");
            }
        }
        catch (ArithmeticException e) {
            System.out.println(e);
            System.out.println("Немає розв'язків");
        }
    }

    public static void main (String[] args)
    {
        Practice QE = new Practice( 0, 4, 4);
        QE.Desicion();
        System.out.println(QE.x1);
        System.out.println(QE.x2);
    }
}
