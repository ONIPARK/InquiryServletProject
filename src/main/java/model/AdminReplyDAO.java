package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminReplyDTO;
import mapper.AdminReplySql;

public class AdminReplyDAO {
	
	// AdminReply と InquiryId 連結
	public static AdminReplyDTO getReplyById(int inquiryId) {
		//AdminReplyDTO reply = null;
		try (
			Connection conn = DBUtil.connect();
			PreparedStatement pstmt = conn.prepareStatement(AdminReplySql.SELECT_REPLYID);
			) {
			pstmt.setInt(1, inquiryId);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 問い合わせの詳細ページ
				AdminReplyDTO dto = new AdminReplyDTO();
				dto.setReplyId(rs.getInt("reply_id"));
				dto.setInquiryId(rs.getInt("inquiry_id"));
				dto.setAdminId(rs.getInt("admin_id"));
				dto.setContent(rs.getString("content"));
				dto.setReplyDate(rs.getTimestamp("reply_date"));
				System.out.println("getInquiryById接続　成功：" + dto.getInquiryId());
				
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
    // 該当Replyをサーバーで受け取る
    public static boolean insertReply(AdminReplyDTO dto) {
    	try (
    		Connection conn = DBUtil.connect();
        	PreparedStatement pstmt = conn.prepareStatement(AdminReplySql.INSERT_REPLY);
    		){
    		
    		pstmt.setInt(1, dto.getInquiryId());
    		pstmt.setInt(2, dto.getAdminId());
    		pstmt.setString(3, dto.getContent());
    		
    		return pstmt.executeUpdate() > 0;
    		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }	
}
