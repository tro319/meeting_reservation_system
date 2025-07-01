package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

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

		String sql = "SELECT * FROM reservation_slots JOIN interviewers ON reservation_slots.interviewer_id = interviewers.id JOIN time_slots ON reservation_slots.time_id = time_slots.id WHERE interviewers.name = ?";
		
		// 予約枠の予約状況一覧を取得
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, name);
			
			ResultSet results = pstmt.executeQuery();
			
			
			LocalDate today = LocalDate.now();
			
			DayOfWeek todayWeek = today.getDayOfWeek();
			
			String todayWeekBefore = todayWeek.toString();
			
			String todayWeekAfter = null;
			
		switch (todayWeekBefore) {
			
		case "MONDAY":
			todayWeekAfter = "月";
			break;
		case "TUESDAY":
			todayWeekAfter = "火";
			break;
		case "WEDNESDAY":
			todayWeekAfter = "水";
			break;
		case "THURSDAY":
			todayWeekAfter = "木";
			break;
		case "FRIDAY":
			todayWeekAfter = "金";
			break;
		case "SATURDAY":
			todayWeekAfter = "土";
			break;
		case "SUNDAY":
			todayWeekAfter = "日";
			break;
		default:
			todayWeekAfter = "なし";
			break;
	
		}
		
		
		
		
		
		
		HashMap<String, Integer> weekOrder = new HashMap<>();
		
	
		weekOrder.put("月", 0);
	
		weekOrder.put("火", 1);
		
		weekOrder.put("水", 2);
		
		weekOrder.put("木", 3);
		
		weekOrder.put("金", 4);
		
		weekOrder.put("土", 5);
		
		weekOrder.put("日", 6);
		
		
		int todayIndex = weekOrder.get(todayWeekAfter);
		
			
			while (results.next()) {
				
					
					int id = results.getInt("id");
					
					Boolean status = results.getBoolean("status");
					
					int interID = results.getInt("interviewer_id");
					
					int timeID = results.getInt("time_id");
					
					String weekday = results.getString("weekday");
					
					int startTime = results.getInt("start_time");
				
					
					int weekdayIndex = weekOrder.getOrDefault(weekday, 99);
					
					int relativeOrder = (weekdayIndex - todayIndex + 7) % 7;

					
					ReseSituDTO slotSituDTO = new ReseSituDTO(id, status, interID, timeID, weekday, startTime, relativeOrder); 
					
					reseSlotSituations.add(slotSituDTO);
					
			
				
			}
			
			
			// 現在曜日に合わせた、配列並び替え
			
			reseSlotSituations.sort(
					Comparator.comparingInt(ReseSituDTO::getOrderInt)
						.thenComparingInt(ReseSituDTO::getStartTime)
			);
			
			
			
			
			
			
			DBConnect.closeDB(con);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.out.println("DB接続エラー発生: " + e.getMessage());
			
		}
		
		return reseSlotSituations;
		
		
	}

}
