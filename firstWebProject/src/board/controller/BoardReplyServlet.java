package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardReplyServlet
 */
@WebServlet("/breply")
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 답글 등록 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		
		int level = Integer.parseInt(request.getParameter("level"));
		int ref = Integer.parseInt(request.getParameter("ref"));		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String bwriter = request.getParameter("bwriter");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		try {
			BoardService bService = new BoardService();
			//최근 답글이 위에 나오게 하려면 현재 답글에 대한 기존의 순번을 모두 1 증가시킴
			int result = bService.updateReplySeq(ref, seq);
			//답글 등록 처리
			//첫번째 답글일때는 순번 증가처리한 갯수 result 가 0임
			if(result >= 0){
				Board b = new Board(bwriter, btitle, bcontent, ref, level, seq);
				int result2 = bService.insertReply(b);
				if(result2 > 0)
					response.sendRedirect("/first/blist?page=1");
				else
					throw new BoardException(ref + "번 글에 대한 답글 달기가 실패하였습니다.");
			}else
				throw new BoardException(ref + "번 글에 대한 답글 순번 처리 실패하였습니다.");
			
		} catch (BoardException e) {
			e.printStackTrace();
			RequestDispatcher errorPage = 
					request.getRequestDispatcher("view/board/boardError.jsp");
			request.setAttribute("message", e.getMessage());
			errorPage.forward(request, response);
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
