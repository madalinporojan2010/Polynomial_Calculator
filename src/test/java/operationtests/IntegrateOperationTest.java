package operationtests;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrateOperationTest {

    public IntegrateOperationTest() {
        System.out.println("Initializare Test Integrare!");
    }


    @ParameterizedTest
    @MethodSource("provideInput")
    public void integrateOperationTest(Polynomial A, Polynomial expectedRes) {
        boolean isCorrect = true;
        Polynomial C = Polynomial.integrateOperation(A);
        for (Monomial n : C.getPolynomial()) {
            for (Monomial m : expectedRes.getPolynomial()) {
                if (n.getDeg() == m.getDeg() && Math.abs(n.getCoef() - m.getCoef()) > 0.0001) {
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
        p1.getPolynomial().set(5, new Monomial(3, 5));
        p1.getPolynomial().set(0, new Monomial(6, 0));

        pres1 = new Polynomial(6);
        pres1.getPolynomial().set(6, new Monomial(3f / 6, 6));
        pres1.getPolynomial().set(1, new Monomial(6, 1));

        argumentsList.add(Arguments.of(p1, pres1));
        return argumentsList;
    }
}
