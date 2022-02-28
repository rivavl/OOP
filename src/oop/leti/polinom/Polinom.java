package oop.leti.polinom;

import oop.leti.numbers.CustomInteger;
import oop.leti.numbers.CustomNumber;

public class Polinom<T extends CustomNumber> {
    private T a;
    private T b;
    private T c;

    private T x1;
    private T x2;

    private T result;

    public Polinom(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Roots<T> getRoots() throws NoRootsException {
        if (x1 == null || x2 == null) {
            double D = 0;
            double exactX1 = 0;
            double exactX2 = 0;

            T discr = (T) b.mult(b).minus(a.mult(c).mult(new CustomInteger(4)));


            if (discr.biggerZero() || discr.isZero()) {
                x1 = (T) this.b.mult(new CustomInteger(-1)).plus(discr.sqrt()).div(this.a.mult(new CustomInteger(2)));
                x2 = (T) this.b.mult(new CustomInteger(-1)).minus(discr.sqrt()).div(this.a.mult(new CustomInteger(2)));
            }

            if (x1 == null || x2 == null) {
                System.out.println("\nУравнение не имеет вещественных корней\n".toUpperCase());
                throw new NoRootsException();
            }

            if (a instanceof CustomInteger) {
                D = (int) b.getValue() * (int) b.getValue() - 4 * (int) a.getValue() * (int) c.getValue();
                exactX1 = ((-1) * (int) b.getValue() + Math.sqrt(D)) / (2 * (int) a.getValue());
                exactX2 = ((-1) * (int) b.getValue() - Math.sqrt(D)) / (2 * (int) a.getValue());
                System.out.println("(exact x1 = " + exactX1 + ")");
                System.out.println("(exact x2 = " + exactX2 + ")");

                if ((double) (int) x1.getValue() != exactX1
                        || (double) (int) x2.getValue() != exactX2) {
                    System.out.println("Значения корней округлены до целых чисел, т.к. выбран целочисленный тип");
                }
            }

        }

        return new Roots<T>(x1, x2);
    }

    public void printEquation() {
        String res = "" + this.a + "*x^2";

        if (this.b.biggerZero() || this.b.isZero()) {
            res = res + "+" + this.b + "*x";
        } else {
            res = res + this.b + "*x";
        }

        if (this.c.biggerZero() || this.c.isZero()) {
            res = res + "+" + this.c + "*x";
        } else {
            res = res + this.c + "*x";
        }

        System.out.println(res);
    }

    public void printCanonical() {
        if (this.x1 == null || this.x2 == null) {
            System.out.println("\nКорни еще не вычислены\n".toUpperCase());
        } else {
            String res = "" + this.a + "(x";
            if (this.x1.biggerZero()) {
                res = res + "-" + this.x1;
            } else {
                res = res + "+" + this.x1.mult(new CustomInteger(-1));
            }

            res = res + ")(x";

            if (this.x2.biggerZero()) {
                res = res + "-" + this.x2;
            } else {
                res = res + "+" + this.x2.mult(new CustomInteger(-1));
            }
            res = res + ")";
            System.out.println(res);
        }
    }

    public T solveWithX(CustomNumber x) {
        result = (T) this.a.mult(x).mult(x).plus(this.b.mult(x)).plus(this.c);
        return result;
    }

    public class Roots<T extends CustomNumber> {
        private T x1;
        private T x2;

        public Roots(T x1, T x2) {
            this.x1 = x1;
            this.x2 = x2;
        }

        public T getX1() {
            return x1;
        }

        public T getX2() {
            return x2;
        }
    }
}
