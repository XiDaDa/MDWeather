package com.mdweather.app.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB 
{
	public static final String DB_NAME = "cool_weather";
	public static final int VERSION = 1;
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	private CoolWeatherDB(Context context)
	{
		
	}
}
