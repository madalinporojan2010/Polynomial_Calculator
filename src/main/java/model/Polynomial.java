package model;

import java.util.ArrayList;

public class Polynomial {
    private ArrayList<Monomial> polynomial;
    private int highestDeg;

    public Polynomial(int highestDeg) {
        this.highestDeg = highestDeg;
        polynomial = new ArrayList<>();
        for (int i = 0; i <= highestDeg; i++) {
            polynomial.add(new Monomial(0, i));
        }
    }

    public Polynomial(Monomial m) {
        highestDeg = m.getDeg();
        polynomial = new ArrayList<>();
        this.polynomial.add(m);
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }

    public int getHighestDeg() {
        return highestDeg;
    }

    public void setHighestDeg() {
        int max = 0;
        for (Monomial n : this.polynomial) {
            if (max < n.getDeg() && Math.abs(n.getCoef()) > 0.0001)
                max = n.getDeg();
        }
        this.highestDeg = max;
    }

    public String toString() {
        String s = "";
        for (Monomial n : polynomial) {
            if (n.getCoef() > 0.0001)
                s += " +" + n;
            else if (n.getCoef() < -0.0001)
                s += " " + n;
//            else if (highestDeg == 0){
//                s += "0";
//            }
        }
        return s;
    }

    public static Polynomial addOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg()));

        for (Monomial n : C.getPolynomial()) {
            for (Monomial m : A.getPolynomial()) {
                n.addOperation(m);
            }

            for (Monomial m : B.getPolynomial()) {
                n.addOperation(m);
            }
        }
        C.getPolynomial().sort(Monomial::compareTo);
        return C;
    }

    public void addOperation(Polynomial B) {
        for (Monomial n : this.getPolynomial()) {
            for (Monomial m : B.getPolynomial()) {
                n.addOperation(m);
            }
        }
    }

    public static Polynomial subOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg()));

        for (Monomial n : C.getPolynomial()) {
            for (Monomial m : A.getPolynomial()) {
                n.addOperation(m);
            }

            for (Monomial m : B.getPolynomial()) {
                Monomial aux = new Monomial(-m.getCoef(), m.getDeg());
                n.addOperation(aux);
            }
        }
        C.getPolynomial().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial mulOperation(Polynomial A, Polynomial B) {
        Polynomial C = new Polynomial(A.getHighestDeg() + B.getHighestDeg());

        for (Monomial n : A.getPolynomial()) {
            Polynomial aux = new Polynomial(0);
            for (Monomial m : B.getPolynomial()) {
                Monomial product = new Monomial(n.getCoef(), n.getDeg());
                product.mulOperation(m);
                aux.getPolynomial().add(product);
            }
            C = addOperation(C, aux);
        }
        C.getPolynomial().sort(Monomial::compareTo);
        return C;
    }

    public static ArrayList<Polynomial> divOperation(Polynomial A, Polynomial B) throws ArithmeticException {
        Polynomial P = new Polynomial(Math.max(A.getHighestDeg(), B.getHighestDeg())), Q = new Polynomial(Math.min(A.getHighestDeg(), B.getHighestDeg()));
        if (A.getHighestDeg() > B.getHighestDeg()) {
            P = Polynomial.addOperation(P, A);
            Q = Polynomial.addOperation(Q, B);
        } else {
            P = Polynomial.addOperation(P, B);
            Q = Polynomial.addOperation(Q, A);
        }
        Polynomial C = new Polynomial(P.getHighestDeg() - Q.getHighestDeg());
        if ((Q.getHighestDeg() == 0 && Math.abs(Q.getPolynomial().get(0).getCoef()) > 0.0001) || Q.getHighestDeg() > 0) {
            for (Monomial n : P.getPolynomial()) {
                if (P.getHighestDeg() >= Q.getHighestDeg()) {
                    Monomial catul = new Monomial(n.getCoef(), n.getDeg());
                    catul.getQuotient(Q.getPolynomial().get(0)); // divider
                    Polynomial aux = new Polynomial(catul.getDeg());
                    aux.getPolynomial().add(catul);
                    C = Polynomial.subOperation(C, aux);
                    aux = Polynomial.mulOperation(Q, aux);
                    P.addOperation(aux);
                    P.setHighestDeg();
                }
            }
            ArrayList<Polynomial> ret = new ArrayList<>();
            ret.add(C);
            ret.add(P);
            return ret;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }

    public static Polynomial derivateOperation(Polynomial A) {
        Polynomial C = new Polynomial(Math.max(A.getHighestDeg() - 1, 0));
        C = addOperation(C, A);
        for (Monomial n : C.getPolynomial()) {
            n.derivateOperation();
        }
        C.getPolynomial().sort(Monomial::compareTo);
        return C;
    }

    public static Polynomial integrateOperation(Polynomial A) {
        Polynomial C = new Polynomial(A.getHighestDeg() + 1);
        C = addOperation(C, A);
        for (Monomial n : C.getPolynomial()) {
            n.integrateOperation();
        }
        C.getPolynomial().sort(Monomial::compareTo);
        return C;
    }

}