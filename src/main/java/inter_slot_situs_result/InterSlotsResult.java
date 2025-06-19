package inter_slot_situs_result;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InterSlotsDAO;
import dto.ReseSituDTO;

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
		
		// 配列を受け取る
		
		InterSlotsDAO interSlotDAO = new InterSlotsDAO();
		
		ArrayList<ReseSituDTO> interSlotReses = interSlotDAO.getSlotSituations(interName);
		
		
		// 配列中身表示テスト（一時的に、コンソールに表示）
		
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
			
			System.out.println("-------------");
			
			System.out.println("\n");
			
		}
		
		
		request.setAttribute("result_msg", "成功しました！");
		
		
		
		
		// 遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		rd.forward(request, response);
		
		
	}

}
