package member.controller;

import java.io.IOException;
import java.text.*;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberRegisterServlet
 */
@WebServlet("/mRegister")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 처리용 컨트롤러
		//System.out.println("회원 가입 서비스 구동");
		//1. 전송값에 / 응답할 데이터에 한글이 있을 경우 문자셋 인코딩 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//2. 전송값 꺼내서 변수에 저장
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd1");
		/*char gender = request.getParameter("gender").charAt(0);*/
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("zcode1") + "-" + 
							request.getParameter("zcode2") + "\n" +
							request.getParameter("address");
		/*String hobbys = request.getParameterValues("hobby");*/
		String hobby = request.getParameter("hobby");
		Date birthday = Date.valueOf(request.getParameter("birthday"));	
		
		//값의 갯수가 많을 경우에는 , 객체에 담아서 전달하도록 한다.
		Member member = new Member(userid, userpwd, username, email, phone, 
				address, gender,birthday,  hobby);
		
		//3. 회원가입 처리 서비스 메소드로 객체 넘기고 처리 결과 받음
		int result = new MemberService().memberInsert(member);
		
		//4. 받은 결과를 가지고, 성공/실패에 대한 뷰를 선택해서 내보냄
		if(result > 0){	//회원 가입 성공시
			response.sendRedirect("index.html");
		}else{  //회원 가입 실패시
			response.sendRedirect("fail.html");
		}
	}

}








