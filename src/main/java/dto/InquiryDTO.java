package main.java.dto;

import java.sql.Timestamp;

public class InquiryDTO {
    private int inquiryId;
    private int userId;
    private String subject;
    private String content;
    private String name;
    private String nameFurigana;
    private String email;
    private Timestamp createDate;
    private boolean hasReply;
    
    // Getters and Setters
    public int getInquiryId() {
    	return inquiryId; 
    	}
    public void setInquiryId(int inquiryId) {
    	this.inquiryId = inquiryId; 
    	}

    public int getUserId() {
    	return userId; 
    	}
    public void setUserId(int userId) {
    	this.userId = userId;
    	}

    public String getSubject() {
    	return subject; 
    	}
    public void setSubject(String subject) { 
    	this.subject = subject; 
    	}

    public String getContent() {
    	return content; 
    	}
    public void setContent(String content) { 
    	this.content = content; 
    	}

    public String getName() { 
    	return name; 
    	}
    public void setName(String name) { 
    	this.name = name; 
    	}

    public String getNameFurigana() { 
    	return nameFurigana; 
    	}
    public void setNameFurigana(String nameFurigana) { 
    	this.nameFurigana = nameFurigana; 
    	}

    public String getEmail() {
    	return email; 
    	}
    public void setEmail(String email) { 
    	this.email = email; 
    	}

    public Timestamp getCreateDate() { 
    	return createDate;
    	}
    public void setCreateDate(Timestamp createDate) { 
    	this.createDate = createDate; 
    	}
    
    public boolean isHasReply() {
    	return hasReply;
    }
    
    public void setHasReply(boolean hasReply) {
    	this.hasReply = hasReply;
    }
    
    /*
     * public Timestamp getReplyDate() {
		return replyDate;
	}
    
    public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}
     * 
     * */
    
}
