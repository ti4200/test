package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/binsert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 추가와 파일 업로드용 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//첨부파일 저장용 폴더
		String savePath = "E:\\오전반_수업자료\\workspace\\firstWebProject\\web\\uploadFiles";
		//한번에 업로드할 파일의 제한 용량 지정 : 10메가바이트
		int fileLimit = 10 * 1024 * 1024;
		//파일명 저장용
		String originFileName = null;
		String renameFileName = null;
		String btitle = null;
		String bwriter = null;
		String bcontent = null;
		
		try {
			//cos.jar 라이브러리를 이용한 파일 업로드 처리
			//multipart 방식으로 전송되지 않았다면 에러처리
			if(!ServletFileUpload.isMultipartContent(request)){
				throw new BoardException("multipart 속성을 지정하지 않았습니다.");
			}
			
			//multipart 로 전송된 정보를 추출하기 위한 객체 생성
			MultipartRequest multi = new MultipartRequest(request, savePath,
					fileLimit, "utf-8", new DefaultFileRenamePolicy());
			//DefaultFileRenamePolicy : 저장폴더에 같은 이름의 파일이 여러개 저장될때
			// 자동으로 파일명 뒤에 (1) 순번에 대한 숫자를 붙이는 기능을 제공함
			
			//전송값 추출
			bwriter = multi.getParameter("bwriter"); 
            btitle = multi.getParameter("btitle"); 
            bcontent = multi.getParameter("bcontent"); 
            
			//파일 업로드 처리
			originFileName = multi.getFilesystemName("upfile");
			Board b = null;
			
			//첨부 파일이 있을 때만 처리함
			if(originFileName != null){
				//파일명을 업로드한 '년월일시분초'로 변경함
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis())) + "." +
						originFileName.substring(originFileName.lastIndexOf(".") + 1);
				
				File originFile = new File(savePath + "\\" +originFileName);
				File renameFile = new File(savePath + "\\" + renameFileName);
									
				//파일명 바꾸기가 실패했다면, 파일을 강제로 복제하고, 기존 파일은 삭제함
				if(!originFile.renameTo(renameFile)){
					//파일 입출력을 위한 변수 선언
					int read = 0;
					byte[] buf = new byte[1024];	//한번에 읽을 배열 크기 지정
					FileInputStream fin = new FileInputStream(originFile);				
					FileOutputStream fout = new FileOutputStream(renameFile);
					
					while((read = fin.read(buf, 0, buf.length)) != -1){
						fout.write(buf, 0, read);
					}
					
					fin.close();
					fout.close();
					originFile.delete();
				}
				
				b = new Board(btitle, bwriter, bcontent, originFile.getName(), 
						renameFile.toString());
			}else{	//첨부파일이 없을 경우
				b = new Board(btitle, bwriter, bcontent, null, null);
			}			
			
			int result = new BoardService().insertBoard(b);
			if(result > 0){
				response.sendRedirect("/first/blist?page=1");
			}
			
			
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
