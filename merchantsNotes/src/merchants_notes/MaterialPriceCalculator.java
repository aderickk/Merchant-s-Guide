package merchants_notes;

import java.util.Arrays;
import java.util.HashMap;

import merchant_other.MerchantsException;

public class MaterialPriceCalculator {
	
	static HashMap<String, Integer> materialPriceList;
	
	public static void cleanMaterialPriceList() {
		if (materialPriceList != null) materialPriceList.clear();
	}
	
	public static void addEntry(String input) throws MerchantsException{
		String[] inputArray = input.split(" ");
		
		// Get first capital letter as material name
		int materialIndex = -1;
		for(int i=0; i < inputArray.length ; i++) {
			if (Character.isUpperCase(inputArray[i].charAt(0))) {
				materialIndex = i;
				break;
			}
		}
		
		// Throw error if no material name specified.
		if (materialIndex == -1) throw new MerchantsException();
		
		// Throw error if no price specified. 
		try {
			Integer.parseInt(inputArray[materialIndex + 2]);
		} catch (NumberFormatException e) {
			throw new MerchantsException();
		}
		
		// Get individual value.
		String[] alienString = Arrays.copyOf(inputArray, materialIndex);		
		int decimalValue = AlienDictionary.alienLanguageToDecimal(alienString);
		int overallPrice = Integer.parseInt(inputArray[materialIndex+2]);
		int individualPrice = overallPrice / decimalValue;
		
		// Add the material to hashmap.
		String key = inputArray[materialIndex];
		
		if (materialPriceList == null) materialPriceList = new HashMap<String, Integer>();
		materialPriceList.put(key, individualPrice);		
	}
	
	public static int getMaterialPrice(String input) throws MerchantsException {
		String[] inputArray = input.split(" ");		
		int materialIndex = inputArray.length - 1;
		String materialName = inputArray[materialIndex];
		
		// Error entry and throws exception
		if (materialPriceList == null || !materialPriceList.containsKey(materialName)) {
			throw new MerchantsException();
		}
		
		// Convert alien language to decimal.
		String[] alienString = Arrays.copyOf(inputArray, materialIndex);		
		int decimalValue = AlienDictionary.alienLanguageToDecimal(alienString);
		
		int individualPrice = materialPriceList.get(inputArray[materialIndex]);
		
		return (individualPrice * decimalValue);
	}
}
