package de.hftstuttgart.wip2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void addingNumbers() {
		Calculator calc = new Calculator();
		assertEquals(calc.add(17, 3), 20);
	}

}
