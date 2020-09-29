package de.hftstuttgart.wip2;

import javax.swing.SwingUtilities;

public class App 
{
    public static void main( String[] args )
    {
    	final Calculator calculator = new Calculator();
    	
    	SwingUtilities.invokeLater(() -> {
				CalculatorWindow calcWin = new CalculatorWindow(calculator);
				calcWin.setVisible(true);
		});
    }
}
