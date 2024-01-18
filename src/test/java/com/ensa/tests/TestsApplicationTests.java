package com.ensa.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class TestsApplicationTests {
	private final BasicCalculator basicCalculator=new BasicCalculator();

	@Test
	void contextLoads() {
	}

	@Test
	void itShouldAddTwoNumbers(){
		// Given
		double num1 = 1;
		double num2 = 2;

		// When
		double result = basicCalculator.add(num1, num2);

		// Then
		double expected = 3;
		assertThat(result).isEqualTo(expected);

	}

	public class BasicCalculator {

		// Method for addition
		public double add(double num1, double num2) {
			return num1 + num2;
		}

		// Method for subtraction
		public double subtract(double num1, double num2) {
			return num1 - num2;
		}

		// Method for multiplication
		public double multiply(double num1, double num2) {
			return num1 * num2;
		}

		// Method for division
		public double divide(double num1, double num2) {
			if (num2 == 0) {
				throw new ArithmeticException("Cannot divide by zero");
			}
			return num1 / num2;
		}
	}


}
