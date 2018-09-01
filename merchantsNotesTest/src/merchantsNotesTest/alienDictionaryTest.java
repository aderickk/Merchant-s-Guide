package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import extras.MerchantsException;
import function_main.AlienDictionary;
import function_support.RomanCalculator;

class AlienDictionaryTest {
	
	@BeforeAll
	static void alien_dictionary_setup() {
		RomanCalculator.initial();
	}
	
	@BeforeEach
	void alien_dictionary_before_each() {
		AlienDictionary.cleanDictionary();
	}
	
	@Test
	void test_simple_1() {
		String testCase = "glob is I";
		char expectedResult = 'I';		
		char result;
		
		try {
			AlienDictionary.addEntry(testCase);
			result = AlienDictionary.searchEntry("glob");
		} catch (Exception e) {
			result = ' ';
		}
		assertEquals(result, expectedResult);
	}

	@Test
	void test_update_languange() {
		String testCaseOri = "glob is I";
		String testCaseUpdated = "glob is X";
		char expectedResult = 'X';		
		char result;
		
		try {
			AlienDictionary.addEntry(testCaseOri);
			AlienDictionary.addEntry(testCaseUpdated);
			
			result = AlienDictionary.searchEntry("glob");			
		} catch (Exception e) {
			result = ' ';
		}
		assertEquals(result, expectedResult);
	}
	
	@Test
	void test_search_invalid_entry() {
		String validEntry = "glob is L";
		String invalidEntry = "grok";
		
		try {
			AlienDictionary.addEntry(validEntry);
		} catch (Exception e) {
			// do nothing
		}
		
		Assertions.assertThrows(MerchantsException.class, () -> {
			AlienDictionary.searchEntry(invalidEntry);
		});
	}
	
	@Test
	void test_add_invalid_entry_1() {
		String invalidEntry = "glob grok is L";
		
		try {
			Assertions.assertThrows(MerchantsException.class, () -> {
				AlienDictionary.addEntry(invalidEntry);
			});
		} catch (Exception e) {
			// do nothing
		}
	}
	
	@Test
	void test_add_invalid_entry_2() {
		String invalidEntry = "grrr is U";
		
		try {
			Assertions.assertThrows(MerchantsException.class, () -> {
				AlienDictionary.addEntry(invalidEntry);
			});
		} catch (Exception e) {
			// do nothing
		}
	}
	
	@Test
	void test_add_invalid_entry_3() {
		String invalidEntry = "life is free";
		
		try {
			Assertions.assertThrows(MerchantsException.class, () -> {
				AlienDictionary.addEntry(invalidEntry);
			});
		} catch (Exception e) {
			// do nothing
		}
	}
	
	@Test
	void test_convert_alien_language_to_decimal_1() {
		String testCase = "glob glob";
		int expectedResult = 2;
		
		int result = 0;
		try {
			AlienDictionary.addEntry("glob is I");
			result = AlienDictionary.alienLanguageToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		} 
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void test_convert_alien_language_to_decimal_2() {
		//MCMXLIV 
		String testCase = "cih bleh cih pish tegj glob prok";
		int expectedResult = 1944;
		
		int result = 0;
		try {
			AlienDictionary.addEntry("cih is M");
			AlienDictionary.addEntry("bleh is C");
			AlienDictionary.addEntry("pish is X");
			AlienDictionary.addEntry("tegj is L");
			AlienDictionary.addEntry("glob is I");
			AlienDictionary.addEntry("prok is V");
			result = AlienDictionary.alienLanguageToDecimal(testCase);
		} catch (MerchantsException e) {
			// do nothing
		} 
		
		assertEquals(expectedResult, result);
	}
}
