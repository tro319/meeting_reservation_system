package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connect.DBConnect;
import dto.TimeSlotDTO;

public class StatusUpdateDAO {
	
	/*
	 * 
	 * 時間枠が埋まっていれば、statusをfalseにするメソッド
	 * 
	 * @param int interviewerName, String weekday 対象面談実施者の名前, 対象の曜日
	 * 
	 * @return String resultMsg 結果メッセージ
	 * 
	 */
	
	public String updateStatus(String weekday, String interName) {
		
		// 時間枠の数を取得。
		
		TimesGetDAO timesGetDAO = new TimesGetDAO();
		
		ArrayList<TimeSlotDTO> timeSlots = timesGetDAO.getTimeSlots();
		
		int timeSlotsCount = timeSlots.size();
		
		// SQL群定義
		
		String statusConfSQL = "SELECT reservations.id FROM reservations JOIN reservation_slots ON reservations.slot_id = reservation_slots.id JOIN interviewers ON reservations.interviewer_id = interviewers.id JOIN time_slots ON reservation_slots.time_id = time_slots.id WHERE reservation_slots.weekday = ? AND interviewers.name = ?";
		
		String updateSQL = "UPDATE reservation_slots SET status = false WHERE weekday = ?";
		
		// 結果格納変数
		
		String resultMsg = "なし";
		
		// 対象予約枠への、予約の件数を取得
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(statusConfSQL)) {
			
			pstmt.setString(1, weekday);
			pstmt.setString(2, interName);
			
			ResultSet confResult = pstmt.executeQuery();
			
			int confResultCount = 0;
			
			
			while (confResult.next()) {
				
				confResultCount += 1;
				
			}
			
			// 対象予約枠の予約件数と、既定の時間枠の枠数が一致しているか。
			
			if (confResultCount == timeSlotsCount) {
				
				// 一致していたら、更新処理実行
				
				try (PreparedStatement pstmt2 = con.prepareStatement(updateSQL)) {
					
					pstmt2.setString(1, weekday);
					
					int updateResultCount = pstmt2.executeUpdate();
					
					if (updateResultCount == timeSlotsCount) {
						
						resultMsg = "予約枠のstatus、更新成功！";
						
					} else {
						
						resultMsg = "予約枠のstatus、更新失敗";
						
					}
					
				} catch (SQLException e) {
					
					System.out.println("DBの接続に失敗しました。");
					
					System.out.println("DB接続エラーサブ: " + e.getMessage());
					
				}
				
			}
			
			
		} catch (SQLException e) {
			
			System.out.println("DBの接続に失敗しました。予約枠取得クラス");
			
			System.out.println("DB接続エラー: " + e.getMessage());
			
		}
		
		
		return resultMsg;
	}

}
