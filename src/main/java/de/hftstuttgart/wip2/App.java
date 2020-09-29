package de.hftstuttgart.wip2;

import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final Calculator calculator = new Calculator();
    	
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CalculatorWindow calcWin = new CalculatorWindow(calculator);
				calcWin.setVisible(true);
				
			}
		});
    }
}
