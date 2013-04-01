package com.example.andrestcli;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andrestcli.R;
import com.example.andrestcli.listeners.OnFetchListener;
import com.example.andrestcli.models.AsyncBase;
import com.example.andrestcli.models.Post;
import com.example.andrestcli.models.PostDetail;

public class PostActivity extends Activity {
	private OnFetchListener postFetch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		
		Intent intent = getIntent();
		Long postId = intent.getLongExtra("id", 0);
		
		this.prepareHandlers();
		
		PostDetail postDetail = new PostDetail(this, this.postFetch);
		postDetail.execute(postId.toString());
	}
	
	private void prepareHandlers() {
		this.postFetch = new OnFetchListener() {
			@Override
			public void onFetch(Context context, AsyncBase asyncTask) {
				PostActivity activity = (PostActivity)context;
				PostDetail postDetail = (PostDetail)asyncTask;
				
				TextView textView1 = (TextView)findViewById(R.id.textView1);
				TextView textView2 = (TextView)findViewById(R.id.textView2);
				TextView textView3 = (TextView)findViewById(R.id.textView3);
				Post post = postDetail.getData();
				
				textView1.setText(post.getTime());
				textView2.setText(post.getTitle());
				textView3.setText(post.getContent());
			}
		};
	}
}
