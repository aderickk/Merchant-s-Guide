package merchant_other;

/**
 * @author Aderick
 *
 *	General exception used throughout the application.
 */
public class MerchantsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MerchantsException() {
		System.out.println("I don't know what you're talking about.");	
	}
	
	public MerchantsException(ErrorTypeEnum errType, String x) {
		switch (errType) {
			case RomanCalculator:
				System.out.println("Invalid Roman Numerals " + x );		
				break;
			case FileReading:
				System.out.println("Failed to read files. Please provide correct format or file.");		
				break;
		}			
	}
}
