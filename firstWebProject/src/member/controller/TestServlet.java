package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.exception.LoginFailException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("LoginServlet의 doPost() 작동됨");
		//1. 전송값 꺼내기
		//먼저 값 꺼내기 전에 전송값에 한글이 있을 경우에는 인코딩처리함
		request.setCharacterEncoding("utf-8");
		//응답 처리시에도 전송값에 한글이 있을 경우 인코딩처리해야 함
		response.setContentType("text/html; charset=utf-8");
		
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		
		//System.out.println(userid + ", " + userpwd);
		
		//2. 값을 비즈니스 로직 처리하는 모델로 넘기고, 결과 받음		
		try {
			Member loginUser = new MemberService().loginCheck(userid, userpwd);
			
			//3. 받은 결과를 가지고 뷰를 선택해서 내보냄
			//response.sendRedirect("/first/view/member/loginSuccess.jsp");
			
			//서블릿이 처리된 결과를 선택한 뷰로 전달해서, 출력에 사용하게 해야 하는 경우
			//RequestDispatcher 를 사용함 : 절대경로 사용 못하는 메소드임.
			RequestDispatcher rd = request.getRequestDispatcher("view/member/loginSuccess.jsp");
			request.setAttribute("user", loginUser);
			rd.forward(request, response);
			
		} catch (LoginFailException e) {
			//서비스를 요청한 클라이언트에게로 처리 결과를 보내는 방법
			//3가지가 있음
			//1. 뷰를 선택해서 바로 대상에게 보내는 방법
			//로그인 실패 페이지를 내보냄
			//response.sendRedirect("/first/view/member/loginFail.html");
			
			//2. 요청한 클라이언트와 스트림을 열어서 직접 값을 보내는 방법
			//클라이언트 쪽에서 ajax 로 서비스 요청을 했을 경우에 사용하는 방법임.
			/*PrintWriter out = response.getWriter();
			out.print("<html><head><meta charset='utf-8'><title>로그아웃 실패</title></head>");
			out.print("<body><h2>아이디나 암호가 잘못 되었습니다.</h2>");
			out.print("<a href='/first/view/member/loginForm.html'>로그인 페이지로 이동</a>");
			out.print("</body></html>");
			out.flush();
			out.close();*/
			
			//3. RequestDispatcher 사용하는 방법
			//내보낼 뷰 페이지를 선택하고, 처리된 결과값을 뷰 페이지로 전달하는 경우
			//해당 뷰 페이지가 서블릿이 보낸 값을 꺼내서 출력에 사용해야 하는 경우
			RequestDispatcher rd = request.getRequestDispatcher("view/member/error.jsp");
			request.setAttribute("message", e.getMessage());
			request.setAttribute("code", "loginFail");
			rd.forward(request, response);
		}
		
	}

}











