package de.hftstuttgart.wip2;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.sourceforge.marathon.javadriver.JavaDriver;

/**
 * AppTestIT
 */
public class AppTestIT {
	
	private CalculatorWindow calcWin;
	private Calculator calculator;
	private JavaDriver driver;

	@Before
	public void setUp() {
		calculator = new Calculator();
		SwingUtilities.invokeLater(() -> {
			calcWin = new CalculatorWindow(calculator);
			calcWin.setVisible(true);
		});
		driver = new JavaDriver();
	}

	@After
	public void tearDown() throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				calcWin.dispose();
			}
		});
		driver.close();
	}

    @Test
    public void testAddition() {
    	PageCalculator pageCalc = new PageCalculator(driver);
    	pageCalc.setFirstNumber("23");
    	pageCalc.setSecondNumber("19");
    	pageCalc.add();
    	assertEquals(pageCalc.getResult(), "42");
    	
    }
}