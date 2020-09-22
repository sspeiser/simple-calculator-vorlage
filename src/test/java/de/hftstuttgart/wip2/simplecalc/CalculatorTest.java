package de.hftstuttgart.wip2.simplecalc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

		@Test
		public void sumNumbers() {
			Calculator calc = new Calculator();
			int sum = calc.sum(31, 11);
			assertEquals(42, sum);
		}
}
