package com.example.andrestcli;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.andrestcli.listeners.OnFetchListener;
import com.example.andrestcli.models.AsyncBase;
import com.example.andrestcli.models.PostList;

public class AndrestcliActivity extends Activity {
	private OnClickListener button1Click;
	private OnFetchListener postFetch;
	private AdapterView.OnItemClickListener listView1ItemClick;
	private PostList postList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_andrestcli);
		
		// イベントハンドラの準備
		this.prepareHandlers();
		
		// button1クリック時のハンドラをセット
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(this.button1Click);
		
		// 投稿一覧取得開始
		PostList posts = new PostList(this, this.postFetch);
		posts.execute("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_andrestcli, menu);
		return true;
	}

	private void prepareHandlers() {
		// button1クリック時
		this.button1Click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				AndrestcliActivity activity = (AndrestcliActivity)v.getContext();
				activity.alert("button clicked.");
			}
		};
		
		// 投稿一覧取得処理終了
		this.postFetch = new OnFetchListener() {
			@Override
			public void onFetch(Context context, AsyncBase asyncTask) {
				AndrestcliActivity activity = (AndrestcliActivity)context;
				PostList postList = (PostList)asyncTask;
				activity.setPostList(postList);
				
				ArrayList<HashMap<String, String>> data = postList.getData();
				
				SimpleAdapter sa= new SimpleAdapter(activity, data,
						R.layout.row, new String[]{"time", "title"},
						new int[]{R.id.textView1, R.id.textView2});
					
				ListView listView1 = (ListView)findViewById(R.id.listView1);
				listView1.setAdapter(sa);
				listView1.setOnItemClickListener(activity.getOnListItemClickListener());
			}
		};
		
		this.listView1ItemClick = new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				ListView listView = (ListView)parent;
				AndrestcliActivity activity = (AndrestcliActivity)listView.getContext();
				PostList postList = activity.getPostList();
				
				ArrayList<HashMap<String, String>> data = postList.getData();
				HashMap map = data.get(position);
				
				Intent intent = new Intent(activity, PostActivity.class);
//				intent.putExtra("id", parent.getItemIdAtPosition(position));
				intent.putExtra("id", Long.parseLong((String)map.get("post_id")));
				startActivity(intent);
			}
		};
	}
	
	public void alert(String message) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setTitle("information");
		dialogBuilder.setMessage(message);
		dialogBuilder.setPositiveButton("OK", null);
		
		 AlertDialog dialog = dialogBuilder.create();
		 dialog.show();
	}
	
	public AdapterView.OnItemClickListener getOnListItemClickListener() {
		return this.listView1ItemClick;
	}
	
	public void setPostList(PostList postList) {
		this.postList = postList;
	}
	
	public PostList getPostList() {
		return this.postList;
	}
}
