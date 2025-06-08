package result_show;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_connect.CreateTable;

/**
 * Servlet implementation class MsgShow
 */
@WebServlet("/CreateTable")
public class CreateShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * 
	 * get メソッドは禁止 
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("not_found/notFound.jsp").forward(request, response);
		
	}

	/**
	 * 
	 * テーブル作成の結果を返す処理 (post)
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response
	 * 
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 文字化け防止
		
		request.setCharacterEncoding("UTF-8");
		
		// 遷移先パスを定義
		
		String path = "result/createShow.jsp";
		
		// ユーザーからのテーブル名を取得
		
		String tableName = (String)request.getParameter("table_name");
		
		// 作成結果を受け取る
		
		CreateTable createTable = new CreateTable();
		
		String createResultMsg = createTable.createTable(tableName);
		
		// リクエストスコープへセット
		
		request.setAttribute("create_result", createResultMsg);
		
		// 遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
	}

}
