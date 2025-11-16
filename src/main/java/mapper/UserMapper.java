package main.java.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import jakarta.servlet.http.HttpServletRequest;

import dto.UserDTO;

public class UserMapper { // 메퍼는 단지 디티오에 저장하고 디비에 저장하는 역할만 하는 클래스
	
	// 【Register Insert文】
		 // 요청(request)로부터 데이터를 꺼내서 UserDTO 객체에 저장
		 public static UserDTO mapToDTO(HttpServletRequest req) {
			 	UserDTO dto = new UserDTO();
		        dto.setUsername(req.getParameter("username"));
		        dto.setPassword(req.getParameter("password"));
		        dto.setName(req.getParameter("name"));
		        dto.setNameFurigana(req.getParameter("nameFurigana"));
		        dto.setEmail(req.getParameter("email"));
		        return dto;
		 }
		 
		// DTO 객체에서 값을 꺼내 PreparedStatement에 넣음
		 public static void mapToPreparedStatement(PreparedStatement pstmt, UserDTO dto) throws SQLException {
			 	// pstmt.setInt(1, Integer.parseInt(request.getParameter("userId")));
			 	pstmt.setString(1, dto.getUsername());
			    pstmt.setString(2, dto.getPassword());
		        pstmt.setString(3, dto.getName());
		        pstmt.setString(4, dto.getNameFurigana());
		        pstmt.setString(5, dto.getEmail());
		        pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // 登録時間
		 } 
		 
		 
	// 【Login Select文】
		 // ユーザーログイン、必須項目(サーバーでもらう)
		 public static UserDTO mapToLoginDTO(HttpServletRequest req) {
			 UserDTO dto = new UserDTO();
			 dto.setUsername(req.getParameter("username"));
			 dto.setPassword(req.getParameter("password"));
			 System.out.println("ユーザーログイン　入力");
			 return dto;
		 }
		 
		 // dto값을 select문에 넣어서 비교하는 메서드
		 public static void mapToLoginPreparedStatement(PreparedStatement pstmt, UserDTO dto) throws SQLException {
			 pstmt.setString(1, dto.getUsername());
			 pstmt.setString(2, dto.getPassword());
		 }
		 /**
	     * [로그인] ResultSet으로부터 데이터를 꺼내어 UserDTO로 변환
	     * - DB 조회 결과(ResultSet)를 기반으로 UserDTO를 구성
	     * - 로그인 성공 시 사용자 정보를 세션에 저장하거나 화면에 출력할 때 사용
	     */
		 // 회원 정보를 프로그램에서 활용하기 위해 DB에서 다시 가져온다(세션에 저장하기 위해서)
		 public static UserDTO mapFromResultSet(ResultSet rs) throws SQLException {
			 UserDTO dto = new UserDTO();
			 dto.setUserId(rs.getInt("user_id"));
			 dto.setUsername(rs.getString("username"));
			 dto.setName(rs.getString("name"));
			 dto.setNameFurigana(rs.getString("name_furigana"));
			 dto.setEmail(rs.getString("email"));
			 
			 System.out.println("[LOGIN SUCCESS]ユーザー名:" + dto.getUsername());
			 System.out.println(dto.getName() + " / " + dto.getEmail());
			 return dto;
		 }
}
