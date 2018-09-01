package merchantsNotesTest;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merchant_other.MerchantsException;
import merchants_notes.AlienDictionary;
import merchants_notes.MaterialPriceCalculator;
import merchants_notes.MenuFilterer;

class MenuFiltererTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final String standardErrMessage = "I don't know what you're talking about.\r\n";
	
	static MenuFilterer mf;
	
	@BeforeAll
	static void menuFiltererTest_before_all() {
		mf = new MenuFilterer();
	}
	
	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    AlienDictionary.cleanDictionary();
	    MaterialPriceCalculator.cleanMaterialPriceList();
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	void test_invalid_less_than_three_words() {
		String testCase = "Foo bar";
		String expectedResult = standardErrMessage;
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}

	@Test
	void test_invalid_meaningless_three_words() {
		String testCase = "Fus Ro Dah";
		String expectedResult = standardErrMessage;
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_invalid_meaningless_long_words_1() {
		String testCase = "Lorem ipsum dolor sit amel";
		String expectedResult = standardErrMessage;
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_invalid_meaningless_long_words_2() {
		String testCase = "How many of us should die more in the friendzone ?";
		String expectedResult = standardErrMessage;
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_invalid_meaningless_long_words_3() {
		String testCase = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
		String expectedResult = standardErrMessage;
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_alien_dictionary_entry_1() {
		String testCase = "bleh is C";		
		String expectedResult = "";
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_alien_dictionary_entry_multiple() {
		String testCase1 = "bleh is C";
		String testCase2 = "bhu is L";
		String testCase3 = "goo is V";
		String testCase4 = "fus is I";
		
		String expectedResult = "";
		
		try {
			mf.filter(testCase1);
			mf.filter(testCase2);
			mf.filter(testCase3);
			mf.filter(testCase4);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_alien_dictionary_question_1() {
		String entry = "bleh is C";
		
		String testCase = "how much is bleh ?";
		String expectedResult = "bleh is 100\r\n";
		
		try {
			mf.filter(entry);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_alien_dictionary_question_2() {
		String entry1 = "bleh is C";
		String entry2 = "bhu is X";
		
		String testCase = "how much is bleh bleh bhu bleh ?";
		String expectedResult = "bleh bleh bhu bleh is 290\r\n";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_alien_dictionary_question_3() {
		String entry1 = "pish is X";
		String entry2 = "glob is I";
		String entry3 = "pish is X";
		String entry4 = "tegj is L";
		
		String testCase = "how much is pish tegj glob glob ?";
		String expectedResult = "pish tegj glob glob is 42\r\n";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
			mf.filter(entry4);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_material_price_entry_1() {
		String entry1 = "boo is X";
		String entry2 = "ij is L";
		String entry3 = "boo Diamond is 25000 Credits";
		
		String expectedResult = "";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_material_price_question_1() {
		String entry1 = "boo is X";
		String entry2 = "ij is L";
		String entry3 = "boo Diamond is 25000 Credits";
		
		String testCase = "how many Credits is ij Diamond ?";
		String expectedResult = "ij Diamond is 125000 Credits\r\n";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_material_price_question_2() {
		String entry1 = "glob is I";
		String entry2 = "prok is V";
		String entry3 = "pish is X";
		String entry4 = "tegj is L";
		String entry5 = "glob prok Gold is 57800 Credits";
		
		String testCase = "how many Credits is glob prok Gold ?";
		String expectedResult = "glob prok Gold is 57800 Credits\r\n";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
			mf.filter(entry4);
			mf.filter(entry5);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_valid_material_price_question_3() {
		String entry1 = "gouuu is I";
		String entry2 = "bhibi is X";
		String entry3 = "lag is V";
		String entry4 = "gouuu Iron is 2000 Credits";
		String entry5 = "lag Silver is 1500 Credits";
		
		String testCase = "how many Credits is bhibi bhibi lag Iron ?";
		String expectedResult = "bhibi bhibi lag Iron is 50000 Credits\r\n";
		
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
			mf.filter(entry4);
			mf.filter(entry5);
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
	
	@Test
	void test_ALL_functions() {
		String entry1 = "glob is I";
		String entry2 = "prok is V";
		String entry3 = "pish is X";
		String entry4 = "tegj is L";
		String entry5 = "glob glob Silver is 34 Credits";
		String entry6 = "glob prok Gold is 57800 Credits";
		String entry7 = "pish pish Iron is 3910 Credits";	
				
		String testCase1 = "how much is pish tegj glob glob ?";
		String testCase2 = "how many Credits is glob prok Silver ?";
		String testCase3 = "how many Credits is glob prok Gold ?";
		String testCase4 = "how many Credits is glob prok Iron ?";
		String testCase5 = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
		
		String expectedResult1 = "pish tegj glob glob is 42\r\n";
		String expectedResult2 = "glob prok Silver is 68 Credits\r\n";
		String expectedResult3 = "glob prok Gold is 57800 Credits\r\n";
		String expectedResult4 = "glob prok Iron is 782 Credits\r\n";
		String expectedResult5 = "I don't know what you're talking about.\r\n";
		
		StringBuilder expectedResult = new StringBuilder();
		expectedResult.append(expectedResult1);
		expectedResult.append(expectedResult2);
		expectedResult.append(expectedResult3);
		expectedResult.append(expectedResult4);
		expectedResult.append(expectedResult5);
		try {
			mf.filter(entry1);
			mf.filter(entry2);
			mf.filter(entry3);
			mf.filter(entry4);
			mf.filter(entry5);
			mf.filter(entry6);
			mf.filter(entry7);
			
			mf.filter(testCase1);
			mf.filter(testCase2);
			mf.filter(testCase3);
			mf.filter(testCase4);
			mf.filter(testCase5);
		} catch(MerchantsException e) {
			// do nothing
		}
		
		assertEquals(expectedResult.toString(), outContent.toString());
	}
}
