package com.example.andrestcli.models;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.util.Log;

import com.example.andrestcli.R;
import com.example.andrestcli.listeners.OnFetchListener;

public class PostList extends AsyncBase {
	private ArrayList<HashMap<String, String>> data;
	
	public PostList(Context context, OnFetchListener listener) {
		super(context, listener);
		this.data = new ArrayList<HashMap<String, String>>();
	}

	@Override
	protected Long doInBackground(String... params) {
		// TODO Auto-generated method stub
		RestTemplate	rest;
		String url = this.context.getString(R.string.post_url);
		
		// Create a new RestTemplate instance
		rest = new RestTemplate();
		
		try {
			Log.i("App", "start");
			// Add the String message converter
			rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			
			Post posts[] = rest.getForObject(url,  Post[].class);
			
			this.data.removeAll(null);
			for (int i=0; i<posts.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("post_id", posts[i].getPost_id());
				map.put("time", posts[i].getTime());
				map.put("title", posts[i].getTitle());
				this.data.add(map);
			}
			Log.i("App", "end");
		}
		catch (Exception e) {
			Log.e("App", e.getMessage());
		}
		return null;
	}
	
	public ArrayList<HashMap<String, String>> getData() {
		return this.data;
	}
}
