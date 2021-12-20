package challenge_two;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Challenge2 {
	
	private void loadValues() throws IOException {
		File input = new File("./src/challenge_two/submitInput.txt");
        File output = new File("./src/challenge_two/submitOutput.txt");
        output.createNewFile(); // if exists will do nothing 
        FileWriter fw = new FileWriter(output);
        PrintWriter writer = new PrintWriter(fw);
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	int numberCases = Integer.parseInt(bf1.readLine());
        	
        	for(int i=1; i<=numberCases; i++) {
        		line1 = bf1.readLine();
        		int pokemons = Integer.parseInt(line1.split(" ")[0]);
        		int size = Integer.parseInt(line1.split(" ")[1]);
        		List<String> pokemonsString = new ArrayList<String>();
        		
        		for(int p=0; p<pokemons; p++) {
        			line1 = bf1.readLine();
        			pokemonsString.add(line1);
        		}
        		
        		String texto = "";
        		for(int l=0; l<size; l++) {
        			line1 = bf1.readLine();
        			texto = texto + line1.replaceAll(" ", "");
        		}
        		
        		while(pokemonsString.size() > 0) {
        			for(int p=0; p<pokemonsString.size(); p++) {
        				String p1 = pokemonsString.get(p);
        				StringBuilder strb = new StringBuilder(p1);
        				String p2 = strb.reverse().toString();
        				if(texto.contains(p1)) {
        					texto = texto.replaceAll(p1, "");
        					pokemonsString.remove(p);
        					break;
        				}
            			if(texto.contains(p2)) {
            				texto = texto.replaceAll(p2, "");
            				pokemonsString.remove(p);
            				break;
            			}
            		}
        		}
        		
        		
        		String res = "Case #"+i+": "+texto;
        		writer.println(res);
        		
        	}

        } catch (Exception e) {
        	System.out.println("Error with files:");
        	System.out.println(e.getMessage());
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		Challenge2 challenge = new Challenge2();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
