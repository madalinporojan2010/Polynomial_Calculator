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

    public GUIController(PolynomialCalculator app) {
        ActionListener buttonListener = e -> {
            p1 = getTextFieldData(app.getPolinom1TextField(), app.getLabel1());
            p2 = getTextFieldData(app.getPolinom2TextField(), app.getLabel2());
            if (p1 != null && p2 != null) {
                p1.getPolinom().sort(Monomial::compareTo);
                p2.getPolinom().sort(Monomial::compareTo);
                if (e.getSource().toString().contains("Addition")) {
                    app.getPolinomRetTextArea().setText(Polynomial.addOperation(p1, p2).toString());
                } else if (e.getSource().toString().contains("Subtraction")) {
                    app.getPolinomRetTextArea().setText(Polynomial.subOperation(p1, p2).toString());
                } else if (e.getSource().toString().contains("Multiplication")) {
                    app.getPolinomRetTextArea().setText(Polynomial.mulOperation(p1, p2).toString());
                } else if (e.getSource().toString().contains("Division")) {
                    try {
                        app.getPolinomRetTextArea().setText("Cat: " + Polynomial.divOperation(p1, p2).get(0) + "\nRest: " + Polynomial.divOperation(p1, p2).get(1));
                    } catch (ArithmeticException f) {
                        JOptionPane.showMessageDialog(null, "Impartire la ZERO!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (e.getSource().toString().contains("Derrivation")) {
                    app.getPolinomRetTextArea().setText("Polinomul 1: " + Polynomial.derivateOperation(p1) + "\nPolinomul 2: " + Polynomial.derivateOperation(p2));
                } else if (e.getSource().toString().contains("Integration")) {
                    app.getPolinomRetTextArea().setText("Polinomul 1: " + Polynomial.integrateOperation(p1) + " + C" + "\nPolinomul 2: " + Polynomial.integrateOperation(p2) + " + C");
                }
            }
        };
        app.getMultiplicationButton().addActionListener(buttonListener);
        app.getSubtractionButton().addActionListener(buttonListener);
        app.getAdditionButton().addActionListener(buttonListener);
        app.getDivisionButton().addActionListener(buttonListener);
        app.getDerrivationButton().addActionListener(buttonListener);
        app.getIntegrationButton().addActionListener(buttonListener);
    }


    public Polynomial getTextFieldData(JTextField t, JLabel l) {
        Polynomial p = null;
        Pattern pattern1 = Pattern.compile("([+-]?[0-9]+[*][x][\\^][+-]?[0-9]+)|([+-]?[x][\\^][+-]?[0-9]+)|([+-]?[0-9]+)");
        Pattern pattern2 = Pattern.compile("([+-]?[0-9]+)");
        Matcher matcher1, matcher2;
        Monomial ret;
        matcher1 = pattern1.matcher(t.getText().replaceAll(" ", "").toLowerCase());

        if (!verifyInput(t))
            JOptionPane.showMessageDialog(null, l.getText() + " gresit!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        else {
            p = new Polynomial(getMaximumExponent(t));
            while (matcher1.find()) {
                try {
                    if (matcher1.group(1) != null) {
                        matcher2 = pattern2.matcher(matcher1.group(1));
                        ret = getResults(matcher2);
                        p.addOperation(new Polynomial(ret));
                    }
                    if (matcher1.group(2) != null) {
                        matcher2 = pattern2.matcher(matcher1.group(2));
                        ret = getResults(matcher2);
                        p.addOperation(new Polynomial(ret));
                    }
                    if (matcher1.group(3) != null) {
                        matcher2 = pattern2.matcher(matcher1.group(3));
                        ret = getResults(matcher2);
                        p.addOperation(new Polynomial(ret));
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

    public Monomial getResults(Matcher matcher) {
        Monomial m = new Monomial(1, 0);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                if (matcher.start() > 0) {
                    m.setDeg(Integer.parseInt(matcher.group(1)));
                } else {
                    m.setCoef(Integer.parseInt(matcher.group(1)));
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
                case '*' -> {
                    if (length + 1 >= input.length() || length - 1 < 0 || input.charAt(length + 1) != 'x' || !Character.isDigit(input.charAt(length - 1))) {
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
                case '0', '1', '2', '3', '4', '5', '6', '8', '9' -> {
                    if (length + 1 < input.length() && input.charAt(length + 1) == 'x') {
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
