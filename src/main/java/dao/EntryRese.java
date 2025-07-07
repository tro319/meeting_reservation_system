package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db_connect.DBConnect;

public class EntryRese {
	
	public String entryRese(int slotID, int interID, int userID, String reseDate) {
		
		//　結果メッセージ定義
		
		String resultMsg;
		
		String sql = "INSERT INTO reservations VALUES(NULL, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
		
		try (Connection con = DBConnect.getDB();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, slotID);
			
			pstmt.setInt(2, interID);
			
			pstmt.setInt(3, userID);
			
			pstmt.setString(4, reseDate);
			
			int resultCnt = pstmt.executeUpdate();
			
			if (resultCnt > 0) {
				
				resultMsg = "予約が完了しました";
				
			} else {
				
				resultMsg = "予約に失敗しました";
				
			}
			
		} catch (SQLException e) {
			
			resultMsg = "予約に失敗しました";
			
			e.printStackTrace();
			
			
		}
		
		return resultMsg;
		
	}

}
