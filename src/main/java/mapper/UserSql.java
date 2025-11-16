package main.java.mapper;

public class UserSql {
	// 会員登録
	public static final String INSERT_USER = 
	        "INSERT INTO users (username, password, name, name_furigana, email, created_at) VALUES (?, ?, ?, ?, ?, ?)";
	
	// 会員ID重複チェック
	public static final String CHECK_USER_ID_EXISTS =
			"SELECT COUNT(*) FROM users WHERE username = ?";
	
	// ログイン: ユーザーを検索するクエリ　
	public static final String SELECT_USER_BY_LOGIN =
			"select * from users where username = ? and password = ?";
	
	
	}
