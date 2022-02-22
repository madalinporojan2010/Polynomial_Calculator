package model;

import java.text.DecimalFormat;

public class Monomial implements Comparable<Monomial>{
    private float coef;
    private int deg;

    public Monomial(float coef, int deg) {
        this.coef = coef;
        this.deg = deg;
    }

    public Monomial() {
        this(0, 0);
    }

    public float getCoef() {
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
        DecimalFormat df = new DecimalFormat("0.000");
        String s = "";
        if (Math.abs(coef) > 0.0001) {
            if (deg != 0) {
                s += df.format(coef) + "*X^" + deg;
            } else {
                s += df.format(coef);
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

    public void getQuotient(Monomial m) {
        this.deg = this.deg - m.getDeg();
        this.coef = -this.coef / m.getCoef();
    }

    public void derivateOperation() {
        this.coef *= this.deg;
        if (this.deg > 0)
            this.deg--;
    }

    public void integrateOperation() {
        this.deg++;
        this.coef /= this.deg;
    }

    @Override
    public int compareTo(Monomial o) {
        return -Integer.compare(this.deg, o.getDeg());
    }
}
