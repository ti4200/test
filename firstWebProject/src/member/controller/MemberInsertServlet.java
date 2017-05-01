package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.EnrollFailException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/minsert")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 정보 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("username");
		String userId = request.getParameter("userid");
		String userPwd = request.getParameter("userpwd");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String birth = request.getParameter("birth");		
		String zcode1 = request.getParameter("zcode1");
		String zcode2 = request.getParameter("zcode2");
		String address = request.getParameter("address");
		String[] hobby = request.getParameterValues("hobby");
		
		//주소와 우편번호 하나의 문자열로 합치기
		address = zcode1.concat("-" + zcode2).concat(" " + address);
		//System.out.println(address);
		
		//취미도 하나의 문자열로 합치기함 : 토큰문자 ',' 추가
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < hobby.length; i++){
			if(i < hobby.length - 1)
				sb.append(hobby[i] + ",");
			else
				sb.append(hobby[i]);
		}
		
		//꺼낸 값들을 객체에 저장
		Member m = new Member(userId, userPwd, userName, email, phone, 
				address,	gender, java.sql.Date.valueOf(birth), sb.toString());
		
		//모델로 넘기고, 처리 결과 받음
		try {
			int result = new MemberService().insertMember(m);
			if(result > 0)
				response.sendRedirect("/first/view/member/loginForm.html");
		
		} catch (EnrollFailException e) {
			RequestDispatcher error = request.getRequestDispatcher("view/member/error.jsp");
			request.setAttribute("code", "enrollFail");
			request.setAttribute("message", e.getMessage());
			error.forward(request, response);
		}
	}

}










