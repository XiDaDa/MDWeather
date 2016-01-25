package com.mdweather.app.adapter;

import java.util.List;

import com.mdweather.app.R;
import com.mdweather.app.model.Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeatherListViewAdapter extends BaseAdapter{
	 
    private LayoutInflater mInflater;
    private List<Weather> mWeatherData;
     
    public WeatherListViewAdapter(Context context, List<Weather> objects){
        this.mInflater = LayoutInflater.from(context);
        this.mWeatherData = objects;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
    	if(mWeatherData == null) {
    		return 0;
    	}
        return mWeatherData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         
        ViewHolder holder = null;
        if (convertView == null) {
             
            holder=new ViewHolder();  
             
            convertView = mInflater.inflate(R.layout.listview_item, null);
//          holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.time = (TextView)convertView.findViewById(R.id.time_tv);
            holder.city = (TextView)convertView.findViewById(R.id.city_tv);
            holder.weatheInfo = (TextView)convertView.findViewById(R.id.weather_tv); 
//          holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
            convertView.setTag(holder);
             
        }else {
             
            holder = (ViewHolder)convertView.getTag();
        }
         
         
//        holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
        holder.time.setText((String)mWeatherData.get(position).getTime());
        holder.city.setText((String)mWeatherData.get(position).getCity());
        holder.weatheInfo.setText((String)mWeatherData.get(position).getWeather() + " " +"温度："
        							+ (String)mWeatherData.get(position).getLow_Temperture() + " - " 
        							+ (String)mWeatherData.get(position).getHigh_Temperture()); 
//        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
//             
//            @Override
//            public void onClick(View v) {
//                showInfo();                 
//            }
//        });
         
         
        return convertView;
    }
     
    public final class ViewHolder{
//      public ImageView img;
      public TextView time;
      public TextView city;
      public TextView weatheInfo;    
//      public Button viewBtn;
  }

}
 
