package com.example.andrestcli.models;

import com.example.andrestcli.listeners.OnFetchListener;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncBase extends AsyncTask<String, Integer, Long> {
	protected Context context;
	protected OnFetchListener listener;

	public AsyncBase(Context context, OnFetchListener listener) {
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	protected void onPreExecute() {
		
	}
	
	@Override
	protected Long doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPostExecute(Long result) {
		this.listener.onFetch(this.context, this);
	}

}
