package com.sehyeon.trainGood;

public class User {
	String _id;
	String id;
	String pw;
	String username;
	Integer point;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public User(String id, String pw, String username) {
		this.id = id;
		this.pw = pw;
		this.username = username;
		this._id = "";
		this.point = 0;
	}

	public User(String _id, String id, String pw, String username, Integer point) {
		this(id, pw, username);
		this._id = _id;
		this.point = point;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
