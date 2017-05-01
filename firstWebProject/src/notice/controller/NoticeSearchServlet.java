package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/nsearch")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 검색 서비스 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String itemValue = request.getParameter("item");
		ArrayList<Notice> list = null;
		try{
			switch(itemValue){
			case "title":
				String keyword = request.getParameter("keyword");
				list = new NoticeService().selectTitleSearch(keyword);
				break;
			case "writer":
				String writer = request.getParameter("keyword");
				list = new NoticeService().selectWriterSearch(writer);
				break;
			case "date":
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				//System.out.println(start);
				list = new NoticeService().selectDateSearch(start, end);
				break;
			}
			
			RequestDispatcher view = request.getRequestDispatcher("view/notice/noticeListView.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
			
		}catch(Exception e){
			RequestDispatcher error = request.getRequestDispatcher("view/notice/noticeError.jsp");
			request.setAttribute("code", "nsearchFail");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
