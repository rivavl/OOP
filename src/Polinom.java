
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

    public boolean getRoots() {
        T discr = (T) b.mult(b).minus(a.mult(c).mult(new CustomInteger(4)));

        if (discr.biggerZero() || discr.isZero()) {
            x1 = (T) this.b.mult(new CustomInteger(-1)).plus(discr.sqrt()).div(this.a.mult(new CustomInteger(2)));
            x2 = (T) this.b.mult(new CustomInteger(-1)).minus(discr.sqrt()).div(this.a.mult(new CustomInteger(2)));
        }

        if (x1 == null || x2 == null) {
            System.out.println("\nУравнение не имеет корней на данном поле чисел\n".toUpperCase());
            return false;
        }
        return true;
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

    public void printRoots() {
        System.out.print("x1 = ");
        System.out.println(x1);
        System.out.print("x2 = ");
        System.out.println(x2);
    }

}
