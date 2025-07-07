package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_connect.DBConnect;


public class GetUserName {
	
	public String getUserName(int userID) {
		
		// 処理結果変数定義
		
		String resultName = "該当担当者なし";
		
		String sql = "SELECT name FROM users WHERE id = ?";
		
		try (Connection con = DBConnect.getDB();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1,  userID);
			
			ResultSet result = pstmt.executeQuery();
			
			if (result.next()) {
				
				resultName = result.getString("name");
				
			}

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return resultName;
		 
	}
	
	
}

