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

    public void setHighestDeg() {
        int max = 0;
        for (Monomial n : this.polinom) {
            if (max < n.getDeg() && n.getCoef() != 0)
                max = n.getDeg();
        }
        this.highestDeg = max;
    }

    public String toString() {
        String s = "";
        for (Monomial n : polinom) {
            if (n.getCoef() > 0)
                s += " +" + n;
            else if (n.getCoef() < 0)
                s += " " + n;
        }
        s = s.substring(0);
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

    public static Polynomial[] divOperation(Polynomial A, Polynomial B) throws ArithmeticException {
        if (Math.min(A.getHighestDeg(), B.getHighestDeg()) != 0) {
            Polynomial[] ret = new Polynomial[2];
            Polynomial P = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg())), Q = new Polynomial(Math.min(A.getHighestDeg(), B.getHighestDeg()));

            if (A.getHighestDeg() > B.getHighestDeg()) {
                P = Polynomial.addOperation(P, A);
                Q = Polynomial.addOperation(Q, B);
            } else {
                P = Polynomial.addOperation(P, B);
                Q = Polynomial.addOperation(Q, A);
            }
            Polynomial C = new Polynomial(Math.max(P.getHighestDeg() - Q.getHighestDeg(), 0));

            for (Monomial n : P.getPolinom()) {
                if (P.getHighestDeg() >= Q.getHighestDeg()) {
                    Monomial impartitor = Q.getPolinom().get(0);
                    Monomial catul = new Monomial(n.getCoef(), n.getDeg());

                    catul.getQuotient(impartitor);

                    Polynomial aux = new Polynomial(catul.getDeg());
                    aux.getPolinom().add(catul);
                    C = Polynomial.subOperation(C, aux);

                    aux = Polynomial.mulOperation(Q, aux);
                    for (Monomial m : P.getPolinom()) {
                        for (Monomial q : aux.getPolinom())
                            m.addOperation(q);
                    }
                    P.getPolinom().get(0).setCoef(0);
                    P.getPolinom().get(0).setDeg(0);
                    P.setHighestDeg();
                }
            }
            ret[0] = C;
            ret[1] = P;
            return ret;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }

    public static Polynomial derivateOperation(Polynomial A) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg() - 1, 0));
        C = addOperation(C, A);
        for (Monomial n : C.getPolinom()) {
            n.derivateOperation();
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial integrateOperation(Polynomial A) {
        Polynomial C = new Polynomial(A.getHighestDeg() + 1);
        C = addOperation(C, A);
        for (Monomial n : C.getPolinom()) {
            n.integrateOperation();
        }
        C.getPolinom().sort(Monomial::compareTo);
        return C;
    }

}