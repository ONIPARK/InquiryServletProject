package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.AdminDTO;
import mapper.AdminSQL;

public class AdminDAO {
	
	
	public AdminDTO findAdminByLogin(AdminDTO dto) {
		try (
			Connection conn = DBUtil.connect();
			PreparedStatement pstmt = conn.prepareStatement(AdminSQL.SELECT_ADMIN_BY_LOGIN);
			){
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setAdminId(rs.getInt("admin_id"));
				dto.setUsername(rs.getString("username"));
				
				System.out.println("[LOGIN SUCCESS]Admin: " + dto.getUsername());
				
				return dto;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
