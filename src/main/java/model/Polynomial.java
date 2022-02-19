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
            else if (n.getCoef() < 0)
                s += " " + n;
        }
        s = s.substring(1);
        return s;
    }

    public static Polynomial addOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg()));

        for (Monomial n : C.getPolinom()) {
            for (Monomial m : A.getPolinom()) {
                n.addOperation(m);
            }

            for (Monomial m : B.getPolinom()) {
                n.addOperation(m);
            }
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial subOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg()));

        for (Monomial n : C.getPolinom()) {
            for (Monomial m : A.getPolinom()) {
                n.addOperation(m);
            }

            for (Monomial m : B.getPolinom()) {
                Monomial aux = new Monomial(-m.getCoef(), m.getDeg());
                n.addOperation(aux);
            }
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial mulOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(A.getHighestDeg() + B.getHighestDeg());

        for (Monomial n : A.getPolinom()) {
            Polynomial aux = new Polynomial(0);
            for (Monomial m : B.getPolinom()) {
                Monomial product = new Monomial(n.getCoef(), n.getDeg());
                product.mulOperation(m);
                aux.getPolinom().add(product);
            }
            C = addOperation(C, aux);
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial divOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(A.getHighestDeg() + B.getHighestDeg());
        Polynomial auxA = new Polynomial(A.getHighestDeg());
        Polynomial auxB = new Polynomial(B.getHighestDeg());


        return C;
    }

    public static Polynomial derivateOperation(Polynomial A) {
        Polynomial C = new Polynomial(A.getHighestDeg() - 1);
        C = addOperation(C, A);
        for (Monomial n : C.getPolinom()) {
            n.derivateOperation();
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial integrateOperation(Polynomial A) {
        Polynomial C = new Polynomial(A.getHighestDeg() - 1);
        C = addOperation(C, A);
        for (Monomial n : C.getPolinom()) {
            n.integrateOperation();
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

}