package main.java.mapper;

public class AdminSQL {
	
	public static final String SELECT_ADMIN_BY_LOGIN =
			"select * from admin where username = ? and admin_pw = ?";
}
