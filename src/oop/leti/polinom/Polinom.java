package oop.leti.polinom;

import oop.leti.numbers.CustomNumber;

public class Polinom<T extends CustomNumber> {
    private T a;
    private T b;
    private T c;

    private T x1;
    private T x2;

    public Polinom(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Roots<T> getRoots() throws NoRootsException {
        if (x1 == null || x2 == null) {
            double exactD = 0;
            double exactX1 = 0;
            double exactX2 = 0;

            T b2 = (T) b.mult(b);
            T ac4 = (T) a.mult(c).mult(4);

            T D = (T) b2.minus(ac4);


            if (D.biggerZero() || D.isZero()) {
                CustomNumber num = b.mult(-1).plus(D.sqrt());
                CustomNumber denom = a.mult(2);
                x1 = (T) num.div(denom);
                num = b.mult(-1).minus(D.sqrt());
                x2 = (T) num.div(denom);
            }

            if (x1 == null || x2 == null) {
                throw new NoRootsException();
            }

            exactD = (double) b.getValue() * (double) b.getValue() - 4 * (double) a.getValue() * (double) c.getValue();
            exactX1 = ((-1) * (double) b.getValue() + Math.sqrt(exactD)) / (2 * (double) a.getValue());
            exactX2 = ((-1) * (double) b.getValue() - Math.sqrt(exactD)) / (2 * (double) a.getValue());
            System.out.println("(exact x1 = " + exactX1 + ")");
            System.out.println("(exact x2 = " + exactX2 + ")");

            if (!x1.isTheSame(exactX1)) {
                System.out.println("x1 округлен, т.к. не принадлежат заданному множеству");
            } else {
                System.out.println("х1 принадлежит заданному множеству");
            }

            if (!x2.isTheSame(exactX2)) {
                System.out.println("x2 округлен, т.к. не принадлежит заданному множеству");
            } else {
                System.out.println("х2 принадлежит заданному множеству");
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
        if (x1 == null || x2 == null) {
            System.out.println("\nКорни еще не вычислены\n".toUpperCase());
        } else {
            StringBuilder res = new StringBuilder();
            res.append(a).append("(x");
            if (x1.biggerZero()) {
                res.append("-").append(x1);
            } else {
                res.append("+");
                res.append(x1.mult(-1));
            }

            res.append(")(x");

            if (x2.biggerZero()) {
                res.append("-").append(x2);
            } else {
                res.append("+").append(x2.mult(-1));
            }
            res.append(")");
            System.out.println(res);
        }
    }

    public T solveWithX(CustomNumber x) {
        return (T) this.a.mult(x).mult(x).plus(this.b.mult(x)).plus(this.c);
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
