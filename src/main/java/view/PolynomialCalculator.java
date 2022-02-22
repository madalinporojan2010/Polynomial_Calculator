package view;

import javax.swing.*;

public class PolynomialCalculator {
    private JButton multiplicationButton;
    private JButton subtractionButton;
    private JButton additionButton;
    private JButton divisionButton;
    private JTextField polinom1TextField;
    private JTextField polinom2TextField;
    private JTextArea polinomRetTextArea;
    private JButton derrivationButton;
    private JButton integrationButton;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel resultLabel;

    public JButton getMultiplicationButton() {
        return multiplicationButton;
    }

    public JButton getSubtractionButton() {
        return subtractionButton;
    }

    public JButton getAdditionButton() {
        return additionButton;
    }

    public JButton getDivisionButton() {
        return divisionButton;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public JLabel getLabel2() {
        return label2;
    }

    public JTextField getPolinom1TextField() {
        return polinom1TextField;
    }

    public JTextField getPolinom2TextField() {
        return polinom2TextField;
    }

    public JTextArea getPolinomRetTextArea() {
        return polinomRetTextArea;
    }

    public JButton getDerrivationButton() {
        return derrivationButton;
    }

    public JButton getIntegrationButton() {
        return integrationButton;
    }

    private JFrame frame;

    public PolynomialCalculator() {
        frame = new JFrame("UTCN Assignment #1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
