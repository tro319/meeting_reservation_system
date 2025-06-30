package get_dates;

import java.time.LocalDate;
import java.time.ZoneId;

public class GetDate {

	public LocalDate getDate(String week) {
		
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		
		String weekday = week;
		
		LocalDate selectDay = null;
		
		switch (weekday) {
		
			case "月":
				selectDay = today.plusDays(0);
				break;
			case "火":
				selectDay = today.plusDays(1);
				break;
			case "水":
				selectDay = today.plusDays(2);
				break;
			case "木":
				selectDay = today.plusDays(3);
				break;
			case "金":
				selectDay = today.plusDays(4);
				break;
			
		
		}
		
		return selectDay;
		
	}
	
}
