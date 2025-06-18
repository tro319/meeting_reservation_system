package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connect.DBConnect;

public class StatusUpdateDAO {
	
	/*
	 * 
	 * 時間枠が埋まっていれば、statusをfalseにするメソッド
	 * 
	 * @param int interviewerName, String weekday 対象面談実施者の名前, 対象の曜日
	 * 
	 * @return なし
	 * 
	 */
	
	public void updateStatus(String interviewerName, String weekday) {
		
		
		TimesGetDAO timesGetDAO = new TimesGetDAO();
		
		ArrayList<Integer> timeSlots = timesGetDAO.getTimeSlots();
		
		int timeSlotsCount = timeSlots.size();
		
		// SQL群定義
		
		String statusConfSQL = "SELECT COUNT(reservations.id) FROM reservations, reservation_slots, interviewers, time_slots WHERE reservations.interviewer_id = reservation_slots.interviewer_id AND reservation_slots.time_id = time_slots.id AND reservations.interviewers_id = interviewers.id reservation_slots.weekday = ? AND interviewers.name = ?";
		
		String updateSQL = "UPDATE reservation_slots SET status = false WHERE weekday = ?";
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(statusConfSQL)) {
			
			pstmt.setString(1, weekday);
			pstmt.setString(2, interviewerName);
			
			int confResultCount = pstmt.executeUpdate();
			
			if (confResultCount == timeSlotsCount) {
				
				try (PreparedStatement pstmt2 = con.prepareStatement(updateSQL)) {
					
					pstmt2.setString(1, weekday);
					
					int updateResultCount = pstmt2.executeUpdate();
					
					if (updateResultCount == timeSlotsCount) {
						
						System.out.println("予約枠のstatus、更新成功！");
						
					} else {
						
						System.out.println("予約枠のstatus、更新失敗");
						
					}
					
				} catch (SQLException e) {
					
					System.out.println("DBの接続に失敗しました。");
					
					System.out.println("DB接続エラー: " + e.getMessage());
					
				}
				
			}
			
			
		} catch (SQLException e) {
			
			System.out.println("DBの接続に失敗しました。");
			
			System.out.println("DB接続エラー: " + e.getMessage());
			
		}
		
	}

}
