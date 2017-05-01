package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.exception.NoticeListFailException;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/nlist")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지글 전체 조회 서비스 컨트롤러
		//문자 인코딩처리
		response.setContentType("text/html; charset=utf-8");
		//값 추출
		
		try {
			//서비스 메소드 실행하고, 결과 받기
			ArrayList<Notice> list = new NoticeService().selectAll();
			//받은 결과에 따라 뷰 선택
			RequestDispatcher rd = request.getRequestDispatcher("view/notice/noticeListView.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			
		} catch (NoticeListFailException e) {
			RequestDispatcher rd = request.getRequestDispatcher("view/notice/noticeError.jsp");
			request.setAttribute("code", "nlistFail");
			request.setAttribute("message", e.getMessage());
			rd.forward(request, response);
		}
		
		
	}

}
