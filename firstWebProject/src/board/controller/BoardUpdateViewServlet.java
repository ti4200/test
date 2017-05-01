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
 * Servlet implementation class BoardUpdateViewServlet
 */
@WebServlet("/bupdateView")
public class BoardUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 페이지 출력용 컨트롤러
response.setContentType("text/html; charset=utf-8");
		
		int bnum = Integer.parseInt(request.getParameter("num"));		
		
		try {				
			//게시글 수정페이지로 전송할 게시글 정보 조회
			Board board = new BoardService().selectBoard(bnum);
			
			if(board != null){
				RequestDispatcher view = 
						request.getRequestDispatcher("view/board/boardModifyForm.jsp");
				request.setAttribute("board", board);				
				view.forward(request, response);
			}else
				throw new BoardException(bnum + "번 게시글 수정페이지 이동이 실패하였습니다.");
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
