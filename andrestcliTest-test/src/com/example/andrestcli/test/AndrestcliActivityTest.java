package com.example.andrestcli.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.ListView;

import com.example.andrestcli.AndrestcliActivity;
import com.example.andrestcli.R;
import com.example.andrestcli.listeners.OnFetchListener;
import com.example.andrestcli.models.AsyncBase;
import com.example.andrestcli.models.PostList;

// http://www.atmarkit.co.jp/fsmart/articles/androidtest01/01.html
// http://itinfo.main.jp/tan/?p=35
public class AndrestcliActivityTest extends ActivityInstrumentationTestCase2<AndrestcliActivity> {
	private AndrestcliActivity activity;
	
	// http://wavetalker.blog134.fc2.com/blog-entry-68.html
	// create CountDownLatch.
	final CountDownLatch signal = new CountDownLatch(1);
    
	public AndrestcliActivityTest(String pkg,
			Class<AndrestcliActivity> activityClass) {
		super(pkg, activityClass);
		// TODO Auto-generated constructor stub
	}
	
	public AndrestcliActivityTest() {
	    super("com.example.andrestcli", AndrestcliActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		activity = getActivity();
		Log.i("App", "setup");
	}
	
	public void testGetPostList() throws Exception {
		// 非同期処理完了時のハンドラーを定義
		OnFetchListener handlerForTest = new OnFetchListener() {
			@Override
			public void onFetch(Context context, AsyncBase asyncTask) {
				// unlock testcase's thread.
				signal.countDown();
			}
		};
		
		// create subclass of test target asynctask.
		PostList posts = new PostList(activity, handlerForTest);
		
		// execute asynctask.
		// AndrestcliActivityのOnCreate()で実行されているものとは別にもう一度実行する
		posts.execute("");
		
		// wait for asynctask.
		signal.await(30, TimeUnit.SECONDS);
		
		PostList result = activity.getPostList();
		assertNotNull("記事の一覧が取得できること。", result);
		assertEquals("取得した記事の件数が一致すること。", 8, result.getData().size());
		
		ListView listView1 = (ListView)activity.findViewById(R.id.listView1);
		assertNotNull("リストビューが取得できること。", listView1);
		assertEquals("リストビューのアイテム数が記事の数と一致すること。", 8, listView1.getCount());
	}
}
