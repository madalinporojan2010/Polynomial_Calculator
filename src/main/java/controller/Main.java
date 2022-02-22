package controller;

import view.PolynomialCalculator;


public class Main {
    public static void main(String[] args) {
        PolynomialCalculator app = new PolynomialCalculator();
        GUIController controller = new GUIController(app);
    }
}
