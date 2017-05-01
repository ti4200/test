package member.model.dao;

import java.io.Reader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.exception.EnrollFailException;
import member.exception.LoginFailException;
import member.exception.MemberDeleteFailException;
import member.exception.MemberDetailFailException;
import member.exception.MemberUpdateFailException;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDao {
	public static String config = "mybatis/mybatis-config.xml";
	public MemberDao(){}
	
	//로그인 확인을 위한 메소드
	public Member  loginCheck(String userId, String userPwd) throws LoginFailException{
		Member member = null;
		Reader reader = null;
		SqlSession session = null;
		Map<String, String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		//String query = "select * from member where userid = ? and userpwd = ?";
		
		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			member = session.selectOne("selectUser", map);
			
		} catch (Exception e) {
			throw new LoginFailException(e.getMessage());
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
		return member;
	}

	public int insertMember(Member m) throws EnrollFailException{
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		
		//String query = "insert into member values (?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate)";
		
		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.insert("insertRow", m);
			
			/*pstmt = con.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());	
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getPhone());	
			pstmt.setString(7, m.getEmail());					
			pstmt.setString(8, m.getHobby());
			pstmt.setString(9, m.getEtc());
			
			result = pstmt.executeUpdate();*/
			
			if(result <= 0){
				session.rollback();
				throw new EnrollFailException("회원 가입이 실패하였습니다.");
			} else session.commit();
		} catch (Exception e) {
			throw new EnrollFailException(e.getMessage());
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
		return result;
	}

	public Member selectMember(String userId) throws MemberDetailFailException{
		Member m = null;
		Reader reader = null;
		SqlSession session = null;
		
		//String query = "select * from member where userid = ?";
		
		try {
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			m = session.selectOne("selectRow", userId);
						
		} catch (Exception e) {
			throw new MemberDetailFailException(e.getMessage());
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
		return m;
	}

	public int deleteMember(String userId) throws MemberDeleteFailException{
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		
		//String query = "delete from member where userid = ?";
		 
		try {
			
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.delete("deleteRow", userId);
			
			if(result <= 0){
				session.rollback();
				throw new MemberDeleteFailException("회원 탈퇴 실패하였습니다.");
			} else {
				session.commit();
			}
		} catch (Exception e) {
			throw new MemberDeleteFailException(e.getMessage());
		}finally{
			if(session!=null)		session.close();
		}
		
		return result;
	}

	public int updateMember(Member m) throws MemberUpdateFailException{
		int result = 0;
		Reader reader = null;
		SqlSession session = null;
		
		/*String query = "update member set userpwd = ?, gender = ?, " + 
				"age = ?, phone = ?, email = ?, hobby = ?, etc = ? " + 
				"where userid = ?";*/
		
		try {
			
			reader = Resources.getResourceAsReader(config);
			session = new SqlSessionFactoryBuilder().build(reader).openSession();
			
			result = session.update("updateRow", m);
			
			if(result <= 0){
				session.rollback();
				throw new MemberUpdateFailException("회원 정보 변경 실패하였습니다.");
			} else {
				session.commit();
			}
		} catch (Exception e) {
			throw new MemberUpdateFailException(e.getMessage());
		}finally{
			if(session!=null)		session.close();
		}
		
		return result;
	}
}










