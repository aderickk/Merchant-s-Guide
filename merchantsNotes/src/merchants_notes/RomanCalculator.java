package merchants_notes;
import java.util.*;

import merchant_other.RomanCharacterEnum;


public class RomanCalculator {

	static HashMap<Character, Integer> romanMap;
	
	public static void initial() {
		romanMap = new HashMap<Character, Integer>();
		
		for(RomanCharacterEnum r : RomanCharacterEnum.values()) {
			romanMap.put(r.getChar(), r.getDecimalValue());
		}
	}
	
	public static int convertRomanToDecimal(String input) {
		if (romanMap == null || romanMap.isEmpty()) initial();
		
		int result = 0;
		String romanInput = input.toUpperCase();
		
		for (int i=0; i < romanInput.length(); i++) {
			if (i == (romanInput.length()-1)) {
				// last member of the string.
				result += romanMap.get(romanInput.charAt(i));
			} else {
				int currValue = romanMap.get(romanInput.charAt(i));
				int nextValue = romanMap.get(romanInput.charAt(i + 1));
				
				if (currValue < nextValue) {
					// If the next char is bigger than current value, decrease them.  
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
