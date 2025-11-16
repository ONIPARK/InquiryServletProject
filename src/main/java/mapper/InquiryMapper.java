package main.java.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jakarta.servlet.http.HttpServletRequest;

import dto.InquiryDTO;

public class InquiryMapper {
	
	 // 【Insert文】
	 // 요청(request)로부터 데이터를 꺼내서 InquiryDTO 객체에 저장
	 public static InquiryDTO mapToDTO(HttpServletRequest req) {
	        InquiryDTO dto = new InquiryDTO();
	        dto.setSubject(req.getParameter("subject"));
	        dto.setContent(req.getParameter("content"));
	        dto.setName(req.getParameter("name"));
	        dto.setNameFurigana(req.getParameter("nameFurigana"));
	        dto.setEmail(req.getParameter("email"));
	        return dto;
	 }
	 
	// DTO 객체에서 값을 꺼내 PreparedStatement에 넣음
	 public static void mapToPreparedStatement(PreparedStatement pstmt, InquiryDTO dto) throws SQLException {
		 	// pstmt.setInt(1, Integer.parseInt(request.getParameter("userId")));
		 	pstmt.setInt(1, dto.getUserId());
		 	pstmt.setString(2, dto.getSubject());
		    pstmt.setString(3, dto.getContent());
	        pstmt.setString(4, dto.getName());
	        pstmt.setString(5, dto.getNameFurigana());
	        pstmt.setString(6, dto.getEmail());
	        pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // 등록 시간
	 } 
	 
	 // 【Select文】 ArrayList List 가져오기
	 public static InquiryDTO mapFromResultSet(ResultSet rs) throws SQLException {
		    InquiryDTO dto = new InquiryDTO();
		    dto.setInquiryId(rs.getInt("inquiry_id"));
		    dto.setSubject(rs.getString("subject"));
		    dto.setName(rs.getString("name"));
		    dto.setCreateDate(rs.getTimestamp("createdate"));
		    //System.out.println(dto.getCreateDate());
		    
		    return dto;
	} 
	
	 // 【Update用】 ---------------------------------------------------------------
	 
	 // サーバーで送信されたフォームデータ（HttpServletRequest）を受け取り、、InquiryDTOに値を設定する。
	 public static InquiryDTO mapToUpdateDTO(HttpServletRequest req) {
	        InquiryDTO dto = new InquiryDTO();
	              
	        dto.setInquiryId(safeParseInt(req.getParameter("inquiryId")));
	        dto.setSubject(req.getParameter("subject"));
	        dto.setContent(req.getParameter("content"));
	        System.out.println("Update InquiryId: " + dto.getInquiryId());
	        return dto;
	 }
	 
	 // Integer.parseInt（）：例外処理
	 private static int safeParseInt(String param) {
		 try {
			return Integer.parseInt(param);			
		} catch (NumberFormatException e) {
			return 0; // 又は,-1
		}
	 }
	 
	 // PreparedStatementにパラメータを設定する
	 public static void bindUpdateParams(PreparedStatement pstmt, InquiryDTO dto) throws SQLException {
		 pstmt.setString(1, dto.getSubject());
		 pstmt.setString(2, dto.getContent());
		 pstmt.setInt(3, dto.getInquiryId());
	 }
	 
	 // ----------------------------------------------------------------------------
	 
	 
	// 【Select文】　問い合わせの詳細ページ
	 public static InquiryDTO mapFromResultSetFull(ResultSet rs) throws SQLException {
		    InquiryDTO dto = new InquiryDTO();
		    dto.setInquiryId(rs.getInt("inquiry_id"));
		    // 새로 추가 ============================================
		    dto.setUserId(rs.getInt("user_id"));
		    // ====================================================
		    dto.setSubject(rs.getString("subject"));
		    dto.setContent(rs.getString("content"));
		    dto.setName(rs.getString("name"));
		    dto.setNameFurigana(rs.getString("name_furigana"));
		    dto.setEmail(rs.getString("email"));
		    dto.setCreateDate(rs.getTimestamp("createdate"));
		  //  dto.setReply(rs.getString("reply"));
		  //  dto.setReplyDate(rs.getTimestamp("reply_date"));
		    return dto;
		}
	 
	 // 【Select文】　Reply ページ
	 public static void mapToReplyUpdate(PreparedStatement pstmt, String reply, Timestamp replyDate, int inquiryId) throws SQLException {
		    pstmt.setString(1, reply);
		    pstmt.setTimestamp(2, replyDate);
		    pstmt.setInt(3, inquiryId);
	}
}
