package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import merchantsNotes.AlienDictionary;
import merchantsNotes.MerchantsException;

class AlienDictionaryTest {
	
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
	void test_add_invalid_entry() {
		String invalidEntry = "glob grok is L";
		
		try {
			Assertions.assertThrows(MerchantsException.class, () -> {
				AlienDictionary.addEntry(invalidEntry);
			});
		} catch (Exception e) {
			// do nothing
		}
	}
}
