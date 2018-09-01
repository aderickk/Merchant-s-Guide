package merchantsNotesTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import function_support.MerchantFileReader;

class MerchantFileReaderTest {

	@Test
	void test_simple_test() {
		String testCase = System.getProperty("user.dir") + "\\fileTest\\simple_test_1.txt";
		String result1 = "bleh is C";
		String result2 = "how much is bleh ?";
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add(result1);
		expectedResult.add(result2);
		List<String> result = new ArrayList<String>();
		
		try {
			result = MerchantFileReader.readFile(testCase);
		} catch(Exception ex) {
			//
		}
		
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	void test_complicated_test_1() {
		String testCase = System.getProperty("user.dir") + "\\fileTest\\complicated_test_1.txt";
		String entry1 = "glob is I";
		String entry2 = "prok is V";
		String entry3 = "pish is X";
		String entry4 = "tegj is L";
		String entry5 = "glob glob Silver is 34 Credits";
		String entry6 = "glob prok Gold is 57800 Credits";
		String entry7 = "pish pish Iron is 3910 Credits";	
				
		String testCase1 = "how much is pish tegj glob glob ?";
		String testCase2 = "how many Credits is glob prok Silver ?";
		String testCase3 = "how many Credits is glob prok Gold ?";
		String testCase4 = "how many Credits is glob prok Iron ?";
		String testCase5 = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add(entry1);
		expectedResult.add(entry2);
		expectedResult.add(entry3);
		expectedResult.add(entry4);
		expectedResult.add(entry5);
		expectedResult.add(entry6);
		expectedResult.add(entry7);
		expectedResult.add(testCase1);
		expectedResult.add(testCase2);
		expectedResult.add(testCase3);
		expectedResult.add(testCase4);
		expectedResult.add(testCase5);
		List<String> result = new ArrayList<String>();
		
		try {
			result = MerchantFileReader.readFile(testCase);
		} catch(Exception ex) {
			//
		}
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	void read_input_test_1() {
		String testCase = "LOAD C:\\documents\\users\\test.txt";
		String expectedResult = "C:\\documents\\users\\test.txt";
		
		String result = MerchantFileReader.getFilePathFromQuery(testCase);
		
		assertEquals(expectedResult, result);
	}

	@Test
	void read_input_test_2() {
		String testCase = "LOAD D:\\my documents\\testing\\test 2.txt";
		String expectedResult = "D:\\my documents\\testing\\test 2.txt";
		
		String result = MerchantFileReader.getFilePathFromQuery(testCase);
		
		assertEquals(expectedResult, result);
	}
}
