package controller;

import model.Monomial;
import model.Polynomial;

public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(3);
        Polynomial p2 = new Polynomial(5);

        Monomial n1 = new Monomial(3, 0);
        Monomial n2 = new Monomial(-1, 1);
        Monomial n3 = new Monomial(4, 2);
        Monomial n4 = new Monomial(6, 3);

        Monomial m1 = new Monomial(3, 0);
        Monomial m2 = new Monomial(1, 1);
        Monomial m3 = new Monomial(4, 2);
        Monomial m4 = new Monomial(2, 3);
        Monomial m5 = new Monomial(-4, 4);
        Monomial m6 = new Monomial(-2, 5);

        p1.getPolinom().add(n1);
        p1.getPolinom().add(n2);
        p1.getPolinom().add(n3);
        p1.getPolinom().add(n4);

        p2.getPolinom().add(m1);
        p2.getPolinom().add(m2);
        p2.getPolinom().add(m3);
        p2.getPolinom().add(m4);
        p2.getPolinom().add(m5);
        p2.getPolinom().add(m6);

       System.out.println(Polynomial.addOperation(p1, p2));
       // System.out.println(p1);
    }
}
