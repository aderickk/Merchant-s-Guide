package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import extras.MerchantsException;
import function_support.RomanCalculator;

class RomanCalculatorTest {

	@BeforeAll
	static void romanCalculator_setup() {
		RomanCalculator.initial();
	}
	
	@Test
	void test_simple_1() {
		String testCase = "I";
		int expectedResult = 1;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}

	@Test
	void test_simple_2() {
		String testCase = "IV";
		int expectedResult = 4;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_simple_3() {
		String testCase = "L";
		int expectedResult = 50;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_1() {
		String testCase = "XXIII";
		int expectedResult = 23;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_2() {
		String testCase = "XLII";
		int expectedResult = 42;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_3() {
		String testCase = "MMVI";
		int expectedResult = 2006;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_complicated_4() {
		String testCase = "MCMXLIV";
		int expectedResult = 1944;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_long_roman_number_1() {
		String testCase = "MDCCCLXXXVIII";
		int expectedResult = 1888;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_long_roman_number_2() {
		String testCase = "MMMDCCCLXXXVIII";
		int expectedResult = 3888;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_largest_roman_number() {
		String testCase = "MMMCMXCIX";
		int expectedResult = 3999;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_invalid_roman_repetitions_1() {
		String testCase = "XXXX";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_invalid_roman_repetitions_2() {
		String testCase = "MMMCCCC";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_invalid_roman_repetitions_3() {
		String testCase = "VV";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_invalid_roman_repetitions_4() {
		String testCase = "MMMDDXXX";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_valid_roman_repetitions_1() {
		String testCase = "XXXIX";		
		int expectedResult = 39;
		
		int result = 0;
		try {
			result = RomanCalculator.convertRomanToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_invalid_roman_subtraction_1() {
		String testCase = "VL";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_invalid_roman_subtraction_2() {
		String testCase = "IM";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
	
	@Test
	void test_invalid_roman_subtraction_3() {
		String testCase = "DM";
		
		assertThrows(MerchantsException.class, 
				() -> RomanCalculator.convertRomanToDecimal(testCase));
	}
}
