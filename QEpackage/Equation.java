package QEpackage;

import java.util.*;

public class Equation {
    private final double a;
    private final double b;
    private final double c;

    Equation(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public ArrayList<Double> desicion() {
        ArrayList<Double> answer = new ArrayList<Double>();
        double result = 0;
        try {
            double d = b * b - 4 * a * c;
            if (d < 0) {
                throw new ArithmeticException();
            }
            if (a == 0) {
                throw new ArithmeticException();
            }
            if (d == 0) {
                result = (-b) / (2 * a);
                answer.add(result);
            } else {
                result = (-b - Math.sqrt(d)) / (2 * a);
                answer.add(result);
                result = (-b + Math.sqrt(d)) / (2 * a);
                answer.add(result);
            }
        }
        catch (ArithmeticException e) {
            System.out.println(e);
            System.out.println("Немає розв'язків");
        }
        return answer;
    }
}

