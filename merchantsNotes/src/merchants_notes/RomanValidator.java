package merchants_notes;

/**
 * @author Aderick
 *
 *	Responsible to validates whether a roman numeral is valid.
 */
public class RomanValidator {
	private enum ValidRepetition {
		I('I'), X('X'), C('C'), M('M');
		
		private char member;
		ValidRepetition(char m) {
			this.member = m;
		}
		
		public char getChar() {
			return this.member;
		}
	};
	
	private enum ValidSubtraction {
		IV("IV"), IX("IX"), XL("XL"), XC("XC"), CD("CD"), CM("CM");
		
		private String member;
		ValidSubtraction(String m){
			this.member = m;
		}
		
		public String getString() {
			return this.member;
		}
	
	};	
		
	private static boolean isRepetitionMember(char curr) {
		// If the character exist in Valid Repetition list, then it's okay to repeat those character. 
		for (ValidRepetition vr : ValidRepetition.values()) {
			if (curr == vr.getChar()) return true;
		}
		
		return false;
	}
	
	/**
	 * Validate whether a subtraction in roman numeral is valid. If two character exist in
	 * Valid Subtraction list, then it's valid.
	 * 
	 * @param curr
	 * @param next
	 * @return
	 */
	public static boolean isValidSubtraction(char curr, char next) {
		StringBuilder str = new StringBuilder();
		str.append(curr);
		str.append(next);
		
		for(ValidSubtraction vs : ValidSubtraction.values()) {
			if (vs.getString().equals(str.toString())) return true;
		}
		return false;
	}
	
	/**
	 * Check if the string contains illegal Roman number repetition.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isValidRepetition(String input) {
		// Only three repetitions of a character allowed.
		int repeatCount = 1;
		for(int i = 0; i < input.length(); i++) {
			int nextChar = i + 1;
			if (nextChar < input.length() && input.charAt(i) == (input.charAt(nextChar))) {
				repeatCount++;
				if (!RomanValidator.isRepetitionMember(input.charAt(i))) return false;
				if (repeatCount > 3) return false;
			} else {
				// different character found. so only 1 repeating char now.
				repeatCount = 1;
			}
		}
		
		return true;
	}
}