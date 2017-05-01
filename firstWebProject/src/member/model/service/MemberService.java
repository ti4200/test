/*package member.model.service;

import java.sql.Connection;

import member.exception.EnrollFailException;
import member.exception.LoginFailException;
import member.exception.MemberDeleteFailException;
import member.exception.MemberDetailFailException;
import member.exception.MemberUpdateFailException;
import member.model.dao.MemberDao;
import member.model.vo.Member;
import static common.JDBCTemplate.*;

public class MemberService {
	public MemberService(){}
	
	//로그인 확인을 위한 메소드
		public Member loginCheck(String userId, String userPwd) throws LoginFailException{
			Connection con = getConnection();
			Member m = new MemberDao().loginCheck(con, userId, userPwd);
			close(con);			
			return m;
		}

		//회원가입 처리용 메소드
		public int insertMember(Member m) throws EnrollFailException{
			Connection con = getConnection();
			int result = new MemberDao().insertMember(con, m);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}

		public Member selectMember(String userId) throws MemberDetailFailException{
			Connection con = getConnection();
			Member m = new MemberDao().selectMember(con, userId);
			close(con);
			return m;
		}

		public int deleteMember(String userId) throws MemberDeleteFailException{
			Connection con = getConnection();
			int result = new MemberDao().deleteMember(con, userId);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}

		public int updateMember(Member m) throws MemberUpdateFailException{
			Connection con = getConnection();
			int result = new MemberDao().updateMember(con, m);
			if(result > 0)
				commit(con);
			else
				rollback(con);
			close(con);
			return result;
		}
}







*/