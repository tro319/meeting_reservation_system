package status_update_result;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StatusUpdateDAO;

/**
 * Servlet implementation class MsgShow
 */
@WebServlet("/UpdateSlotStatus")

public class StatusUpdateResult extends HttpServlet {
	
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
		
		String path = "result/updateStatusShow.jsp";
		

		// 対象面談実施者名
		
		String interName = "清川";
		
		// 対象曜日（現在の）
		
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		
		// switch用に、判断する変数をString型に変換
		
		String dayOfWeekBefore = dayOfWeek.toString();
		
		
		// 曜日格納 (日本語版)

		
		String dayWeek = "";
		
		switch (dayOfWeekBefore) {
			
			case "MONDAY":
				dayWeek = "月";
				break;
			case "TUESDAY":
				dayWeek = "火";
				break;
			case "WEDNESDAY":
				dayWeek = "水";
				break;
			case "THURSDAY":
				dayWeek = "木";
				break;
			case "FRIDAY":
				dayWeek = "金";
				break;
			case "SATURDAY":
				dayWeek = "土";
				break;
			case "SUNDAY":
				dayWeek = "日";
				break;
			default:
				dayWeek = "なし";
				break;
	
		}
		
		// 予約枠 status 更新処理
		
		
		StatusUpdateDAO statusUpdateDAO = new StatusUpdateDAO();
		
		String resultMsg = statusUpdateDAO.updateStatus(dayWeek, interName);
		
		request.setAttribute("result_msg", resultMsg);
		
		
		
		
		
		// 遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
	}

}
