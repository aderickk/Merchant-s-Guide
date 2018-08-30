package merchant_other;

public enum RomanCharacterEnum {
	I('I', 1), 
	V('V', 5), 
	X('X', 10), 
	L('L', 50), 
	C('C', 100), 
	D('D', 500), 
	M('M', 1000);
	
	private char romanChar;
	private int decValue;
	
	RomanCharacterEnum(char romanChar, int decValue){
		this.romanChar = romanChar;
		this.decValue = decValue;
	}
	
	public char getChar() {
		return romanChar;
	}
	
	public int getDecimalValue() {
		return decValue;
	}
	
	public static boolean isRomanCharacter(char c) {
		for(RomanCharacterEnum validChar: RomanCharacterEnum.values()) {
			if (validChar.getChar() == c) return true;
		}
		return false;
	}
}
