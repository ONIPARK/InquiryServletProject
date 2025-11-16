package main.java.mapper;

public class InquirySql {
    
	// 問い合わせの作成
	public static final String INSERT_INQUIRY = 
	        "INSERT INTO inquiries (USER_ID, SUBJECT, CONTENT, NAME, NAME_FURIGANA, EMAIL, CREATEDATE) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	//　問い合わせの内容修正
	public static final String UPDATE_INQUIRY =
			"UPDATE inquiries SET subject = ?, content = ? WHERE inquiry_id = ?";
	
	//public static final String SELECT_ALL_INQUIRIES = 
	//        "SELECT inquiry_id, subject, name, createdate, reply FROM inquiries ORDER BY createdate DESC, inquiry_id DESC";

	public static final String SELECT_INQUIRY_BY_ID = 
	        "SELECT * FROM inquiries WHERE inquiry_id = ?";
	
	
	// 問い合わせの削除
	public static final String DELETE_INQUIRY =
			 "DELETE FROM inquiries WHERE inquiry_id = ?";
	
	// 問い合わせのリスト（投稿文数）
	public static final String SELECT_INQUIRY_COUNT =
			"SELECT count(*) FROM inquiries";
	
	// 問い合わせ　ページング処理
	public static final String SELECT_ALL_INQUIRIES =
			"SELECT i.inquiry_id, i.subject, i.name, i.createdate, EXISTS (SELECT 1 FROM admin_reply ar WHERE ar.inquiry_id = i.inquiry_id) AS has_reply FROM inquiries i ORDER BY i.createdate DESC, i.inquiry_id DESC LIMIT ? OFFSET ?";
	
}
