package challenge_eight;

import java.util.ArrayList;
import java.util.List;

public class City {
	
	private String nombre;
	private List<String> citiesConnected;
	
	public City(String nombre) {
		this.nombre = nombre;
		this.citiesConnected = new ArrayList<>();
	}
	
	public void addCityConnected(String city) {
		if(!this.citiesConnected.contains(city)) {
			this.citiesConnected.add(city);
		}
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public boolean isConnectedWith(City city) {
		return citiesConnected.contains(city.getNombre());
	}
	
	public List<String> getCitiesConnected() {
		return this.citiesConnected;
	}
}
