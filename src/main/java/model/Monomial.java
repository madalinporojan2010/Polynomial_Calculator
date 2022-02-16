package model;

public class Monomial {
    private int coef;
    private int power;

    public Monomial(int coef, int power){
        this.coef = coef;
        this.power = power;
    }

    public int getCoef() {
        return coef;
    }

    public int getPower() {
        return power;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
