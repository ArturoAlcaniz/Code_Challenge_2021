package challenge_three;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;

public class Challenge3 {
	
	private void loadValues() throws IOException {
		File input = new File("./src/challenge_three/submitInput.txt");
        File output = new File("./src/challenge_three/submitOutput.txt");
        output.createNewFile(); // if exists will do nothing 
        FileWriter fw = new FileWriter(output);
        PrintWriter writer = new PrintWriter(fw);
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	int numberCases = Integer.parseInt(bf1.readLine());
        	
        	for(int i=1; i<=numberCases; i++) {
        		line1 = bf1.readLine();
        		
        		String words = line1.split("\\|")[0];
        		String values = line1.split("\\|")[1];
        		
        		HashMap<String, Double> valuesLetters = new HashMap<>();
        		
        		if(values.substring(0,1).equals("[")) {
        			values = values.replaceAll("\\[", "");
        			values = values.replaceAll("]", "");
        			values = values.replaceAll("\\(", "");
        			values = values.replaceAll("\\), ", " | ");
        			values = values.replaceAll("\\)", "");
        			values = values.replaceAll("'", "");
        			String[] values2 = values.split("\\|");
        			for(int v=0; v<values2.length; v++) {
        				String valueNotEmpty = values2[v].replace(" ", "");
        				String letter = valueNotEmpty.split(",")[0];
        				String val = valueNotEmpty.split(",")[1];
        				if(val.contains("/")) {
        					String[] fraccion = valueNotEmpty.split(",")[1].split("/");
        					int part1 = Integer.parseInt(fraccion[0]);
        					int part2 = Integer.parseInt(fraccion[1]);
        					double div = 1.0*part1/part2;
        					Double division = new Double(div);
        					valuesLetters.put(letter, division);
        				}else {
        					valuesLetters.put(letter, Double.parseDouble(val));
        				}
        			}
        		}else if(values.substring(0,1).equals("{")) {
        			values = values.replaceAll("\\{", "");
        			values = values.replaceAll("}", "");
        			values = values.replaceAll("'", "");
        			String[] values2 = values.split(",");
        			for(int v=0; v<values2.length; v++) {
        				String valueNotEmpty = values2[v].replace(" ", "");
        				String letter = valueNotEmpty.split(":")[0];
        				String val = valueNotEmpty.split(":")[1];
        				if(val.contains("/")) {
        					String[] fraccion = valueNotEmpty.split(":")[1].split("/");
        					int part1 = Integer.parseInt(fraccion[0]);
        					int part2 = Integer.parseInt(fraccion[1]);
        					double div = 1.0*part1/part2;
        					Double division = new Double(div);
        					valuesLetters.put(letter, division);
        				}else {
        					valuesLetters.put(letter, Double.parseDouble(val));
        				}
        			}
        		} else {
        			String[] values2 = values.split(",");
        			for(int v=0; v<values2.length; v++) {
        				String valueNotEmpty = values2[v].replace(" ", "");
        				String letter = valueNotEmpty.split("=")[0];
        				String val = valueNotEmpty.split("=")[1];
        				if(val.contains("/")) {
        					String[] fraccion = valueNotEmpty.split("=")[1].split("/");
        					int part1 = Integer.parseInt(fraccion[0]);
        					int part2 = Integer.parseInt(fraccion[1]);
        					double div = 1.0*part1/part2;
        					Double division = new Double(div);
        					valuesLetters.put(letter, division);
        				}else {
        					valuesLetters.put(letter, Double.parseDouble(val));
        				}
        			}
        		}
        		
        		String word1 = words.split("-")[0];
        		String word2 = words.split("-")[1];
        		Double valueWord1 = 0.0;
        		Double valueWord2 = 0.0;
        		for(int w=0; w<word1.length(); w++) {
        			valueWord1 += valuesLetters.get(word1.substring(w, w+1));
        		}
        		
        		for(int w=0; w<word2.length(); w++) {
        			valueWord2 += valuesLetters.get(word2.substring(w, w+1));
        		}
        		String fight = "";
        		if(valueWord1 > valueWord2) {
        			fight = word1;
        		}else if(valueWord1 < valueWord2) {
        			fight = word2;
        		}else {
        			fight = "-";
        		}
        		String res = "Case #"+i+": "+fight;
        		writer.println(res);
        		
        	}

        } catch (Exception e) {
        	System.out.println("Error with files:");
        	System.out.println(e.getMessage());
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		Challenge3 challenge = new Challenge3();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
