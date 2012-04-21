import java.io.*;

public class Summarizer {
	
	public static String summarizeText(File file){
		BufferedReader input; 
		try {
			
			input = new BufferedReader(new FileReader(file));
			String line = null;
			if((line = input.readLine()) != null){
				input.close();
				return line;
			}
			else {
				input.close();
				return "";
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
