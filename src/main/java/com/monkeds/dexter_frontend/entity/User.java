package com.monkeds.dexter_frontend.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nick;
	private String email;
	private String password;
	private String state;
	
	private List<Room> roomList;
	
	public User(){
		
	}
	
	public User(String email,String password){
		this.email = email;
		this.password = password;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Room> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nick=" + nick + ", email=" + email
				+ ", password=" + password + ", state=" + state + ", roomList="
				+ roomList + "]";
	}
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", nick=" + nick + ", email=" + email
//				+ ", password=" + password + "]";
//	}
	
	
}
