package com.mdweather.app.activity;

import com.mdweather.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class WeatherDetailActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	private TextView mToolbar_Title_tv; 
	private TextView mCurrent_Date_tv;
	private TextView mWeather_desp_tv;
	private TextView mTemp1_tv;
	private TextView mTemp2_tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity_detail);
		Intent intent = this.getIntent();
		String city = intent.getStringExtra("City");
		String high_Tep = intent.getStringExtra("High");
		String low_Tep = intent.getStringExtra("Low");
		String time =  intent.getStringExtra("Date");
		String weather = intent.getStringExtra("Weather");
		mToolbar = (Toolbar)findViewById(R.id.toolbar);
		mToolbar.setTitle("");
		setSupportActionBar(mToolbar);
//		mToolbar.setNavigationIcon(R.drawable.ic_notification_content);
		mToolbar_Title_tv = (TextView)findViewById(R.id.title_tv);
		mToolbar_Title_tv.setText(city);
		
		mCurrent_Date_tv = (TextView)findViewById(R.id.current_date);
		mCurrent_Date_tv.setText("2015年-12月-" + time);
		mWeather_desp_tv = (TextView)findViewById(R.id.weather_desp);
		mWeather_desp_tv.setText(weather);
		mTemp1_tv = (TextView)findViewById(R.id.temp1);
		mTemp1_tv.setText(low_Tep);
		mTemp2_tv = (TextView)findViewById(R.id.temp2);
		mTemp2_tv.setText(high_Tep);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.weather_activity_detail, menu);
		return true;
	}
}
