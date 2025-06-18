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
		
		String interviewerName = "吉川";
		
		// 対象曜日
		
		LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
		
		DayOfWeek dayOfWeek = today.getDayOfWeek();
		
		System.out.println(dayOfWeek);
		
		
		
		// 遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
	}

}
