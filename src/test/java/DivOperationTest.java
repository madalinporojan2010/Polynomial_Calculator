import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DivOperationTest {
    @ParameterizedTest
    @MethodSource("provideInput")
    public void divOperationTest(Polynomial A, Polynomial B, ArrayList<Polynomial> expectedRes) {
        boolean isCorrect = true;
        ArrayList<Polynomial> C = Polynomial.divOperation(A, B);
        for (Monomial n : C.get(0).getPolinom()) {
            for (Monomial m : expectedRes.get(0).getPolinom()) {
                if (n.getDeg() == m.getDeg() && Math.abs(n.getCoef() - m.getCoef()) > 0.0001) {
                    System.out.println(n.getCoef() + " " + m.getCoef());
                    System.out.println(n.getDeg() + " " + m.getDeg());
                    isCorrect = false;
                }
            }
        }
        System.out.println(C.get(0));
        System.out.println(C.get(1));
        for (Monomial n : C.get(1).getPolinom()) {
            for (Monomial m : expectedRes.get(1).getPolinom()) {
                if (n.getDeg() == m.getDeg() && Math.abs(n.getCoef() - m.getCoef()) > 0.0001) {
                    System.out.println(n.getCoef() + " " + m.getCoef());
                    System.out.println(n.getDeg() + " " + m.getDeg());
                    isCorrect = false;
                }
            }
        }
        assertTrue(isCorrect);
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        Polynomial p1, p2;
        ArrayList<Polynomial> pres1;
        p1 = new Polynomial(5); // 3x^3+6*x^1
        p1.getPolinom().set(5, new Monomial(3, 3));
        p1.getPolinom().set(0, new Monomial(6, 1));

        p2 = new Polynomial(1); //10*x^2+6
        p2.getPolinom().set(1, new Monomial(10, 2));
        p2.getPolinom().set(0, new Monomial(6, 0));

        pres1 = new ArrayList<>(); // CAT:0.3*x  REST:21/5*x

        pres1.add(new Polynomial(1));
        pres1.add(new Polynomial(1));
        pres1.get(0).getPolinom().set(1, new Monomial(3f/10, 1));
        pres1.get(1).getPolinom().set(1, new Monomial(21f/5, 1));

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