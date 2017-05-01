package board.model.dao;

import java.io.Reader;
import java.sql.*;
import java.util.*;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.exception.BoardException;
import board.model.vo.Board;
import static common.JDBCTemplate.*;

public class BoardDao {
	
	public static String config = "mybatis/mybatis-config.xml";
	
	public BoardDao() {
	}

	// 현재 게시글 총 갯수 조회용
	public int getListCount() throws BoardException {
		int listCount = 0;
		//List<Board> list = null; 
		Reader reader = null;
		SqlSession session = null;

		//String query = "select count(*) from board";

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			listCount = (Integer)session.selectOne("countRows");
			System.out.println("listcount : " + listCount);

			
			if (listCount>0) {
			} else
				throw new BoardException("게시글 총 갯수 조회 실패하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (listCount > 0) {
				session.close();
			}
		}

		return listCount;
	}

	// 페이지 단위로 게시글 목록 조회용
	public List<Board> selectList(int currentPage, int limit) throws BoardException {
		List<Board> list = null;
		Reader reader = null;
		SqlSession session = null;

		// 최신글이 맨 위로 오게 조회함
		//String query = "select * from (select rownum rnum, board_num, board_writer, board_title, board_content, board_original_filename, board_rename_filename, board_reply_ref, board_reply_lev, board_reply_seq, board_readcount, board_date from (select * from board order by board_reply_ref desc, board_reply_seq asc)) where rnum >= ? and rnum <= ?";

		int startRow = (currentPage - 1) * 10 + 1; // 읽기 시작할 row 번호.
		System.out.println("startRow : " + startRow);
		int endRow = startRow + limit - 1; // 읽을 마지막 row 번호.
		Map<String, Integer> map = new HashMap<String,Integer>();
		try {
			
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			
			list = session.selectList("selectAll", map);
			


		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session!=null)	session.close();
		}

		return list;
	}

	// 글번호를 통한 게시글 상세조회
	public Board selectOne(int boardNum) throws BoardException {
		Board board = null;
		Reader reader = null;
		SqlSession session = null;
				

		//String query = "select * from board where board_num = ?";

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			board = session.selectOne("selectB", boardNum);


		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}


		return board;
	}

	// 게시글 조회수 증가 처리용
	public int addReadCount(int boardNum) throws BoardException {
		int result = 0;
		Reader reader = null;
		SqlSession session = null;

		String query = "update board set board_readcount = (select board_readcount + 1 from board where board_num = ?) where board_num = ?";

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			result = session.update("updateCount", boardNum);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return result;
	}

	public int insertBoard(Board b) throws BoardException {
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		//String query = "insert into board values ((select max(board_num) + 1 from board) ?, ?, ?, ?, ?, (select max(board_num) + 1 from board), 0, 0, 0, sysdate)";

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.insert("insertB",b);
			session.commit();
		} catch (Exception e) {
			throw new BoardException(e.getMessage());
		}finally {
			if (session != null)
				session.close();
		}

		return result;
	}

	public int updateBoard(Board b) throws BoardException {
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		//String query = "update board set board_title = ?, board_content = ?, board_original_filename = ?,board_rename_filename = ? where board_num = ?";

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.update("updateB", b);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session != null)
				session.close();
		}

		return result;
	}
	
	public int deleteBoard( Board board) throws BoardException{
		int result = 0;
		Reader reader = null;
		SqlSession session = null;		
		
		try {
			//답글의 답글까지만 삭제 처리할 경우
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.delete("deleteB", board);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session!=null) {
				session.close();
			}
		}

		return result;
	}

	// 답글 등록 처리용
	public int insertReply(Board reply) throws BoardException {
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		// 답글에는 파일 업로드를 적용하지 않는 경우
		/*String query = "insert into board (board_num, board_title, " + "board_writer, board_content, board_reply_ref, "
				+ "board_reply_lev, board_reply_seq, board_readcount, "
				+ "board_date) values ((select max(board_num) from board) + 1, " 
				+ "?, ?, ?, ?, ?, ?, 0, sysdate)";*/

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.insert("insertReply", reply);
			
			/*pstmt = con.prepareStatement(query);
			pstmt.setString(1, reply.getBoardTitle());
			pstmt.setString(2, reply.getBoardWriter());
			pstmt.setString(3, reply.getBoardContent());
			pstmt.setInt(4, reply.getBoardReplyRef());
			pstmt.setInt(5, reply.getBoardReplyLev());
			pstmt.setInt(6, reply.getBoardReplySeq());

			result = pstmt.executeUpdate();*/
			
			if (result == 0){
				session.rollback();
				throw new BoardException("답글 등록이 실패하였습니다.");	
			}
			else session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		} finally {
			if (session!=null) {
				session.close();
			}
		}

		return result;
	}
	
	public int updateReplySeq( Board board) throws BoardException{
		int result = 0;
		Reader reader = null;
		SqlSession session = null;

		/*String query = "update board " + 
				"set board_reply_seq = board_reply_seq + 1 "
				+ "where board_reply_ref = ? and "
				+ "board_reply_seq > ?";*/

		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.update("updateReplySeq", board);

			if (result < 0)
				throw new BoardException("답글 순번 수정 실패하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException(e.getMessage());
		}
		
		return result;
	}

	

}
