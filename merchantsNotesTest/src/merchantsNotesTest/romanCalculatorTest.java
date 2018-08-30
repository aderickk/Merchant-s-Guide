package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import merchantsNotes.romanCalculator;

class romanCalculatorTest {

	@BeforeAll
	static void romanCalculator_setup() {
		romanCalculator.initial();
	}
	
	@Test
	void test_simple_1() {
		String testCase = "I";
		int expectedResult = 1;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}

	@Test
	void test_simple_2() {
		String testCase = "IV";
		int expectedResult = 4;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_simple_3() {
		String testCase = "L";
		int expectedResult = 50;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_1() {
		String testCase = "XXIII";
		int expectedResult = 23;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_2() {
		String testCase = "XLII";
		int expectedResult = 42;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_3() {
		String testCase = "MMVI";
		int expectedResult = 2006;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_4() {
		String testCase = "MCMXLIV";
		int expectedResult = 1944;
		
		int result = romanCalculator.convertRomanToDecimal(testCase);
		assertEquals(result, expectedResult);
	}
}
