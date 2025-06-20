package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connect.DBConnect;
import dto.TimeSlotDTO;



/**
 * 時間枠一覧を取得する
 */

public class TimesGetDAO {
	
	/**
	 * 
	 * 全ての時間枠を格納した配列を、取得するメソッド
	 * 
	 * @param なし
	 * @return ArrayList<Integer>
	 * 
	 */
	
	public ArrayList<TimeSlotDTO> getTimeSlots() {
		
		// SQL定義
		
		String sql = "SELECT * FROM time_slots";
		
		// 時間枠格納配列定義
		
		ArrayList<TimeSlotDTO> timeSlots = new ArrayList<>();
	
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ResultSet results = pstmt.executeQuery();
			
			while (results.next()) {
				
				int id = results.getInt("id");
				
				int startTime = results.getInt("start_time");
				
				TimeSlotDTO timeSlotDto = new TimeSlotDTO(id, startTime);
				
				timeSlots.add(timeSlotDto);
				
			}
			
			DBConnect.closeDB(con);
			
			
		} catch (SQLException e) {
			
		}
		
		return timeSlots;
		
	}

}
