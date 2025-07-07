package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db_connect.DBConnect;

public class StatusUpdateOnlyDAO {
	
	/*
	 * 
	 * 時間枠が埋まっていれば、statusをfalseにするメソッド
	 * 
	 * @param int interviewerName, String weekday 対象面談実施者の名前, 対象の曜日
	 * 
	 * @return String resultMsg 結果メッセージ
	 * 
	 */
	
	public String updateStatus(int slotID) {

		
		// SQL定義

		String sql = "UPDATE reservation_slots SET status = false WHERE id = ?";
		
		
		
		// 結果格納変数
		
		String resultMsg = "なし";
		
		// 対象予約枠への、予約の件数を取得
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			

			pstmt.setInt(1, slotID);
			
			int resultCnt = pstmt.executeUpdate();
			
			if (resultCnt > 0) {
				
				resultMsg = "更新成功";
				
			}
			
			
		} catch (SQLException e) {
			
			System.out.println("DBの接続に失敗しました。予約枠取得クラス");
			
			System.out.println("DB接続エラー: " + e.getMessage());

			
		}
		
		
		
		
		return resultMsg;
	}

}
