package merchants_notes;

import java.util.Scanner;

import merchant_other.MerchantsException;

public class MerchantsNotes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MenuFilterer mf = new MenuFilterer();
		String inputString = "";
		
		do {
			inputString = scanner.nextLine();
			try {
				mf.filter(inputString);
			} catch (MerchantsException e) {
				// Do nothing.
			}
		} while (!inputString.equals("exit"));
	}

}
