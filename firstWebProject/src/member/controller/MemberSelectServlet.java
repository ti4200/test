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
 * Servlet implementation class MemberSelectServlet
 */
@WebServlet("/mselect")
public class MemberSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSelectServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		
		String userid = request.getParameter("userid");
		
		Member member;
		try {
			member = new MemberService().selectMember(userid);
			if(member != null){ //조회 성공
				//조회해 온 회원의 정보를 jsp 파일로 전달해야 함
				RequestDispatcher rd = request.getRequestDispatcher("memberDetailView.jsp");
				request.setAttribute("member", member);
				rd.forward(request, response);
			}else{  //조회 실패
				response.sendRedirect("fail.html");
			}
		} catch (MemberDetailFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
