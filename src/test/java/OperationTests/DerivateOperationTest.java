package OperationTests;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DerivateOperationTest {

    public DerivateOperationTest(){
        System.out.println("Initializare Test Derivare!");
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    public void derivateOperationTest(Polynomial A, Polynomial expectedRes) {
        boolean isCorrect = true;
        Polynomial C = Polynomial.derivateOperation(A);
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
        Polynomial p1, pres1;
        p1 = new Polynomial(5); // 3x^5+6
        p1.getPolinom().set(5, new Monomial(3, 5));
        p1.getPolinom().set(0, new Monomial(6, 0));

        pres1 = new Polynomial(4);
        pres1.getPolinom().set(4, new Monomial(15, 4));

        argumentsList.add(Arguments.of(p1, pres1));
        return argumentsList;
    }
}
