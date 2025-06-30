package parameter_show;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// 遷移先パス、定義
		
		String path = "rese/rese_form.jsp";
		
		// 予約枠の各値、パラメーターから取得
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String week = request.getParameter("week");
		
		String time = request.getParameter("time");
		
		int dateCount = Integer.parseInt(request.getParameter("date_count"));
		
		// 予約枠の各値を、遷移先へ設定
		
		
		request.setAttribute("id", id);
		
		request.setAttribute("week", week);
		
		request.setAttribute("time", time);
		
		request.setAttribute("date_count", dateCount);
		
		// 画面遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
	}



}
