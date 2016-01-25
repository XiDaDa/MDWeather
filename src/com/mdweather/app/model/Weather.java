package com.mdweather.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

	public final static String CITY = "cityname";
	public final static String TIME = "time";
	public final static String LOW_TEM = "Low_Temperture";
	public final static String High_TEM = "High_Temperture";
	public final static String WEATHER = "weather";
	
	private String mCity;
	private String mTime;
	private String mWeather;
	private String mLow_Temperture;
	private String mHigh_Temperture;
	private String mDirection_Wind;
	private String mLevel_Wind;
	
	public Weather() {
		
	}
	
	public Weather(String title, String time, String weather) {
		mCity = title;
		mTime = time;
		mWeather = weather;
	}
	
	public Weather(JSONObject jsonObject) {
		// TODO Auto-generated constructor stub
		if(jsonObject.has(CITY)) {
			mCity = jsonObject.optString(CITY);
		}
		if(jsonObject.has(TIME)) {
			mTime = jsonObject.optString(TIME);
		}
		if(jsonObject.has(High_TEM)) {
			mHigh_Temperture = jsonObject.optString(High_TEM);
		}
		if(jsonObject.has(LOW_TEM)) {
			mLow_Temperture = jsonObject.optString(LOW_TEM);
		}
	}

	public String getLow_Temperture() {
		return mLow_Temperture;
	}

	public void setLow_Temperture(String low_Temperture) {
		mLow_Temperture = low_Temperture;
	}

	public String getHigh_Temperture() {
		return mHigh_Temperture;
	}

	public void setHigh_Temperture(String high_Temperture) {
		mHigh_Temperture = high_Temperture;
	}

	public String getDirection_Wind() {
		return mDirection_Wind;
	}

	public void setDirection_Wind(String direction_Wind) {
		mDirection_Wind = direction_Wind;
	}

	public String getLevel_Wind() {
		return mLevel_Wind;
	}

	public void setLevel_Wind(String level_Wind) {
		mLevel_Wind = level_Wind;
	}

	public String getCity() {
		return mCity;
	}
	
	public void setCity(String title) {
		mCity = title;
	}
	
	public String getTime() {
		return mTime;
	}
	
	public void setTime(String time) {
		mTime = time;
	}
	
	public String getWeather() {
		return mWeather;
	}
	
	public void setWeather(String weather) {
		mWeather = weather;
	}

	public JSONObject toJSON() throws JSONException {
		
		JSONObject json = new JSONObject();	
		json.put(CITY, mCity);
//		json.put(High_TEM, mHigh_Temperture);
//		json.put(LOW_TEM, mLow_Temperture);
//		json.put(TIME, mTime);
//		json.put(WEATHER, mWeather);
		return json;	
	}
	
}
