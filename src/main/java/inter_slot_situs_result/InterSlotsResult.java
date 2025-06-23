package inter_slot_situs_result;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InterSlotsDAO;
import dao.TimesGetDAO;
import dto.ReseSituDTO;
import dto.TimeSlotDTO;
import get_dates.GetDates;

/**
 * Servlet implementation class MsgShow
 */
@WebServlet("/InterSlotsResult")

public class InterSlotsResult extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    

	/**
	 * 
	 * post メソッドは禁止 
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("not_found/notFound.jsp").forward(request, response);
		
	}

	/**
	 * 
	 * テーブル作成の結果を返す処理
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字化け防止
		
		request.setCharacterEncoding("UTF-8");
		
		// 遷移先パスを定義
		
		String path = "result/interSlotsShow.jsp";
		

		// 対象面談実施者名
		
		String interName = "清川";
		
		// 予約枠状況の配列を受け取る
		
		InterSlotsDAO interSlotDAO = new InterSlotsDAO();
		
		ArrayList<ReseSituDTO> interSlotReses = interSlotDAO.getSlotSituations(interName);
		
		
		// テスト
		
		
		System.out.println("\n");
		
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		System.out.println("**** ここから面談実施者別、予約枠状況取得確認出力 ****");
		
		System.out.println("\n");
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		
		
		for (ReseSituDTO interSlotSitu : interSlotReses) {
			
			System.out.println(interSlotSitu.getID());
			
			System.out.println("\n");
			
			System.out.println(interSlotSitu.getStatus());
			
			System.out.println("\n");
			
			System.out.println(interSlotSitu.getInterID());
			
			System.out.println("\n");
			
			System.out.println(interSlotSitu.getTimeID());
			
			System.out.println("\n");
			
			System.out.println(interSlotSitu.getWeekday());
			
			System.out.println("\n");
			
			System.out.println(interSlotSitu.getStartTime());
			
			System.out.println("\n");
			
			System.out.println("-------------");
			
			System.out.println("\n");
			
		}
		
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		
		request.setAttribute("result_status_list", interSlotReses);

		
		
		// 時間枠一覧を受け取る
		
		TimesGetDAO timesGetDAO = new TimesGetDAO();
		
		ArrayList<TimeSlotDTO> timeSlotsList = timesGetDAO.getTimeSlots();
		
		request.setAttribute("result_time_list", timeSlotsList);
		
		
		// 現在から7日間の、日付を取得
		
		GetDates getDate = new GetDates();
		
		ArrayList<LocalDate> datesBefore = getDate.getDates();
		
		// 現在からの曜日群格納配列、定義
		
		ArrayList<String> dateWeeks = new ArrayList<>();
		
		for (LocalDate dateWeekBefore : datesBefore) {
			
			DayOfWeek dateWeekAfter = dateWeekBefore.getDayOfWeek();
			
			String dateWeekAfter2 = dateWeekAfter.toString();
			
			String dateWeekAfter3 = "初期";
			
		switch (dateWeekAfter2) {
			
			case "MONDAY":
				dateWeekAfter3 = "月";
				break;
			case "TUESDAY":
				dateWeekAfter3 = "火";
				break;
			case "WEDNESDAY":
				dateWeekAfter3 = "水";
				break;
			case "THURSDAY":
				dateWeekAfter3 = "木";
				break;
			case "FRIDAY":
				dateWeekAfter3 = "金";
				break;
			case "SATURDAY":
				dateWeekAfter3 = "土";
				break;
			case "SUNDAY":
				dateWeekAfter3 = "日";
				break;
			default:
				dateWeekAfter3 = "なし";
				break;
	
		}
			
			dateWeeks.add(dateWeekAfter3);
			
		}
		
		// テスト
		
		System.out.println("**** ここから表示用、曜日群取得確認出力 ****");
		
		System.out.println("\n");
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		
		for (String dateWeekAfter2 : dateWeeks) {
			
			System.out.println(dateWeekAfter2);
			
			System.out.println("\n");
			
			System.out.println("-------------");
			
			System.out.println("\n");
			
		}
		
		request.setAttribute("result_date_week_list", dateWeeks);
		
		
		
		
		
		
		// String型日付格納用配列、定義
		
		ArrayList<String> datesAfter = new ArrayList<>();
		

		
		// 取得した日付をストリング型へ。
		
		for (LocalDate dateBefore : datesBefore) {
			
			String dateAfter = dateBefore.toString();
			
			datesAfter.add(dateAfter);
			
			
		}
		
		
		// テスト
		
		System.out.println("**** ここから日付取得確認出力 ****");
		
		System.out.println("\n");
		
		System.out.println("-------------");
		
		System.out.println("\n");
		
		
		for (String date : datesAfter) {
			
			System.out.println(date);
			
			System.out.println("\n");
			
			System.out.println("-------------");
			
			System.out.println("\n");
			
		}
		
		
		int datesAfterSize = datesAfter.size(); 
		
		System.out.println(datesAfterSize);
		
		request.setAttribute("result_date_list", datesAfter);
		
		
		
		
		// 遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
	}

}
