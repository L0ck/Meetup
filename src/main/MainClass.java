package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import collections.CityCollection;
import common.City;
import http.HttpClients;

public class MainClass {

	public static void main(String[] args) throws Exception {
		
		String url = "https://api.meetup.com/2/cities?country=rs&offset=0&format=json&photo-host=public&page=500&radius=50&order=size&desc=false&sig_id=266432987&sig=af8413c292f9980555fb8a6a02e6701ecaf0a716";
		showCities(HttpClients.getInstance().getResponse(url));	
		
		int i = 0;
		System.out.println("Izaberite grad (Dovoljno je da unesete redni broj grada))");
		
		boolean stop = true;
		while(stop) {
			try {				
				Scanner scann = new Scanner(System.in);
				i = scann.nextInt();
				stop = false;
			} catch (Exception e) {
				System.out.println("Unesite broj");				
			}
		}
		getEvents(i);
				
	}

	private static void showCities(JSONObject o) throws JSONException {
		JSONArray results = o.getJSONArray("results");
		CityCollection.getInstance().fill(results);
		String s = "";
		for (City city : CityCollection.getInstance().getArrayCity()) {
			int rb = city.getRanking()+1;
			s += rb + " - "+city.getName()+"\n";
		}
		System.out.println(s);
	}
	

	private static void getEvents(int ranking) throws Exception {
		int index = ranking - 1;
		float lat = CityCollection.getInstance().getArrayCity().get(index).getLat();
		float lon = CityCollection.getInstance().getArrayCity().get(index).getLon();
		
		String request = "https://api.meetup.com/find/upcoming_events?photo-host=public&sig_id=266432987&radius=3&lon="+lon+"&lat="+lat+"&sig=94d2a59634b3ff40224993ec415469e2d75a0437";
		JSONObject o = 	HttpClients.getInstance().getResponse(request);	
		JSONArray results = o.getJSONArray("events");
		
		for (int i = 0; i < results.length(); i++) {
			String name = results.getJSONObject(i).getString("name");
			String date = results.getJSONObject(i).getString("local_date");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(date);
			
			String description = results.getJSONObject(i).getString("description");
			
			String s  = "Naziv dogadjaja: "+name +"\n" + "Datum: " +d+  "\n" + "Opis: "+description + "\n";
			System.out.println(s);
		}
		
		
		
	}

}
