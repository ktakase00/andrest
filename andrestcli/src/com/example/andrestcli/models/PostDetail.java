package com.example.andrestcli.models;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.andrestcli.R;
import com.example.andrestcli.listeners.OnFetchListener;

import android.content.Context;
import android.util.Log;

public class PostDetail extends AsyncBase {
	private Post data;

	public PostDetail(Context context, OnFetchListener listener) {
		super(context, listener);
		this.data = new Post();
	}
	
	@Override
	protected Long doInBackground(String... params) {
		// TODO Auto-generated method stub
		RestTemplate	rest;
		String url = this.context.getString(R.string.post_url) + params[0];
		
		// Create a new RestTemplate instance
		rest = new RestTemplate();
		
		try {
			Log.i("App", "start");
			// Add the String message converter
			rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			
			this.data = rest.getForObject(url,  Post.class);
			Log.i("App", "end");
		}
		catch (Exception e) {
			Log.e("App", e.getMessage());
		}
		return null;
	}

	public Post getData() {
		return this.data;
	}
}
