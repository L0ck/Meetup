package http;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


public class HttpClients {
	
	private static HttpClients instance;

	
	public static HttpClients getInstance() {
		if (instance == null) {
			return new HttpClients();
		}
		return instance;
	}
	
	public JSONObject getResponse(String url) throws Exception {
	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();	
		
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return new JSONObject(response.toString());
		
	}
	
}
