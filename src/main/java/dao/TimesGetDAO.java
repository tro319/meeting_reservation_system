package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db_connect.DBConnect;



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
	
	public ArrayList<Integer> getTimeSlots() {
		
		// SQL定義
		
		String sql = "SELECT start_time FROM time_slots";
		
		// 時間枠格納配列定義
		
		ArrayList<Integer> timeSlots = new ArrayList<>();
		
		// 時間枠要素格納変数定義
		
		Integer timeSlot = 0;
		
		try (Connection con = DBConnect.getDB();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ResultSet results = pstmt.executeQuery();
			
			while (results.next()) {
				
				timeSlot = results.getInt("start_time");
				
				timeSlots.add(timeSlot);
				
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return timeSlots;
		
	}

}
