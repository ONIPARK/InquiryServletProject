package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.UserDTO;
import mapper.UserMapper;
import mapper.UserSql;

public class UserDAO { // 실제 데이터베이스를 접속하고 해제하는 역할, 실 데이터는 다루지 않고 접속 연결 해제만 담당한다.
	
	/*
	 * ID 重複チェック
	 * */
	public static boolean isUserIdExists(String username) {
		try (
			Connection conn = DBUtil.connect();	
			PreparedStatement pstmt = conn.prepareStatement(UserSql.CHECK_USER_ID_EXISTS);
			){
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("중복 체크할 username: " + username);
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 会員登録
	 * */
	public static boolean insertUser(UserDTO user) {
		
		try (
			Connection conn = DBUtil.connect();
			PreparedStatement pstmt = conn.prepareStatement(UserSql.INSERT_USER)
			) {
			UserMapper.mapToPreparedStatement(pstmt, user);
			
				return pstmt.executeUpdate() > 0;
			
		} catch (Exception e) {
			System.out.println("会員登録　入力中エラー発生");
			e.printStackTrace();
			return false;
		}
	}
	
	// static 이 아닌 이유는
	public UserDTO findUserByLogin(UserDTO dto) {
		try (
			// 1. DB 연결
			Connection conn = DBUtil.connect();
			// 2. 로그인 SQL 실행 준비
			PreparedStatement pstmt = conn.prepareStatement(UserSql.SELECT_USER_BY_LOGIN);	
			){
			// 3. SQL의 WHERE 조건에 입력값 바인딩 (id, password 등)
			UserMapper.mapToLoginPreparedStatement(pstmt, dto);
			// 4. 쿼리 실행
			ResultSet rs = pstmt.executeQuery();
			// 5. 결과가 있으면 (id, pw 일치), DTO로 매핑
			if (rs.next()) {
				return UserMapper.mapFromResultSet(rs);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
