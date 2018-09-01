package merchants_notes;

import java.util.Scanner;

import merchant_other.MerchantsException;

/**
 * @author Aderick
 *
 *	Main class of the application.
 */
public class MerchantsNotes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MenuFilterer mf = new MenuFilterer();
		
		System.out.println("======== Merchants Notes To The Galaxy ========");
		System.out.println("== User Help: Use \"LOAD 'File path'\" to load a file ==");
		System.out.println("== Example: \"LOAD C:\\Users\\Documents\\test_case_1.txt\" ==");
		
		String inputString = "";
		String exitCommand = "exit";
		String loadCommand = "LOAD";
		
		do {
			inputString = scanner.nextLine();
			try {
				if (!inputString.toLowerCase().equals(exitCommand)) {
					if (inputString.substring(0, 4).toUpperCase().equals(loadCommand)) {
						mf.handleReadFiles(inputString);
					} else {
						mf.filter(inputString);		
					}					
				}				
			} catch (MerchantsException e) {
				// Do nothing.
			}
		} while (!inputString.equals(exitCommand));
		
		System.out.println("Goodbye!");
		scanner.close();
	}

}
