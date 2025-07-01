package get_dates;

import java.time.LocalDate;
import java.time.ZoneId;

public class GetDate {

	public LocalDate getDate(int dateCount) {
		
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		
		int dateCountAfter = dateCount;
		
		LocalDate selectDay = null;
		
		switch (dateCountAfter) {
		
			case 0:
				selectDay = today.plusDays(0);
				break;
			case 1:
				selectDay = today.plusDays(1);
				break;
			case 2:
				selectDay = today.plusDays(2);
				break;
			case 3:
				selectDay = today.plusDays(3);
				break;
			case 4:
				selectDay = today.plusDays(4);
				break;
			case 5:
				selectDay = today.plusDays(5);
				break;
			case 6:
				selectDay = today.plusDays(6);
				break;
			
		
		}
		
		return selectDay;
		
	}
	
}
