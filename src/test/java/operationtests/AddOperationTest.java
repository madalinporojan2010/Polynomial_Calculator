package operationtests;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


//                    System.out.println(n.getCoef() + " " + m.getCoef());
//                    System.out.println(n.getDeg() + " " + m.getDeg());

public class AddOperationTest {
    public AddOperationTest() {
        System.out.println("Initializare Test Adunare!");
    }

    @ParameterizedTest
    @MethodSource("provideInput")
    public void addOperationTest(Polynomial A, Polynomial B, Polynomial expectedRes) {
        boolean isCorrect = true;
        Polynomial C = Polynomial.addOperation(A, B);
        for (Monomial n : C.getPolynomial()) {
            for (Monomial m : expectedRes.getPolynomial()) {
                if (n.getDeg() == m.getDeg() && n.getCoef() != m.getCoef()) {
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
        p1.getPolynomial().set(5, new Monomial(3, 5));
        p1.getPolynomial().set(0, new Monomial(6, 0));

        p2 = new Polynomial(1); //10*x^1+6
        p2.getPolynomial().set(1, new Monomial(10, 1));
        p2.getPolynomial().set(0, new Monomial(6, 0));

        pres1 = new Polynomial(5);
        pres1.getPolynomial().set(5, new Monomial(3, 5));
        pres1.getPolynomial().set(1, new Monomial(10, 1));
        pres1.getPolynomial().set(0, new Monomial(12, 0));

        argumentsList.add(Arguments.of(p1, p2, pres1));
        return argumentsList;
    }
}
