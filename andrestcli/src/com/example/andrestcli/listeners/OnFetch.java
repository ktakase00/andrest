package com.example.andrestcli.listeners;

import android.content.Context;

import com.example.andrestcli.models.AsyncBase;

public interface OnFetch {
	void onFetch(Context context, AsyncBase asyncTask);
}
