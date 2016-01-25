package com.mdweather.app.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.mdweather.app.model.Weather;

import android.util.Log;

public class WeatherDataFetcth {
	
	private static ArrayList<Weather> mWeatherData = null;
	public static ArrayList<Weather> getData(final String path) {
		Weather weather = null; 
		String city;
		String weather_Info = "test";
		String time = "test";
		String low_Tem = "test";
		String high_Tem = "test";
		try {
			String s = getUrl(path);;
			mWeatherData = new ArrayList<Weather>();
			try {
				JSONObject obj = new JSONObject(s);
				Log.d("TTT", "TTT: " + obj);
				weather = new Weather(); 
				city = obj.optJSONObject("data").optString("city");
				JSONObject forecast_Info = obj.optJSONObject("data").optJSONArray("forecast").optJSONObject(0);
				for(int i = 0; i < 5; i++) {
					if(i == 0) {
						if(forecast_Info.has("type")) {					
							weather_Info = forecast_Info.optString("type"); 
						}
						if(forecast_Info.has("date")) {
							time = forecast_Info.optString("date").substring(0,3);							
						}
						if(forecast_Info.has("high")) {
							high_Tem = forecast_Info.optString("high");
						}
						if(forecast_Info.has("low")) {
							low_Tem = forecast_Info.optString("low");
						}
					}
				}
				weather.setCity(city);
				weather.setWeather(weather_Info);
				weather.setTime(time);
				weather.setLow_Temperture(low_Tem);
				weather.setHigh_Temperture(high_Tem);
				mWeatherData.add(weather);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mWeatherData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mWeatherData;	
	}
	
	public static String getUrl(String urlSpec) throws IOException {
		
			URL url = new URL(urlSpec);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			try {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				InputStream in = connection.getInputStream();
				if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					return null;
				}
				int bytesRead = 0;
				byte[] buffer = new byte[2048];
				while((bytesRead = in.read(buffer)) > 0) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				return new String(out.toByteArray());
			}finally {
				connection.disconnect();
			}
		}

}
