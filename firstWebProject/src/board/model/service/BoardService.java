package board.model.service;

import java.sql.*;
import java.util.*;

import static common.JDBCTemplate.*;

import board.exception.BoardException;
import board.model.dao.BoardDao;
import board.model.vo.Board;

public class BoardService {
	public BoardService(){}

	public int getListCount() throws BoardException{
		Connection con = getConnection();
		int count = new BoardDao().getListCount(con);
		close(con);
		return count;
	}

	public List<Board> selectList(int currentPage, int listLimit) throws BoardException{
		Connection con = getConnection();
		List<Board> list = new BoardDao().selectList(con, currentPage, listLimit);
		close(con);
		return list;
	}

	public int insertBoard(Board b) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().insertBoard(con, b);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public Board selectBoard(int bnum) throws BoardException{
		Connection con = getConnection();
		Board b = new BoardDao().selectBoard(con, bnum);
		close(con);
		return b;
	}

	public int addReadCount(int bnum) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().addReadCount(con, bnum);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int deleteBoard(int bnum, int level) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().deleteBoard(con, bnum, level);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public int updateReplySeq(int ref, int seq) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().updateReplySeq(con, ref, seq);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int insertReply(Board b) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().insertReply(con, b);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

	public int updateReply(Board b) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().updateReply(con, b);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}
	
	public int updateBoard(Board b) throws BoardException{
		Connection con = getConnection();
		int result = new BoardDao().updateBoard(con, b);
		if(result > 0)
			commit(con);
		else
			rollback(con);
		close(con);
		return result;
	}

}










