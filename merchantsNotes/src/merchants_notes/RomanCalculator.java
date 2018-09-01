package merchants_notes;
import java.util.*;

import merchant_other.ErrorTypeEnum;
import merchant_other.MerchantsException;
import merchant_other.RomanCharacterEnum;


/**
 * @author Aderick
 *
 *	Converts Roman Numeral into decimal.
 */
public class RomanCalculator {

	static HashMap<Character, Integer> romanMap;
	
	public static void initial() {
		romanMap = new HashMap<Character, Integer>();
		
		for(RomanCharacterEnum r : RomanCharacterEnum.values()) {
			romanMap.put(r.getChar(), r.getDecimalValue());
		}
	}
	
	/**
	 * Give decimal value of a Roman number.
	 * 
	 * @param input
	 * @return
	 * @throws MerchantsException
	 */
	public static int convertRomanToDecimal(String input) throws MerchantsException {
		if (romanMap == null || romanMap.isEmpty()) initial();
		
		int result = 0;
		String romanInput = input.toUpperCase();
		
		if (!RomanValidator.isValidRepetition(romanInput)) throw new MerchantsException(ErrorTypeEnum.RomanCalculator, input);
		
		for (int i=0; i < romanInput.length(); i++) {
			if (i == (romanInput.length()-1)) {
				// last member of the string.
				result += romanMap.get(romanInput.charAt(i));
			} else {
				int currValue = romanMap.get(romanInput.charAt(i));
				int nextValue = romanMap.get(romanInput.charAt(i + 1));
				
				if (currValue < nextValue) {
					// If the next char is bigger than current value, decrease them.
					// But only if they are a valid subtraction.
					
					if(!RomanValidator.isValidSubtraction(romanInput.charAt(i), romanInput.charAt(i+1))){
						throw new MerchantsException(ErrorTypeEnum.RomanCalculator, input);
					}
					
					result += (nextValue - currValue);
					i++;
				} else {
					result += romanMap.get(romanInput.charAt(i)); 
				}	
			}
		}
		
		return result;
	}
}
