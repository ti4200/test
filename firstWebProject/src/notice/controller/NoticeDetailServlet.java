package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.exception.NoticeDetailFailException;
import notice.exception.NoticeUpdateFailException;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/ndetail")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 상세 조회 서비스 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		try {
			Notice notice = new NoticeService().selectNotice(no);
			RequestDispatcher view = request.getRequestDispatcher("view/notice/noticeDetailView.jsp");
			request.setAttribute("n", notice);
			view.forward(request, response);
			
		} catch (NoticeDetailFailException e) {
			RequestDispatcher error = request.getRequestDispatcher("view/notice/noticeError.jsp");
			request.setAttribute("code", "ndetailFail");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}catch (NoticeUpdateFailException e) {
			RequestDispatcher error = request.getRequestDispatcher("view/notice/noticeError.jsp");
			request.setAttribute("code", "nupdateFail");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}catch(Exception e){
			RequestDispatcher error = request.getRequestDispatcher("view/notice/noticeError.jsp");
			request.setAttribute("code", "exception");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}
		
	}

}
