package notice.model.service;

import static common.JDBCTemplate.*;
import java.sql.*;

import java.util.ArrayList;

import notice.exception.NoticeDetailFailException;
import notice.exception.NoticeInsertFailException;
import notice.exception.NoticeListFailException;
import notice.exception.NoticeSearchFailException;
import notice.exception.NoticeUpdateFailException;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
	public NoticeService(){}

	public ArrayList<Notice> selectAll() throws NoticeListFailException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectAll(con);
		close(con);
		return list;
	}

	public Notice selectNotice(int no) throws NoticeDetailFailException, NoticeUpdateFailException{
		Connection con = getConnection();
		Notice n = new NoticeDao().selectNotice(con, no);
		if(n != null){
			int result = new NoticeDao().updateHit(con, no);
			if(result > 0)
				commit(con);
			else
				rollback(con);
		}
		close(con);
		return n;
	}

	public int updateNotice(Notice n) throws NoticeUpdateFailException{
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, n);
		if(result > 0){			
			commit(con);
		}else
			rollback(con);
		close(con);
		return result;
	}

	public int insertNotice(Notice notice) throws NoticeInsertFailException{
		Connection con = getConnection();
		int result = new NoticeDao().insertNotice(con, notice);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public ArrayList<Notice> selectDateSearch(String start, String end) throws NoticeSearchFailException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectDateSearch(con, start, end);
		close(con);
		return list;
	}

	public ArrayList<Notice> selectWriterSearch(String writer) throws NoticeSearchFailException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectWriterSearch(con, writer);
		close(con);
		return list;
	}

	public ArrayList<Notice> selectTitleSearch(String keyword)  throws NoticeSearchFailException{
		Connection con = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectTitleSearch(con, keyword);
		close(con);
		return list;
	}
	
	
}



