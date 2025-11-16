package main.java.dto;

import java.sql.Timestamp;

public class AdminDTO {
	private int adminId;
	private String username;
	private String password;
	private Timestamp createAd;
	
	// Getter
	public int getAdminId() {
		return adminId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Timestamp getCreateAd() {
		return createAd;
	}
	
	// Setter
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCreateAd(Timestamp createAd) {
		this.createAd = createAd;
	}
}
