package collections;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import common.City;

public class CityCollection {
	
	private static CityCollection instance;
	private static ArrayList<City> arrayCity = new ArrayList<>();
	
	public static CityCollection getInstance() {
		if (instance == null) {
			return new CityCollection();
		}
		return instance;
	}
	
	public void fill(JSONArray results ) throws JSONException {
		for (int i = 0; i < results.length(); i++) {
			String name = results.getJSONObject(i).getString("city");
			int ranking = results.getJSONObject(i).getInt("ranking");
			float lat =  BigDecimal.valueOf(results.getJSONObject(i).getDouble("lat")).floatValue();
			float lon =  BigDecimal.valueOf(results.getJSONObject(i).getDouble("lon")).floatValue();
			
			City city = new City(name, ranking, lon, lat);
			addCity(city);
		}
	}
	
	public void addCity(City c){
		arrayCity.add(c);
	}
	
	public  ArrayList<City> getArrayCity() {
		return arrayCity;
	}
}
