package model;

import java.util.LinkedList;

public class Polynomial {
    LinkedList<Monomial> polinom;
    private int highestDeg;

    public Polynomial(int highestDeg) {
        this.highestDeg = highestDeg;
        polinom = new LinkedList<>();
    }

    public LinkedList<Monomial> getPolinom() {
        return polinom;
    }

    public void setPolinom(LinkedList<Monomial> polinom) {
        this.polinom = polinom;
    }

    public int getHighestDeg() {
        return highestDeg;
    }

    public void setHighestDeg(int highestDeg) {
        this.highestDeg = highestDeg;
    }
}
