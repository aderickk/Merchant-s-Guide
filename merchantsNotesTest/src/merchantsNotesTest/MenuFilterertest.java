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

class MenuFilterertest {

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
	void test_alien_dictionary_valid_entry_1() {
		String testCase = "bleh is C";
		String expectedResult = "";
		
		try {
			mf.filter(testCase);
		} catch (MerchantsException e) {
			// Do nothing
		}
		assertEquals(expectedResult, outContent.toString());
	}
}
