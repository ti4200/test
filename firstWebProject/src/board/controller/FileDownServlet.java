package board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet("/fdown")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 다운로드 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		
		String originalFileName = request.getParameter("ofile");
		String renameFileName = request.getParameter("rfile");
		//System.out.println(originalFileName);
		//System.out.println(renameFileName);
		
		//파일 읽기용 스트림 객체
		BufferedInputStream bin = null;
		//클라이언트로 출력용 스트림 객체
		ServletOutputStream sout = null;
				
		try {
			//다운할 경로가 포함된 파일명에 대한 File 객체 생성
			File downFile = new File(renameFileName);
			//입출력 스트림 생성
			bin = new BufferedInputStream(new FileInputStream(downFile));
			sout = response.getOutputStream();

			response.setContentType("text/plane; charset=utf-8");
			response.addHeader("Content-Disposition", "attachment; filename=" + 
					URLEncoder.encode(originalFileName, "UTF-8"));			
			response.setContentLength((int)downFile.length());
			
			int read = 0;
			while((read = bin.read()) != -1){
				sout.write(read);
			}
			sout.flush();
			bin.close();
			sout.close();
			
		} catch (Exception e) {
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
