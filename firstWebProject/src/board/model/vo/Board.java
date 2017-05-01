package board.model.vo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements java.io.Serializable{
	private int boardNum;		//게시글 번호
	private String boardTitle;	//게시글 제목
	private String boardWriter;	//게시글 작성자 아이디
	private String boardContent;	//게시글 내용
	private java.sql.Date boardDate;	//게시글 올린 날짜
	private int boardReadCount;	//게시글 읽은 조회수
	private String originalFileName;	//첨부 파일 원래 이름과 경로
	private String renameFileName;	//첨부 파일 변경된 이름과 경로
	private int boardRef;	//답글일 경우 참조하는 원글번호, 원글도 자기 번호 기록
	private int boardLev;	//원글: 0, 답글: 1, 답글의 답글: 2
	private int boardSeq;	//답글의 기록 순번, 최근글을 위에 두는 구조일 때는 최근글이 1
	private String boardDeleteYN; 	//게시글 삭제여부 : 삭제시 "Y"
	
	public Board(){}

	//답글 수정용 생성자
	public Board(int boardNum, String boardTitle, String boardContent) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;		
		this.boardContent = boardContent;		
	}
	
	//원글 기록시 사용할 생성자
	public Board(String boardTitle, String boardWriter, String boardContent, String originalFileName,
			String renameFileName) {
		super();
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
	}	
	
	//목록 조회시 사용할 생성자
	public Board(int boardNum, String boardTitle, String boardWriter, String boardContent, Date boardDate,
			int boardReadCount, String originalFileName, String renameFileName, int boardRef, int boardLev,
			int boardSeq) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.boardRef = boardRef;
		this.boardLev = boardLev;
		this.boardSeq = boardSeq;
	}

	//답글 전송용 생성자
	public Board(String boardWriter, String boardTitle, String boardContent, 
			int boardRef, int boardLev, int boardSeq) {
		super();
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;		
		this.boardRef = boardRef;
		this.boardLev = boardLev;
		this.boardSeq = boardSeq;
	}	

	//원글 수정용 생성자
	public Board(int boardNum, String boardTitle, String boardContent, String originalFileName, String renameFileName) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public java.sql.Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(java.sql.Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public int getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	public int getBoardLev() {
		return boardLev;
	}

	public void setBoardLev(int boardLev) {
		this.boardLev = boardLev;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	
	public String getBoardDeleteYN() {
		return boardDeleteYN;
	}

	public void setBoardDeleteYN(String boardDeleteYN) {
		this.boardDeleteYN = boardDeleteYN;
	}

	@Override
	public String toString(){
		return this.boardNum + ", " + this.boardTitle + ", " + this.boardWriter;
	}	
	
}

