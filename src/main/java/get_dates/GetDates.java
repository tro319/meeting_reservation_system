package get_dates;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;


public class GetDates {
	
	public ArrayList<LocalDate> getDates() {
		
		// 7日間の日付を取得する 配列定義
		
		ArrayList<LocalDate> dates = new ArrayList<>();
		
		// 現在の日付を取得
		
		LocalDate dateBefore = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		
		// 現在から7日間の日付を、配列に格納
		
		for (int i = 1; i < 8; i++) {

			LocalDate dateAfter = dateBefore.plusDays(i);
			
			dates.add(dateAfter);
	
			
		}
		
		
		// 配列を返す
		
		return dates;
		
	}
	
}


