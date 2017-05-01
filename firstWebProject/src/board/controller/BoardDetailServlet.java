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
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/bdetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 상세보기 출력용 컨트롤러
		response.setContentType("text/html; charset=utf-8");
		
		int bnum = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		try {
			BoardService bService = new BoardService();
			
			//게시글 상세보기 조회시 조회수 1 증가 처리
			int result = bService.addReadCount(bnum);
			if(result <= 0)
				throw new BoardException(bnum + "글에 대한 조회수 증가처리 실패!");
			
			//게시글 상세조회
			Board board = bService.selectBoard(bnum);
			
			if(board != null){
				RequestDispatcher view = request.getRequestDispatcher("view/board/boardDetailView.jsp");
				request.setAttribute("board", board);
				request.setAttribute("currentPage", page);
				view.forward(request, response);
			}else
				throw new BoardException(bnum + "번 게시글 상세조회가 실패하였습니다.");
		} catch (BoardException e) {
			e.printStackTrace();
			RequestDispatcher errorPage = request.getRequestDispatcher("view/board/boardError.jsp");
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
