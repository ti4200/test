package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.MemberDetailFailException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDetailServlet
 */
@WebServlet("/mdetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDetailServlet() {
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
		//1. 문자 인코딩 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//2. 값 추출
		String userId = request.getParameter("userid");		
		
		try {
			//3. 모델로 넘기고, 결과 받기
			Member m = new MemberService().selectMember(userId);
			
			//4. 결과에 따라 뷰 선택
			RequestDispatcher view = request.getRequestDispatcher("view/member/memberDetailView.jsp");
			request.setAttribute("member", m);			
			view.forward(request, response);
			
		} catch (MemberDetailFailException e) {
			RequestDispatcher view = request.getRequestDispatcher("view/member/error.jsp");
			request.setAttribute("code", "detailFail");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}	
		
	}

}








