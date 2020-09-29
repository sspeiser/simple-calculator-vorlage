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
	private JavaDriver driver;

	@Before
	public void setUp() {
		Calculator calculator = new Calculator();
		calcWin = new CalculatorWindow(calculator);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				calcWin.setVisible(true);
			}
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