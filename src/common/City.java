package common;

public class City {

	private String name;
	private int ranking;
	private float lon, lat;
	
	public City(String name, int ranking, float lon, float lat) {
		this.name = name;
		this.ranking = ranking;
		this.lon = lon;
		this.lat = lat;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public float getLon() {
		return lon;
	}
	
	public float getLat() {
		return lat;
	}
}
