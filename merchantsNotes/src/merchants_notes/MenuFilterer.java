package merchants_notes;

import java.util.Arrays;
import java.util.List;

import merchant_other.MerchantsException;
import merchant_other.NotesCategory;

public class MenuFilterer {

	public MenuFilterer() {
		RomanCalculator.initial();
	}
	
	public void handleReadFiles(String input) throws MerchantsException{
		String filePath = MerchantFileReader.getFilePathFromQuery(input);
		List<String> fileReadResult = MerchantFileReader.readFile(filePath);
		
		for(String s : fileReadResult) {
			try {
				System.out.println(s);
				this.filter(s);
			} catch(MerchantsException ex) {
				// Do nothing
			}
		}
	}
	
	public void filter(String input) throws MerchantsException {
		NotesCategory nc = categorizeSentence(input);
		
		String[] inputArray = input.split(" ");
		int startIndex = -1;
		int endIndex = -1;
		int result = 0;
		switch (nc) {
			case AlienDictionaryEntry:
				AlienDictionary.addEntry(input);
				break;
			case AlienDictionaryQuestion:
				startIndex = getWordIndex(inputArray, "is") + 1;
				endIndex = getWordIndex(inputArray, "?");
				if (endIndex == startIndex) endIndex++;
				String[] dictionaryQuestion = Arrays.copyOfRange(inputArray, startIndex, endIndex);
				result = AlienDictionary.alienLanguageToDecimal(dictionaryQuestion);
				printResult(dictionaryQuestion, result, false);
				break;
			case MaterialPriceEntry:
				MaterialPriceCalculator.addEntry(input);
				break;
			case MaterialPriceQuestion:
				startIndex = getWordIndex(inputArray, "is") + 1;
				endIndex = getWordIndex(inputArray, "?");
				if (endIndex == startIndex) endIndex++;
				String[] materialQuestion = Arrays.copyOfRange(inputArray, startIndex, endIndex);
				result = MaterialPriceCalculator.getMaterialPrice(materialQuestion);
				printResult(materialQuestion, result, true);
				break;
			case Uncategorized:
				throw new MerchantsException();
		}
	}
	
	private NotesCategory categorizeSentence(String input) {
		String[] inputArray = input.split(" ");
		
		// if the sentence is less than 3 or not contain the word "is", then it's not a valid sentence.
		if (inputArray.length < 3 || !input.contains(" is ")) return NotesCategory.Uncategorized;
				
		//if the last word is not question mark, then it's probably an entry.
		if (!inputArray[inputArray.length-1].equals("?")) {
			// entry for alien dictionary is when the string consist of 3 words and the
			// second word is "is".
			if (inputArray.length == 3 && inputArray[1].equals("is")) return NotesCategory.AlienDictionaryEntry;
			
			// entry for merchant notes is when a material name exist (identified by upper case word)
			for(int i=0; i < inputArray.length ; i++) {
				if (Character.isUpperCase(inputArray[i].charAt(0)) 
						&& !inputArray[i].equals("Credits")) {
					return NotesCategory.MaterialPriceEntry;
				}
			}
		} 
		
		// if a sentence ends with question mark and starts with a valid question,
		// then it's probably a question.
		if (inputArray[inputArray.length-1].equals("?") 
				&& isValidQuestion(inputArray)){
			if (inputArray[2].equals("Credits")) {
				return NotesCategory.MaterialPriceQuestion;
			} else if (inputArray[2].equals("is")) {
				return NotesCategory.AlienDictionaryQuestion;
			}
		}
		
		return NotesCategory.Uncategorized;
	}
	
	private boolean isValidQuestion(String[] input) {
		if (!input[0].equals("how")) return false;
		if (!input[1].equals("many") && !input[1].equals("much")) return false;
		return true;
	}
	
	private int getWordIndex(String[] stringArray, String word) {
		for(int i = 0; i < stringArray.length; i++) {
			if (stringArray[i].equals(word)) return i;
		}
		
		return -1;
	}
	
	private void printResult(String[] questionFormat, int result, boolean isWithCredit) {
		StringBuilder sb = new StringBuilder();
		for(String s : questionFormat) {
			sb.append(s + " ");
		}
		
		sb.append("is " + result);
		if (isWithCredit) sb.append(" Credits");
		System.out.println(sb.toString());
	}
}
