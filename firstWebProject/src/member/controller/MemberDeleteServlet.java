package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.MemberDeleteFailException;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
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
		// TODO Auto-generated method stub
		//1. 인코딩처리
		//2. 값 추출
		String userId = request.getParameter("userid");
		
		try {
			//3. 모델로 넘기고 결과받음
			int result = new MemberService().deleteMember(userId);
			
			//4. 결과에 따라 뷰 선택
			if(result > 0) //탈퇴 성공시 로그아웃 서블릿 실행함
				response.sendRedirect("/first/logout");
		} catch (MemberDeleteFailException e) {
			RequestDispatcher error = request.getRequestDispatcher("view/member/error.jsp");
			request.setAttribute("code", "deleteFail");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}
		
	}

}










