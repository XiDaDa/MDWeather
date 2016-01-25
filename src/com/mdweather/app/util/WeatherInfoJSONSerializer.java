package com.mdweather.app.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import com.mdweather.app.model.Weather;

import android.content.Context;

public class WeatherInfoJSONSerializer {
	
	private Context mContext;
	private String mFileName;
	
	public WeatherInfoJSONSerializer(Context c, String f) {
		mContext = c;
		mFileName = f;
	}
	
	/*
	 * 保存Json 数据
	 */
	public void saveWeatherInfo(ArrayList<Weather> mWeatherInfo) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for(Weather w : mWeatherInfo) {
			array.put(w.toJSON());
		}
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	/*
	 *  读取Json 数据
	 */
	
	public ArrayList<Weather> loadWeatherInfo() throws IOException, JSONException {
		ArrayList<Weather>  weatherInfo = new ArrayList<Weather>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while(null != (line = reader.readLine())) {
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for(int i = 0; i < array.length(); i++) {
				weatherInfo.add(new Weather(array.getJSONObject(i)));	
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(null != reader) {
				reader.close();
			}
		}
		return weatherInfo;
	}

}
