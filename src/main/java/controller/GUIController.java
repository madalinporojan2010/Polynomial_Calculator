package controller;

import model.Monomial;
import model.Polynomial;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;
import view.PolynomialCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.jar.JarInputStream;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class GUIController {
    private int choice;
    private PolynomialCalculator app;
    private String polynomialOutput;
    private ActionListener buttonListener;
    private Polynomial p1;
    private Polynomial p2;

    public GUIController() {
        app = new PolynomialCalculator();
        buttonListener = e -> {
            p1 = getTextFieldData(app.getPolinom1TextField(), app.getLabel1());
            p2 = getTextFieldData(app.getPolinom2TextField(), app.getLabel2());
            if (e.getSource().toString().contains("Addition")) {
            } else if (e.getSource().toString().contains("Subtraction")) {

            } else if (e.getSource().toString().contains("Multiplication")) {

            } else if (e.getSource().toString().contains("Division")) {

            } else if (e.getSource().toString().contains("Derrivation")) {

            } else if (e.getSource().toString().contains("Integration")) {

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
                        //// polinoamele cu grad mai mic nu sunt adaugate
                        p.addOperation(new Polynomial(ret));
                    }
                    if (matcher1.group(3) != null) {
                        matcher2 = pattern2.matcher(matcher1.group(3));
                        ret = getResults(matcher2);

                        p.addOperation(new Polynomial(ret));
                    }
                } catch (Exception e) {
                    System.out.println("Eroare convertire in int + " + e.getMessage());
                }
                p.getPolinom().sort(Monomial::compareTo);
            }
        }
        System.out.println(p);
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

    public void setTextFieldData() {

    }

    public boolean verifyInput(JTextField t) {
        boolean isCorrect = true;
        String input = t.getText().replaceAll(" ", "").toLowerCase();
        int length = 0;
        while (length < input.length()) {
            switch (input.charAt(length)) {
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
                    if (length + 1 >= input.length() || length - 1 < 0 || (!Character.isDigit(input.charAt(length + 1)) && input.charAt(length + 1) != '+' && input.charAt(length + 1) != '-') || input.charAt(length - 1) != 'x') {
                        isCorrect = false;
                    }
                }
                case 'x' -> {
                    if (length + 1 >= input.length() || input.charAt(length + 1) != '^') {
                        isCorrect = false;
                    }
                }
                case '0','1','2','3','4','5','6','8','9' -> {
                    if (length + 1 < input.length() && input.charAt(length + 1) == 'x'){
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
