package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import merchantsNotes.alienDictionary;

class alienDictionaryTest {
	
	@Test
	void test_simple_1() {
		String testCase = "glob is I";
		char expectedResult = 'I';		
		char result;
		
		try {
			alienDictionary.addEntry(testCase);
			result = alienDictionary.searchEntry("glob");
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
			alienDictionary.addEntry(testCaseOri);
			alienDictionary.addEntry(testCaseUpdated);
			
			result = alienDictionary.searchEntry("glob");			
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
			alienDictionary.addEntry(validEntry);
		} catch (Exception e) {
			// do nothing
		}
		
		Assertions.assertThrows(Exception.class, () -> {
			alienDictionary.searchEntry(invalidEntry);
		});
	}
	
	@Test
	void test_add_invalid_entry() {
		String invalidEntry = "glob grok is L";
		
		try {
			Assertions.assertThrows(Exception.class, () -> {
				alienDictionary.addEntry(invalidEntry);
			});
		} catch (Exception e) {
			// do nothing
		}
	}
}
