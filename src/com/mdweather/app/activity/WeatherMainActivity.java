package com.mdweather.app.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.mdweather.app.R;
import com.mdweather.app.adapter.WeatherListViewAdapter;
import com.mdweather.app.model.Weather;
import com.mdweather.app.util.WeatherDataFetcth;
import com.mdweather.app.util.WeatherInfoJSONSerializer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WeatherMainActivity extends AppCompatActivity {
	
	private ArrayList<Weather> mWeatherData;
	private Toolbar mToolbar;
	private ListView mWeather_lv;
	private WeatherListViewAdapter mAdapter;
	private Handler handler = null;
	private WeatherInfoJSONSerializer mWeatherInfoJSONSerializer; 
	private String[] path = {"http://wthrcdn.etouch.cn/weather_mini?citykey=101190404",
							 "http://wthrcdn.etouch.cn/weather_mini?citykey=101010100"}; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity_main);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mToolbar.setTitle(""); //空白的 Title
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.drawable.ic_notification_content);
		mWeather_lv = (ListView) findViewById(R.id.weather_lv);
		mWeatherInfoJSONSerializer = new WeatherInfoJSONSerializer(this, "weather.json");
		try {
			mWeatherData = mWeatherInfoJSONSerializer.loadWeatherInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mWeatherData != null) {			
			BinderListData(mWeatherData);
			getWeatherInfo();
		}else{
			getWeatherInfo();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.weather_activity_main, menu);
		return true;
	}
	
 	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_search:
			return true;

		case R.id.action_share:
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
 	@Override
 	public void onPause() {
 		super.onPause();
 		try {
			mWeatherInfoJSONSerializer.saveWeatherInfo(mWeatherData);
			Log.d("TTT", "TTT" + "保存成功");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	
 	//获取天气信息
 	private void getWeatherInfo() {
 		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
					ArrayList<Weather> data = new ArrayList<Weather>();
					for(int i = 0; i < path.length; i++) {
						
						data.addAll(WeatherDataFetcth.getData(path[i]));						
					}
					mWeatherData = data;
					handler.sendMessage(handler.obtainMessage(0, mWeatherData));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		try {
			new Thread(runnable).start();
			handler = new Handler() {
				public void handleMessage(Message msg) {
					if (msg.what == 0) {
						// msg.obj是获取handler发送信息传来的数据
						@SuppressWarnings("unchecked")
						ArrayList<Weather> mWeatherData = (ArrayList<Weather>) msg.obj;					
						// 给ListView绑定数据
//						mAdapter.notifyDataSetChanged();
						BinderListData(mWeatherData);
					}
				}
			};
		}catch(Exception e) {
			e.printStackTrace();
		} 
 	}
 	
 	public void BinderListData(final ArrayList<Weather> mWeatherData) {
		// 创建adapter对象
		mAdapter = new WeatherListViewAdapter(this, mWeatherData);
		// 将Adapter绑定到listview中
		mWeather_lv.setAdapter(mAdapter);
		mWeather_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WeatherMainActivity.this, WeatherDetailActivity.class);
				intent.putExtra("Date", (String)mWeatherData.get(position).getTime());
				intent.putExtra("City", (String)mWeatherData.get(position).getCity());
				intent.putExtra("High", (String)mWeatherData.get(position).getHigh_Temperture());
				intent.putExtra("Low", (String)mWeatherData.get(position).getLow_Temperture());
				intent.putExtra("Weather", (String)mWeatherData.get(position).getWeather());
				startActivity(intent);
			}
		});
	}
 	
//	private void initData() {
//		mWeatherData = new ArrayList<Weather>();
//		
//		Weather hangzhou  = new Weather("", "18:00", "晴");
//		mWeatherData.add(hangzhou);
//		
//		Weather xiamen  = new Weather("xiamen", "18:00", "晴");
//		mWeatherData.add(xiamen);
//		
//		Weather zhuhai  = new Weather("zhuhai", "18:00", "晴");
//		mWeatherData.add(zhuhai);
//		
//		Weather HK  = new Weather("HK", "18:00", "晴");
//		mWeatherData.add(HK);
//	} 
//	
//	private class FetchWeatherDataTask extends AsyncTask<Void, Void, Void> {
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//			try {
//				result = new WeatherDataFetcth().getUrl("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100");
//				try {
//					mWeatherData_Obj = new JSONObject(result);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Log.i("TTT", "Fetched contents of URL: " + mWeatherData_Obj + "City： " + mWeatherData_Obj.optJSONObject("data").optString("city") );
//			}catch (IOException e) {
//				Log.e("TTT", "Failed to fetch URL: " + e);
//			}
//			return null;
//		}
//	}
}
