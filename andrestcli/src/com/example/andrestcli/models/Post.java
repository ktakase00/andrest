package com.example.andrestcli.models;

public class Post {
	private Long post_id;
	private String time;
	private String title;
	private String content;
	
	public String getPost_id() {
		return this.post_id.toString();
	}
	
	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
