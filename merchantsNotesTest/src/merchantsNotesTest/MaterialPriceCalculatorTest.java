package merchantsNotesTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import merchantsNotes.AlienDictionary;
import merchantsNotes.MaterialPriceCalculator;
import merchantsNotes.MerchantsException;
import merchantsNotes.RomanCalculator;

class MaterialPriceCalculatorTest {

	@BeforeAll
	static void materialPriceCalculator_before_all() {
		try {
			RomanCalculator.initial();
			AlienDictionary.addEntry("cih is M");
			AlienDictionary.addEntry("bleh is C");
			AlienDictionary.addEntry("pish is X");
			AlienDictionary.addEntry("tegj is L");
			AlienDictionary.addEntry("glob is I");
			AlienDictionary.addEntry("prok is V");
		} catch (MerchantsException e) {
			// do nothing
		} 
	}
	
	@BeforeEach
	void materialPriceCalculator_before_each() {
		MaterialPriceCalculator.cleanMaterialPriceList();
	}
	
	@Test
	void test_valid_entries() {
		String validEntry1 = "glob glob Silver is 34 Credits";
		String validEntry2 = "pish pish pish Vibranium is 666 Credits";
		
		assertAll("valid entries",
				() -> MaterialPriceCalculator.addEntry(validEntry1),
				() -> MaterialPriceCalculator.addEntry(validEntry2));
	}
	
	@Test
	void test_invalid_entry() {
		String invalidEntry = "glob glob glob is 54";
		
		Assertions.assertThrows(MerchantsException.class, () -> {
			MaterialPriceCalculator.addEntry(invalidEntry);
		});
	}

	@Test
	void test_get_material_price_simple_1() {
		String entry = "glob glob Silver is 34 Credits";
		String testCase = "glob prok Silver";
		int expectedResult = 68;
		int result = 0;
		
		try {
			MaterialPriceCalculator.addEntry(entry);
			result = MaterialPriceCalculator.getMaterialPrice(testCase);
		} catch(Exception e) {
			// Do nothing
		}
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void test_get_material_price_simple_2() {
		String entry = "glob glob glob Gold is 60000 Credits";
		String testCase = "glob pish Gold";
		int expectedResult = 180000;
		int result = 0;
		
		try {
			MaterialPriceCalculator.addEntry(entry);
			result = MaterialPriceCalculator.getMaterialPrice(testCase);
		} catch(Exception e) {
			// Do nothing
		}
		
		assertEquals(expectedResult, result);
	}
}
