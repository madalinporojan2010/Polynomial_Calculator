package model;

public class Monomial {
    private int coef;
    private int deg;

    public Monomial(int coef, int deg) {
        this.coef = coef;
        this.deg = deg;
    }

    public Monomial() {
        this(0, 0);
    }

    public int getCoef() {
        return coef;
    }

    public int getDeg() {
        return deg;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public String toString() {
        String s = "";
        if (coef != 0) {
            if (deg != 0) {
                s += coef + "*X^" + deg;
            } else {
                s += coef;
            }
        }
        return s;
    }

    public void addOperation(Monomial m) throws Error {
        if (this.deg == m.getDeg()) {
            this.coef += m.getCoef();
        }
    }

    public void mulOperation(Monomial m) {
        this.deg += m.getDeg();
        this.coef *= m.getCoef();
    }

    public void derivateOperation() {
        this.coef *= this.deg;
        if (this.deg > 0)
            this.deg--;
    }
}
