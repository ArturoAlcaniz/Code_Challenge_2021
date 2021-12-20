package challenge_eight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Challenge8 {
	
	private HashMap<String, City> cities =  new HashMap<>();

	private Graph loadGraphWithCities() {
		Graph graph = new Graph();
		for(City c : cities.values()) {
			graph.addVertex(c.getNombre());
		}
		for(City c : cities.values()) {
			for(String conn : c.getCitiesConnected()) {
				graph.addEdge(c.getNombre(), conn);
			}
		}
		return graph;
	}
	
	private List<String> getCriticalCitiesThatCannotBeRemoved(HashMap<String, City> cities) {
		List<String> criticalCities = new ArrayList<>();
		Collection<City> citiesLista =  cities.values();
		for(City cityToCheck : citiesLista) {
			Graph graphcopy = loadGraphWithCities();
			graphcopy.removeVertex(cityToCheck.getNombre());
			if(graphcopy.hasVertexCut()) {
				criticalCities.add(cityToCheck.getNombre());
			}
		}
		return criticalCities;
	}
	
	private void loadValues() throws IOException {

        File input = new File("./src/challenge_eight/submitInput.txt");
		File output = new File("./src/challenge_eight/submitOutput.txt");
		output.createNewFile(); // if exists will do nothing 
	    PrintWriter writer = new PrintWriter("./src/challenge_eight/submitOutput.txt", "UTF-8");
        
        try (FileReader fr1 = new FileReader(input);
             BufferedReader bf1 = new BufferedReader(fr1)) {
        	String line1;
        	
        	int numberCases = Integer.parseInt(bf1.readLine());
        	
        	for(int i=1; i<=numberCases; i++) {
        		cities =  new HashMap<String, City>();
        		line1 = bf1.readLine();
        		int numberConnections = Integer.parseInt(line1);
        		for(int j = 0; j<numberConnections; j++) {
        			String connection = bf1.readLine();
        			String[] connectionArray = connection.split(",");
        			String city1Nombre = connectionArray[0];
        			String city2Nombre = connectionArray[1];
        			if(cities.containsKey(city1Nombre)) {
        				City city1 = cities.get(city1Nombre);
        				city1.addCityConnected(city2Nombre);
        				cities.replace(city1Nombre, city1);
        			}else {
        				City city1 = new City(city1Nombre);
        				city1.addCityConnected(city2Nombre);
        				cities.put(city1Nombre, city1);
        			}
        			if(cities.containsKey(city2Nombre)) {
        				City city2 = cities.get(city2Nombre);
        				city2.addCityConnected(city1Nombre);
        				cities.replace(city2Nombre, city2);
        			}else {
        				City city2 = new City(city2Nombre);
        				city2.addCityConnected(city1Nombre);
        				cities.put(city2Nombre, city2);
        			}
        			if(j == 0) {
        			}
        			if(j == numberConnections-1) {
        			}
        		}
        		        		
        		List<String> criticalCities = getCriticalCitiesThatCannotBeRemoved(cities);
        		Collections.sort(criticalCities);

        		String result = criticalCities.stream()
        			      .map(n -> String.valueOf(n))
        			      .collect(Collectors.joining(",", "", ""));
        		if(criticalCities.isEmpty()) {
        			result = "-";
        		}
        		String res = "Case #"+i+": "+result;
        		try {
        			
        			writer.print(res+"\n");
        		} catch (Exception e) {
        			
        			writer.print(res+"\n");
        		}
        	}
    		
        } catch (Exception e) {
        	System.out.println("Error with files:");
        	System.out.println(e.getMessage());
        }
        writer.close();
    }
	
	public static void main (String [] args) {
		Challenge8 challenge = new Challenge8();
		try {
			challenge.loadValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
