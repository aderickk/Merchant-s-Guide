package merchantsNotes;
import java.util.*;


public class RomanCalculator {

	static HashMap<Character, Integer> romanMap;
	
	public static void initial() {
		romanMap = new HashMap<Character, Integer>();
		
		romanMap.put('I', 1);
		romanMap.put('V', 5);
		romanMap.put('X', 10);
		romanMap.put('L', 50);
		romanMap.put('C', 100);
		romanMap.put('D', 500);
		romanMap.put('M', 1000);	
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
