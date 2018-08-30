package merchantsNotes;

import java.util.HashMap;

public class alienDictionary {

	private static HashMap<String, Character> aDictionary;
	
	public static void addEntry(String input) throws Exception {
		if (aDictionary == null) aDictionary = new  HashMap<String, Character>();
		
		String[] inputArray = input.split(" ");
		
		if (inputArray.length != 3) {
			System.out.println("I don't know what you're talking about.");
			throw new Exception();
		}
		
		String key = inputArray[0].toLowerCase();
		char value = inputArray[2].toUpperCase().charAt(0);
		
		aDictionary.put(key, value);		
	}
	
	public static char searchEntry(String entry) throws Exception {
		if (aDictionary == null || !aDictionary.containsKey(entry)) {
			System.out.println("I don't know what you're talking about.");
			throw new Exception();
		}
		
		char result = aDictionary.get(entry);
		
		return result;
	}
}
