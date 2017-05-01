package notice.model.dao;

import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.*;

import notice.exception.*;
import notice.model.vo.Notice;

public class NoticeDao {
	public NoticeDao(){}

	public ArrayList<Notice> selectAll(Connection con) throws NoticeListFailException{
		ArrayList<Notice> list = new ArrayList<Notice>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice";
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setHit(rset.getInt("hit"));
				
				list.add(n);
			}
			
			if(list.size() == 0){
				throw new NoticeListFailException("공지글 조회내용이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public Notice selectNotice(Connection con, int no) throws NoticeDetailFailException{
		Notice n = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		
		String query = "select * from notice where notice_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				n = new Notice();
				n.setNoticeNo(no);
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setHit(rset.getInt("hit"));
				
			}else{
				throw new NoticeDetailFailException("공지글 상세조회 실패하였습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return n;
	}

	public int updateNotice(Connection con, Notice n) throws NoticeUpdateFailException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update notice set notice_title = ?, notice_content = ? where notice_no = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			if(result <= 0)
				throw new NoticeUpdateFailException("공지글 수정이 실패하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	
	public int updateHit(Connection con, int no) throws NoticeUpdateFailException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		//조회수 1 증가 처리함
		String query = "update notice set hit = " + 
		"(select hit + 1 from notice where notice_no = ?) " + 
				"where notice_no = ?";
		
		try{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
			
			if(result <= 0)
				throw new NoticeUpdateFailException("공지글 조회수 증가처리 실패했습니다.");
		}catch(Exception e){
			throw new NoticeUpdateFailException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection con, Notice notice) throws NoticeInsertFailException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into notice values " + 
					"(SEQ_NOTICENO.NEXTVAL, ?, ?, sysdate, ?, 0)";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeWriter());
			pstmt.setString(3, notice.getNoticeContent());
			
			result = pstmt.executeUpdate();
			if(result <= 0)
				throw new NoticeInsertFailException("새 공지글 등록이 실패하였습니다.");
		} catch (Exception e) {
			throw new NoticeInsertFailException(e.getMessage());
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> selectTitleSearch(Connection con, String keyword) 
			throws NoticeSearchFailException {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice where notice_title like ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setHit(rset.getInt("hit"));
				
				list.add(n);
			}
			
			if(list.size() == 0){
				throw new NoticeSearchFailException("공지글 제목으로 검색한 내용이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeSearchFailException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Notice> selectWriterSearch(Connection con, String writer) 
			throws NoticeSearchFailException{
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice where notice_writer = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setHit(rset.getInt("hit"));
				
				list.add(n);
			}
			
			if(list.size() == 0){
				throw new NoticeSearchFailException("일치하는 작성자 아이디에 대한 공지글이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeSearchFailException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Notice> selectDateSearch(Connection con, String start, String end)
			throws NoticeSearchFailException{
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from notice where notice_date between to_date(?, 'yyyy-MM-dd') and to_date(?, 'yyyy-MM-dd')";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setHit(rset.getInt("hit"));
				
				list.add(n);
			}
			
			if(list.size() == 0){
				throw new NoticeSearchFailException("해당 날짜 범위에 속하는 공지글이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoticeSearchFailException(e.getMessage());
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
}








