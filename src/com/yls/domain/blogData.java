package com.yls.domain;

public class blogData {
	private String id;
	private String user_id;
	private String user_name;
	private String gender;
	private String pic_ids;
	private String title;
	private String year;
	private String month;
	private String date;
	private String day;
	private String time;
	private String text;
	private String tag_ids;
	private String tag_names;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPic_ids() {
		return pic_ids;
	}

	public void setPic_ids(String pic_ids) {
		this.pic_ids = pic_ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTag_ids() {
		return tag_ids;
	}

	public void setTag_ids(String tag_ids) {
		this.tag_ids = tag_ids;
	}

	public String getTag_names() {
		return tag_names;
	}

	public void setTag_names(String tag_names) {
		this.tag_names = tag_names;
	}


	public blogData() {
	}

	@Override
	public String toString() {
		return "blogData{" +
				"id='" + id + '\'' +
				", user_id='" + user_id + '\'' +
				", user_name='" + user_name + '\'' +
				", gender='" + gender + '\'' +
				", pic_ids='" + pic_ids + '\'' +
				", title='" + title + '\'' +
				", year='" + year + '\'' +
				", month='" + month + '\'' +
				", date='" + date + '\'' +
				", day='" + day + '\'' +
				", time='" + time + '\'' +
				", text='" + text + '\'' +
				", tag_ids='" + tag_ids + '\'' +
				", tag_names='" + tag_names + '\'' +
				'}';
	}
}
