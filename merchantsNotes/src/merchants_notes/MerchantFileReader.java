package merchants_notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import merchant_other.ErrorTypeEnum;
import merchant_other.MerchantsException;

/**
 * @author Aderick
 *
 *	Responsible for reading a file.
 */
public class MerchantFileReader {

	/**
	 * Read a file and returns a list of its lines.
	 * 
	 * @param filePath
	 * @return
	 * @throws MerchantsException
	 */
	public static List<String> readFile(String filePath) throws MerchantsException{
		File fn = new File(filePath);
		List<String> result = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fn));
			
			String line = null;
			while ((line = br.readLine()) != null)
		    {
				result.add(line);
		    }
			
			br.close();
		} catch (IOException e) {
			throw new MerchantsException(ErrorTypeEnum.FileReading, "fail");
		}
		
		return result;
	}
	
	/**
	 * Get the file path from a sentence.
	 * 
	 * @param input
	 * @return
	 */
	public static String getFilePathFromQuery(String input) {
		return input.substring(5, input.length());
	}
}
