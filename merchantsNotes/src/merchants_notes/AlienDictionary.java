package merchants_notes;

import java.util.HashMap;

import merchant_other.MerchantsException;
import merchant_other.RomanCharacterEnum;

/**
 * @author Aderick
 *
 * Responsible for handling known alien language and its meaning.
 *
 */
public class AlienDictionary {

	private static HashMap<String, Character> aDictionary;
	
	public static void cleanDictionary() {
		if (aDictionary != null) aDictionary.clear();
	}
	
	/**
	 * Add new known alien word.
	 * 
	 * @param input
	 * @throws MerchantsException
	 */
	public static void addEntry(String input) throws MerchantsException {
		if (aDictionary == null) aDictionary = new  HashMap<String, Character>();
		
		String[] inputArray = input.split(" ");
		
		if (inputArray.length != 3 
				|| inputArray[2].length() > 1 
				|| !RomanCharacterEnum.isRomanCharacter(inputArray[2].charAt(0))) {
			throw new MerchantsException();
		}
		
		String key = inputArray[0].toLowerCase();
		char value = inputArray[2].toUpperCase().charAt(0);
		
		aDictionary.put(key, value);		
	}
	
	/**
	 * Search the meaning of an alien word.
	 * 
	 * @param entry
	 * @return
	 * @throws MerchantsException
	 */
	public static char searchEntry(String entry) throws MerchantsException {
		if (aDictionary == null || !aDictionary.containsKey(entry)) throw new MerchantsException();
		
		char result = aDictionary.get(entry);
		
		return result;
	}
	
	/**
	 * Get the decimal value of an alien sentence.
	 * 
	 * @param input
	 * @return
	 * @throws MerchantsException
	 */
	public static int alienLanguageToDecimal(String input) throws MerchantsException{
		String[] inputArray = input.split(" ");
		return alienLanguageToDecimal(inputArray);
	}
	
	/**
	 * Get the decimal value of an alien sentence.
	 * 
	 * @param input
	 * @return
	 * @throws MerchantsException
	 */
	public static int alienLanguageToDecimal(String[] input) throws MerchantsException{
		StringBuilder romanString = new StringBuilder();
		for (int i=0; i < input.length; i++) {
			char c = AlienDictionary.searchEntry(input[i]);
			romanString.append(c);
		}
		
		int decimalValue = RomanCalculator.convertRomanToDecimal(romanString.toString());
		
		return decimalValue;
	}
}
