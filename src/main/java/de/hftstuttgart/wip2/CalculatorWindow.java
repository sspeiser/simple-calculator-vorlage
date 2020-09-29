package de.hftstuttgart.wip2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * CalculatorWindow
 */
public class CalculatorWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Calculator calculator;


    public CalculatorWindow(Calculator calculator) {
        super("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.calculator = calculator;

        initComponents();
        pack();
    }

    private void initComponents() {
        this.setSize(400, 300);
        final JLabel firstNumberLabel =new JLabel("1st Number:");
        final JLabel secondNumberLabel =new JLabel("2nd Number:");
        final JLabel resultLabel =new JLabel("Result:");

        final JTextField firstNumberField = new JTextField(10);
        firstNumberField.setName("firstNumber");
        firstNumberField.setText("0");
        
        final JTextField secondNumberField = new JTextField(10);
        secondNumberField.setName("secondNumber");
        secondNumberField.setText("0");

        final JTextField resultField = new JTextField(10);
        resultField.setEditable(false);
        resultField.setText("0");
        resultField.setName("result");

        final JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            int op1 = Integer.parseInt(firstNumberField.getText());
            int op2 = Integer.parseInt(secondNumberField.getText());
            resultField.setText(Integer.toString(calculator.add(op1,op2)));
        });

        JPanel c = new JPanel();
        
        c.add(firstNumberLabel);
        c.add(firstNumberField);
        c.add(secondNumberLabel);
        c.add(secondNumberField);
        c.add(addButton);
        c.add(resultLabel);
        c.add(resultField);
        
        this.add(c);
    }
    

}