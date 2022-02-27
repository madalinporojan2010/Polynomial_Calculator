package controller;

import model.Monomial;
import model.Polynomial;
import view.PolynomialCalculator;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class GUIController {
    private Polynomial p1;
    private Polynomial p2;
    PolynomialCalculator app;

    public GUIController(PolynomialCalculator app) {
        this.app = app;
        ActionListener buttonListener = e -> {
            p1 = getTextFieldData(app.getPolinom1TextField(), app.getLabel1());
            p2 = getTextFieldData(app.getPolinom2TextField(), app.getLabel2());
            if (p1 != null && p2 != null) {
                p1.getPolinom().sort(Monomial::compareTo);
                p2.getPolinom().sort(Monomial::compareTo);
                connectButtons(((JButton) e.getSource()).getText());
            }
        };
        app.getMultiplicationButton().addActionListener(buttonListener);
        app.getSubtractionButton().addActionListener(buttonListener);
        app.getAdditionButton().addActionListener(buttonListener);
        app.getDivisionButton().addActionListener(buttonListener);
        app.getDerivationButton().addActionListener(buttonListener);
        app.getIntegrationButton().addActionListener(buttonListener);
    }

    public void connectButtons(String s) {
        switch (s) {
            case "Addition" -> {
                app.getPolinomRetTextArea().setText(Polynomial.addOperation(p1, p2).toString());
            }
            case "Subtraction" -> {
                app.getPolinomRetTextArea().setText(Polynomial.subOperation(p1, p2).toString());
            }
            case "Multiplication" -> {
                app.getPolinomRetTextArea().setText(Polynomial.mulOperation(p1, p2).toString());
            }
            case "Division" -> {
                try {
                    app.getPolinomRetTextArea().setText("Cat: " + Polynomial.divOperation(p1, p2).get(0) + "\nRest: " + Polynomial.divOperation(p1, p2).get(1));
                } catch (ArithmeticException f) {
                    JOptionPane.showMessageDialog(null, "Impartire la ZERO!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Derivation" -> {
                app.getPolinomRetTextArea().setText("Polinomul 1: " + Polynomial.derivateOperation(p1) + "\nPolinomul 2: " + Polynomial.derivateOperation(p2));
            }
            case "Integration" -> {
                app.getPolinomRetTextArea().setText("Polinomul 1: " + Polynomial.integrateOperation(p1) + " + C" + "\nPolinomul 2: " + Polynomial.integrateOperation(p2) + " + C");
            }
        }
    }

    public Polynomial getTextFieldData(JTextField t, JLabel l) {
        Polynomial p = null;
        if (!verifyInput(t))
            JOptionPane.showMessageDialog(null, l.getText() + " gresit!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        else {
            Pattern pattern1 = Pattern.compile("([+-]?[^-+]+)");
            Pattern pattern2 = Pattern.compile("([+-]?[0-9]+)");
            Matcher matcher1 = pattern1.matcher(t.getText().replaceAll(" ", "").toLowerCase());
            p = new Polynomial(getMaximumExponent(t));
            while (matcher1.find()) {
                try {
                    if (matcher1.group(1) != null) {
                        p.addOperation(new Polynomial(getResults(matcher1, pattern2)));
                    }
                } catch (Exception e) {
                    System.out.println("Eroare convertire in int + " + e.getMessage());
                    JOptionPane.showMessageDialog(null, l.getText() + " overflow/underflow!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return p;
    }

    public int getMaximumExponent(JTextField t) {
        int max = 0;
        Pattern p = Pattern.compile("([\\^][+-]?[0-9]+)");
        Matcher matcher = p.matcher(t.getText().replaceAll(" ", "").toLowerCase());
        while (matcher.find()) {
            String s = matcher.group(1);
            if (max < Integer.parseInt(s.substring(1))) {
                max = Integer.parseInt(s.substring(1));
            }
        }
        return max;
    }

    public Monomial getResults(Matcher matcher1, Pattern p) {
        Matcher matcher2 = p.matcher(matcher1.group(1));
        Monomial m = new Monomial(1, 0);
        while (matcher2.find()) {
            if (matcher2.group(1) != null) {
                if (matcher2.start() > 0) {
                    m.setDeg(Integer.parseInt(matcher2.group(1)));
                } else {
                    m.setCoef(Integer.parseInt(matcher2.group(1)));
                }
            }
        }
        return m;
    }

    public boolean verifyInput(JTextField t) {
        boolean isCorrect = true;
        String input = t.getText().replaceAll(" ", "").toLowerCase();
        int length = 0;
        for (char ch : input.toCharArray()) {
            switch (ch) {
                case '+', '-' -> {
                    if (length + 1 >= input.length() || (!Character.isDigit(input.charAt(length + 1)) && input.charAt(length + 1) != 'x')) {
                        isCorrect = false;
                    }
                }
                case '^' -> {
                    if (length + 1 >= input.length() || length - 1 < 0 || (!Character.isDigit(input.charAt(length + 1)) && input.charAt(length + 1) != '+') || input.charAt(length - 1) != 'x') {
                        isCorrect = false;
                    }
                }
                case 'x' -> {
                    if (length + 1 >= input.length() || input.charAt(length + 1) != '^') {
                        isCorrect = false;
                    }
                }
                default -> {
                    if (!Character.isDigit(input.charAt(length)))
                        isCorrect = false;
                }
            }
            length++;
        }
        return isCorrect && input.length() > 0;
    }
}
