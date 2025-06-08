package db_connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTable {
	
	/** 
	 * テーブル作成メソッド
	 * 
	 * @param tableName 希望のテーブル名
	 * 
	 * @return 作成結果メッセージ
	 * 
	**/
	
	public String createTable(String tableName) {
		
		// SQL定義
		
		String sql = "CREATE TABLE " + tableName + " (id SERIAL PRIMARY KEY, title VARCHAR(30) NOT NULL)";
				
		// 結果のメッセ―ジ初期化
		
		String resultMsg = "結果が代入されていません。";
		
		try (Connection con = DBConnect.getDB();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// SQL実行
			
			pstmt.executeUpdate();
			
			// 実行結果セット
				
			resultMsg = tableName + " テーブルの作成に成功しました!";
			
			
		} catch (SQLException e) {
			
			// 実行結果セット
			
			resultMsg = tableName + " テーブルの作成に失敗しました。 \r\n" + e.getMessage();
			
		}
		
		// 実行結果返す
		
		return resultMsg;
		
	}

}
