package main.java.dto;

import java.sql.Timestamp;

public class AdminReplyDTO {
	private int replyId;
	private int inquiryId;
	private int adminId;
	private String content;
	private Timestamp replyDate;
	
	// Getter
	public int getReplyId() {
		return replyId;
	}
	
	public int getInquiryId() {
		return inquiryId;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public String getContent() {
		return content;
	}
	
	public Timestamp getReplyDate() {
		return replyDate;
	}
	
	// Setter
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
}
