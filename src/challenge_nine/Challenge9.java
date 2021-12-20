package challenge_nine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Challenge9 {
	
	private List<Integer[][]> typeSprites = new ArrayList<>();
	private MapSprite map;
	
	private void loadValues() throws IOException {

        File input = new File("./src/challenge_nine/submitInput.txt");
		File output = new File("./src/challenge_nine/submitOutput.txt");
		output.createNewFile();
	    PrintWriter writer = new PrintWriter("./src/challenge_nine/submitOutput.txt", "UTF-8");
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	int numberCases = Integer.parseInt(bf1.readLine());
        	int numberSprites = Integer.parseInt(bf1.readLine());
        	
        	int xMaxAllSprite = 0;
        	int yMaxAllSprite = 0;
        	
        	for(int i=0; i<numberSprites; i++) {
        		line1 = bf1.readLine();
        		int xMax = Integer.parseInt(line1.split(" ")[0]);
        		int yMax = Integer.parseInt(line1.split(" ")[1]);
        		Integer[][] sprite = new Integer[xMax][yMax];
        		for(int j=0; j<yMax; j++) {
        			String lineSprite = bf1.readLine();
        			for(int k=0; k<lineSprite.length(); k++) {
        				String value = lineSprite.substring(k,k+1);
        				sprite[k][j] = Integer.valueOf(value);
        			}	
        		}
        		typeSprites.add(sprite);
        		if(xMaxAllSprite<xMax) {
        			xMaxAllSprite = xMax;
        		}
        		if(yMaxAllSprite<yMax) {
        			yMaxAllSprite = yMax;
        		}
        	}
        	
        	for(int i=1; i<=numberCases; i++) {
        		int xMaxAllPositions = 0;
        		int yMaxAllPositions = 0;
        		map = new MapSprite();
        		line1 = bf1.readLine();
        		int movements = Integer.parseInt(line1);
        		for(int j=0; j<movements; j++) {
        			line1 = bf1.readLine();
        			String[] values = line1.split(" ");
        			Integer[] sprite = new Integer[3];
        			sprite[0] = Integer.parseInt(values[0]);
        			sprite[1] = Integer.parseInt(values[1]);
        			sprite[2] = Integer.parseInt(values[2]);
        			map.addSprite(sprite);
        			if(xMaxAllPositions<sprite[1]) {
        				xMaxAllPositions = sprite[1];
        			}
        			if(yMaxAllPositions<sprite[2]) {
        				yMaxAllPositions = sprite[2];
        			}
        		}
        		map.initializeMap(yMaxAllSprite+yMaxAllPositions);
        		map.setTypeSprites(typeSprites);
        		map.makeCollisions();
        		String result = String.valueOf(map.obtainCollisions());
        		String res = "Case #"+i+": "+result;
        		writer.print(res+"\n");
        		map = null;
        		System.gc();
        	}
    		
        } catch (Exception e) {
        	System.out.println(e);
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		long inicio = System.currentTimeMillis();
		Challenge9 challenge = new Challenge9();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long fin = System.currentTimeMillis();
		System.out.println((fin-inicio) +" milisegundos");
	}
}
