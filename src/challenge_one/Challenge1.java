package challenge_one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Challenge1 {

	private String getMinimumValue(int dice1, int dice2) {
		return dice1 + dice2 == 12 ? "-" : String.valueOf(dice1+dice2+1);
	}
	
	private void loadValues() throws IOException {
		File input = new File("./src/challenge_one/submitInput.txt");
        File output = new File("./src/challenge_one/submitOutput.txt");
        output.createNewFile();
        FileWriter fw = new FileWriter(output);
        PrintWriter writer = new PrintWriter(fw);
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	line1 = bf1.readLine();
        	int numberCase = 1;
        	
        	
            while ((line1 = bf1.readLine()) != null) {
            	int number1 = Integer.parseInt(line1.substring(0, 1));
            	int number2 = Integer.parseInt(line1.substring(2, 3));
            	String result = getMinimumValue(number1, number2);
            	String res = "Case #"+numberCase+": "+result;
            	writer.println(res);
                numberCase++;
            }
        } catch (Exception e) {
        	System.out.println("Error with files:");
        	System.out.println(e.getMessage());
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		Challenge1 challenge = new Challenge1();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
