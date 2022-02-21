package controller;

import model.Polynomial;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.RegexConversion;
import view.PolynomialCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
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

            getTextFieldData(app.getPolinom1TextField());
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


    public String getTextFieldData(JTextField t) {
//        String s = t.getText();
//        p1 = new Polynomial(0);
//        p2 = new Polynomial(0);
        Pattern pattern = Pattern.compile("([+-]?[0-9]+)([+-]?[0-9]+)?|([+-]?[0-9]+)");
        Matcher matcher, strings;
        matcher = pattern.matcher(t.getText());
        int firstIndex = 0;
        int lastIndex = 0;
        int noGroups = 0;
        while (matcher.find()) {
           System.out.println(matcher.group(1) + matcher.group(2));
        }
        System.out.println(matcher);
        return "";
    }

    public void setTextFieldData() {

    }
}
