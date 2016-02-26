package com.monkeds.dexter_frontend.entity;

import java.io.Serializable;

public class Room implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userid_1;
	private String userid_2;
	private String userid_creator;
	private String state;
	
//	@Transient
	private String user_display;
	private User userDisplay;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid_1() {
		return userid_1;
	}
	public void setUserid_1(String userid_1) {
		this.userid_1 = userid_1;
	}
	public String getUserid_2() {
		return userid_2;
	}
	public void setUserid_2(String userid_2) {
		this.userid_2 = userid_2;
	}
	public String getUserid_creator() {
		return userid_creator;
	}
	public void setUserid_creator(String userid_creator) {
		this.userid_creator = userid_creator;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setUser_display(String user) {
		if(getUserid_1().equals(user)){
			this.user_display= getUserid_2();
		}else if(getUserid_2().equals(user)){
			this.user_display= getUserid_1();
		}else{
			this.user_display= "NOT_FOUND";
		}
	}
	public String getUser_display() {
		return user_display;
	}
	public User getUserDisplay() {
		return userDisplay;
	}
	public void setUserDisplay(User userDisplay) {
		this.userDisplay = userDisplay;
	}
	
	
	
}
