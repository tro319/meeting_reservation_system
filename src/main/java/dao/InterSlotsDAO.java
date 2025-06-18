package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connect.DBConnect;
import dto.ReseSituDTO;


public class InterSlotsDAO {
	
	/* 
	 * 
	 * 面談実施者で絞って、予約枠の状況を取得。
	 *
	 * @param String name 対象の面談実施者の名前
	 * 
	 * @return ArrayList<ReseSitu> 予約枠の状況を格納したリスト
	 * 
	 */
	
	public ArrayList<ReseSituDTO> getSlotSituations(String name) {
		
		// 予約枠の状況を格納する入れる定義
		
		ArrayList<ReseSituDTO> reseSlotSituations = new ArrayList<>();
		
		// SQL定義

		String sql = "SELECT * FROM reservation_slots JOIN interviewers ON reservation_slots.interviewer_id = interviewers.id WHERE interviewers.name = ? ORDER BY reservation_slots.id";
		
		// 予約枠の予約状況一覧を取得
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, name);
			
			ResultSet results = pstmt.executeQuery();
			
			while (results.next()) {
				
				int id = results.getInt("id");
				
				Boolean status = results.getBoolean("status");
				
				int interID = results.getInt("interviewer_id");
				
				int timeID = results.getInt("time_id");
				
				String weekday = results.getString("weekday");
				
				ReseSituDTO slotSituDTO = new ReseSituDTO(id, status, interID, timeID, weekday); 
				
				reseSlotSituations.add(slotSituDTO);
				
			}
			
			
			
		} catch (SQLException e) {
			
			System.out.println("DB接続エラー発生: " + e.getMessage());
			
		}
		
		return reseSlotSituations;
		
		
	}

}
