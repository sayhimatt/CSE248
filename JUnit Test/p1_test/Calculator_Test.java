package p1_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import p1.Calculator;

class Calculator_Test {
	
	@Test
	void testTime() {
		Calculator c = new Calculator(5);
		c.times(2);
		assertEquals(10, c.getAmount(), 0.01);
		
	}
	@Test
	void testDivide() {
		Calculator c = new Calculator(10);
		c.divide(1);
		
		assertEquals(10.0000000003, c.getAmount(), 0.000000001);
		
	}
	@Test
	void testRoundAmount() {
		Calculator c = new Calculator(1);
		System.out.println(c.roundAmount(1234.12345));
		assertEquals("1,234.12", c.roundAmount(1234.12345));
	}
	@Test
	void testDenominatorIsZero() {
		Calculator c = new Calculator(5);
		assertTrue(c.denominatorIsZero(0));
	}

}
