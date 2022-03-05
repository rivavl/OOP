package oop.leti.numbers;

import java.util.ArrayList;
import java.util.List;

public class CustomRational extends CustomNumber {
    int numerator;
    int denominator;

    public CustomRational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private CustomRational convertToCustomRational(CustomNumber a) {
        if (!(a instanceof CustomRational)) {
            throw new RuntimeException();
        }
        CustomRational cr = (CustomRational) a;
        return cr;
    }

    @Override
    public CustomRational plus(CustomNumber a) {
        CustomRational val = convertToCustomRational(a);
        int num = numerator * val.denominator + val.numerator * this.denominator;
        int denom = denominator * val.denominator;
        return createAndReturnCustomRational(num, denom);
    }

    private CustomRational createAndReturnCustomRational(int num, int denom) {
        List<Integer> list = reduceFraction(num, denom);
        num = list.get(0);
        denom = list.get(1);
        if (denom < 0) {
            num = -num;
            denom = -denom;
        }
        return new CustomRational(num, denom);
    }

    @Override
    public CustomRational minus(CustomNumber a) {
        CustomRational val = convertToCustomRational(a);
        int num = numerator * val.denominator - val.numerator * denominator;
        int denom = denominator * val.denominator;
        return createAndReturnCustomRational(num, denom);
    }

    @Override
    public CustomRational mult(CustomNumber a) {
        CustomRational val = convertToCustomRational(a);
        int num = numerator * val.numerator;
        int denom = denominator * val.denominator;
        return createAndReturnCustomRational(num, denom);
    }

    @Override
    public CustomRational mult(int a) {
        int num = numerator * a;
        return createAndReturnCustomRational(num, denominator);
    }

    @Override
    public CustomRational div(CustomNumber a) {
        CustomRational val = convertToCustomRational(a);
        int num = numerator * val.denominator;
        int denom = denominator * val.numerator;
        if (denom == 0 || num == 0) {
            denom = 1;
        }
        return createAndReturnCustomRational(num, denom);
    }

    @Override
    public boolean biggerZero() {
        return numerator > 0;
    }

    @Override
    public boolean isZero() {
        return numerator == 0;
    }

    @Override
    public CustomRational sqrt() {
        int num = (int) Math.sqrt(numerator);
        int denom = (int) Math.sqrt(denominator);
        return new CustomRational(num, denom);
    }

    @Override
    public Double getValue() {
        return (double) numerator / denominator;
    }

    @Override
    public boolean isTheSame(double exactX) {
        double currentX = (double) this.numerator / this.denominator;
        return (int) exactX * 10000 == (int) currentX * 10000;
    }

    private List<Integer> reduceFraction(int num, int denom) {
        List<Integer> list = new ArrayList<>();
        int gcf;
        while ((gcf = getGCF(num, denom)) != 1) {
            num /= gcf;
            denom /= gcf;
        }
        if (denom < 0) {
            num = -num;
            denom = -denom;
        }
        list.add(num);
        list.add(denom);
        return list;
    }

    private int getGCF(int a, int b) {
        if (a == 0) return 1;
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (denominator == 1) {
            result.append(numerator);
        } else {
            result.append(numerator).append("/").append(denominator);
        }
        return String.valueOf(result);
    }
}
