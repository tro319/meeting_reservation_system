package rese;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EntryRese;
import dao.StatusUpdateOnlyDAO;

/**
 * Servlet implementation class EntryRese
 */
@WebServlet("/EntryRese")
public class ShowReseEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * 
	 * 予約登録制御
	 * 
	 * @param request HttpServeltRequest, response HttpServletResponse
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字化け防止
		
		request.setCharacterEncoding("UTF-8");
		
		// 遷移先設定
		
		String path = "rese/rese_finish.jsp";
		
		// ユーザーからの入力値取得
		
		int slotID = Integer.parseInt(request.getParameter("slot_id"));
		
		int interID = Integer.parseInt(request.getParameter("inter_id"));
		
		int userID = Integer.parseInt(request.getParameter("user_id"));
		
		String userDate = request.getParameter("rese_date");
		
		String week = request.getParameter("week");
		
		String interName = request.getParameter("inter_name_conf");
		
		
		EntryRese entryRese = new EntryRese();
		
		String resultMsg = entryRese.entryRese(slotID, interID, userID, userDate);
		
		// 予約枠状況更新
		
		StatusUpdateOnlyDAO statusUpdateOnlyDAO = new StatusUpdateOnlyDAO();
		
		String resultMsgUpdate = statusUpdateOnlyDAO.updateStatus(slotID);
		
		
		// 更新が成功したか
		
		System.out.println(week);
		
		System.out.println(interName);
		
		System.out.println(resultMsgUpdate);
		
		
		// 結果メッセージを遷移先へセット
		
		request.setAttribute("result_msg", resultMsg);
	
		
		
		// ページ遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request,  response);
		
		
		
		
	}

}
