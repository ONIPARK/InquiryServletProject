package main.java.mapper;

public class AdminReplySql {
	// SELECT
	public static final String SELECT_REPLYID =
			"SELECT * FROM admin_reply WHERE inquiry_id = ?";
	
	// INSERT
	public static final String INSERT_REPLY =
			"INSERT INTO admin_reply (inquiry_id, admin_id, content) values (?, ? , ?)";
	
	// UPDATE	
	public static final String UPDATE_REPLY = 
		   "UPDATE inquiries SET reply = ?, reply_date = ? WHERE inquiry_id = ?";	
	// DELECT
}
