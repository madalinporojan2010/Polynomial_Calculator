package view;

import javax.swing.*;

public class PolynomialCalculator {
    private JButton multiplicationButton;
    private JButton subtractionButton;
    private JButton additionButton;
    private JButton divisionButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton derrivationButton;
    private JButton integrationButton;
    private JPanel panel1;
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
