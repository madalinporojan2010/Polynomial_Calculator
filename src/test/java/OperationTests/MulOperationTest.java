package OperationTests;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MulOperationTest {

    public MulOperationTest(){
        System.out.println("Initializare Test Inmultire!");
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    public void mulOperationTest(Polynomial A, Polynomial B, Polynomial expectedRes) {
        boolean isCorrect = true;
        Polynomial C = Polynomial.mulOperation(A, B);
        for (Monomial n : C.getPolinom()) {
            for (Monomial m : expectedRes.getPolinom()) {
                if (n.getDeg() == m.getDeg() && n.getCoef() != m.getCoef()) {
//                    System.out.println(n.getCoef() + " " + m.getCoef());
//                    System.out.println(n.getDeg() + " " + m.getDeg());
                    isCorrect = false;
                }
            }
        }
        assertTrue(isCorrect);
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        Polynomial p1, p2, pres1, pres2;
        p1 = new Polynomial(5); // 3x^5+6
        p1.getPolinom().set(5, new Monomial(3, 5));
        p1.getPolinom().set(0, new Monomial(6, 0));

        p2 = new Polynomial(1); //10*x^1+6
        p2.getPolinom().set(1, new Monomial(10, 1));
        p2.getPolinom().set(0, new Monomial(6, 0));

        pres1 = new Polynomial(6); // 30*x^6+18*x^5+60*x^1+36
        pres1.getPolinom().set(6, new Monomial(30, 6));
        pres1.getPolinom().set(5, new Monomial(18, 5));
        pres1.getPolinom().set(1, new Monomial(60, 1));
        pres1.getPolinom().set(0, new Monomial(36, 0));

//        pres2 = new Polynomial(5);
//        pres2.getPolinom().set(5, new Monomial(6, 5));
//        pres2.getPolinom().set(0, new Monomial(12, 0));
        //1 polinom de la mine 1 de la el
        argumentsList.add(Arguments.of(p1, p2, pres1));
//        argumentsList.add(Arguments.of(p2, p1, pres1));
//        argumentsList.add(Arguments.of(p1, p1, pres2));
        //share la proiect pt un overview
        return argumentsList;
    }
}
