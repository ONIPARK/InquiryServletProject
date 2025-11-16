package main.java.dto;

import java.sql.Timestamp;

public class UserDTO {
	private int userId;
	private String username;
	private String password;
	private String name;
	private String nameFurigana;
	private String email;
	private Timestamp createIdDate;
	
	// Getters
	public int getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getNameFurigana() {
		return nameFurigana;
	}
	public String getEmail() {
		return email;
	}
	public Timestamp getCreateIdDate() {
		return createIdDate;
	}
	
	// Setters
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNameFurigana(String nameFurigana) {
		this.nameFurigana = nameFurigana;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCreateIdDate(Timestamp createIdDate) {
		this.createIdDate = createIdDate;
	}
}
