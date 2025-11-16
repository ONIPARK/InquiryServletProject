package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static final String user = "testuser";
    private static final String password = "testpass";
    private static final String dbserver = "jdbc:postgresql://localhost:5432/";
    private static final String dbname = "testdb";
    private static final String driverName = "org.postgresql.Driver";
    
    // データベース　接続
    public static Connection connect() throws Exception {
        try {
        	// System.out.println("✅ DBUtil.connect() 진입함");
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(dbserver + dbname, user, password);
            System.out.println("DB データベース　接続成功");
            return conn;
           
        } catch (Exception e) {
            System.out.println("DB データベース　接続　エラー");
            throw e;
        }
    }
    
    //　データベース　解除
    public static void disConnect(Connection conn) {
    	try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// エラーが発生しても無視する。ローグに記録
			System.out.println("DB接続終了中、エラー発生" + e.getMessage());
		}
    }
}
