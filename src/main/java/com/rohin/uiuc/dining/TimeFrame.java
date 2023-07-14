package com.rohin.uiuc.dining;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeFrame {
	long id;
	String hall;
	int time;
	int recent_write;
	int recent_read;
	String meal_time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
		
	}
	public int getRecent_write() {
		return recent_write;
	}
	public void setRecent_write(int recent_write) {
		this.recent_write = recent_write;
	}
	public int getRecent_read() {
		return recent_read;
	}
	public void setRecent_read(int recent_read) {
		this.recent_read = recent_read;
	}
	public String getMeal_time() {
		return meal_time;
	}
	public void setMeal_time(String meal_time) {
		this.meal_time = meal_time;
	}
}
