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
        frame = new JFrame("UTCN aplicatie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
