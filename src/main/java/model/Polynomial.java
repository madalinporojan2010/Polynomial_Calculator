package model;

import controller.Main;

import java.util.ArrayList;
import java.util.LinkedList;

public class Polynomial extends Monomial {
    private ArrayList<Monomial> polinom;
    private int highestDeg;

    public Polynomial(int highestDeg) {
        this.highestDeg = highestDeg;
        polinom = new ArrayList<>();
        for (int i = 0; i <= highestDeg; i++) {
            polinom.add(new Monomial(0, i));
        }
    }

    public ArrayList<Monomial> getPolinom() {
        return polinom;
    }

    public void setPolinom(ArrayList<Monomial> polinom) {
        this.polinom = polinom;
    }

    public int getHighestDeg() {
        return highestDeg;
    }

    public void setHighestDeg(int highestDeg) {
        this.highestDeg = highestDeg;
    }

    public String toString() {
        String s = "";
        for (Monomial n : polinom) {
            if (n.getCoef() > 0)
                s += " +" + n;
            else if(n.getCoef() < 0)
                s += " " + n;
        }
        s = s.substring(2);
        return s;
    }

    public static Polynomial addOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg()));

        for(Monomial n : C.getPolinom()){
            for(Monomial m : A.getPolinom()){
                n.addOperation(m);
            }

            for(Monomial m : B.getPolinom()){
                n.addOperation(m);
            }
        }
        return C;
    }
}