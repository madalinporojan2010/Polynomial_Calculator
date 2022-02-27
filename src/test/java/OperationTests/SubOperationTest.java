package OperationTests;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubOperationTest {

    public SubOperationTest() {
        System.out.println("Initializare Test Scadere!");
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    public void addOperationTest(Polynomial A, Polynomial B, Polynomial expectedRes) {
        boolean isCorrect = true;
        Polynomial C = Polynomial.subOperation(A, B);
        for (Monomial n : C.getPolinom()) {
            for (Monomial m : expectedRes.getPolinom()) {
                if (n.getDeg() == m.getDeg() && n.getCoef() != m.getCoef()) {
                    isCorrect = false;
                }
            }
        }
        assertTrue(isCorrect);
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        Polynomial p1, p2, pres1;
        p1 = new Polynomial(5);
        p1.getPolinom().set(5, new Monomial(3, 5));
        p1.getPolinom().set(0, new Monomial(6, 0));

        p2 = new Polynomial(1);
        p2.getPolinom().set(1, new Monomial(10, 1));
        p2.getPolinom().set(0, new Monomial(6, 0));

        pres1 = new Polynomial(5);
        pres1.getPolinom().set(5, new Monomial(3, 5));
        pres1.getPolinom().set(1, new Monomial(-10, 1));
        pres1.getPolinom().set(0, new Monomial(0, 0));

        argumentsList.add(Arguments.of(p1, p2, pres1));
        return argumentsList;
    }
}
