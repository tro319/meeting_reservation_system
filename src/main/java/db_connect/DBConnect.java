package db_connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;


public class DBConnect extends HttpServlet {
	
	private DBConnect() {
		
	}
	
	// 返すコネクションオブジェクト
	
	Connection con = null;
	
	private static final String URL = "jdbc:mysql://localhost:3307/meeting_reservation_sys";
	private static final String USERNAME = "root";
	private static final String PASS = "ktcpass25";
	
	/** 
	 * DB接続メソッド
	 * 
	 * @return 接続インスタンス
	 * 
	**/
	
	public static Connection getDB() throws SQLException {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
			throw new SQLException("MySQL ドライバ が見つかりません。", e);
			
		}
		
		return DriverManager.getConnection(URL, USERNAME, PASS);
		
	}
	
	
	/** 
	 * DB接続を閉じるメソッド
	 * 
	 * @param conn 接続インスタンス
	 * 
	**/
	
	public static void closeDB(Connection conn) {
		
		try {
			
			if (conn != null) {
				
				conn.close();
				
			}
			
		} catch (SQLException ignored) {
			
			
		}
		
	}
       

}
