package parameter_show;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GetUserName;
import get_dates.GetDate;

@WebServlet("/ParamShow")
public class ParamShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	/**
	 * 
	 * 選択した予約枠情報を取得・遷移先への設定、を行うメソッド
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字化け防止
		
		request.setCharacterEncoding("UTF-8");
		
		// セッション初期化
		
		HttpSession session = request.getSession();
		
		// 遷移先パス、定義
		
		String path = "rese/rese_conf.jsp";
		
		// 予約枠の各値、パラメーターから取得
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String week = request.getParameter("week");
		
		String time = request.getParameter("time");
		
		int dateCount = Integer.parseInt(request.getParameter("date_count"));
		
		
		// セッションから、面談実施者名取得
		
		String interName = (String)session.getAttribute("inter_name");
		
		// 対象ユーザーid取得（仮)
		
		int userID = 1;
		
		GetUserName getUserName = new GetUserName();
		
		String userName = getUserName.getUserName(userID);
		
		System.out.println(userName);
		
		
		// 予約対象日付取得
		
		GetDate getDate = new GetDate();
		
		LocalDate reseDayBefore = getDate.getDate(dateCount);
		
		String reseDayAfter = reseDayBefore.toString();
		
		
		
		int interID = 1;
		
		
		
		
		// 予約枠の各値を、遷移先へ設定
		
		
		request.setAttribute("id", id);
		
		request.setAttribute("week", week);
		
		request.setAttribute("time", time);
		
		request.setAttribute("rese_date", reseDayAfter);
		
		request.setAttribute("inter_name_conf", interName);
		
		request.setAttribute("user_name_conf", userName);
		
		request.setAttribute("user_id",  userID);
		
		request.setAttribute("inter_id", interID);
		
		
		// 画面遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
	}



}
