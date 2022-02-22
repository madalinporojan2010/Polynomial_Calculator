package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialCalculator {
    private JButton multiplicationButton;
    private JButton subtractionButton;
    private JButton additionButton;
    private JButton divisionButton;
    private JTextField polinom1TextField;
    private JTextField polinom2TextField;
    private JTextField polinomRetTextField;
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

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public JTextField getPolinom1TextField() {
        return polinom1TextField;
    }

    public JTextField getPolinom2TextField() {
        return polinom2TextField;
    }

    public JTextField getPolinomRetTextField() {
        return polinomRetTextField;
    }

    public JButton getDerrivationButton() {
        return derrivationButton;
    }

    public JButton getIntegrationButton() {
        return integrationButton;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JFrame getFrame() {
        return frame;
    }

    private JFrame frame;

    public PolynomialCalculator(){
        frame = new JFrame("UTCN Assignment #1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
