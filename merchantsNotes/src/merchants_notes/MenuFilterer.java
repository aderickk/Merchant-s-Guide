package merchants_notes;

import merchant_other.MerchantsException;

public class MenuFilterer {

	public MenuFilterer() {
		RomanCalculator.initial();
	}
	
	public void filter(String input) throws MerchantsException {
		String[] inputArray = input.split(" ");
		
		//if the notes less than 3 words then throw exception
		if (inputArray.length < 3) throw new MerchantsException();
		
		// input for alien dictionary is when the string only consist of 3 words.
		if (inputArray.length == 3 && inputArray[1].equals("is")) {
			AlienDictionary.addEntry(input);
			return;
		} else if(inputArray.length > 3) {
			
		}
		
		throw new MerchantsException();
	}
	
	private boolean isStringContains(String[] inputArray, String wordToSearch) {
		for(String s : inputArray) {
			if (s.equals(wordToSearch)) return true;
		}
		
		return false;
	}
}
