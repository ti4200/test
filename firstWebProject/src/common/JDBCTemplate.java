package common;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

//db connection 을 얻어오고, 관리하는 유틸리티 클래스
public class JDBCTemplate {
	private JDBCTemplate(){}
	
	public static Connection getConnection(){
		Connection con = null;
		/*String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "student";
		String password = "student";*/
		
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);*/
			//javax.naming 패키지에서 제공하는 인터페이스
			//context.xml 파일에 설정된 내용을 읽어오기 위한 객체
			Context initContext = new InitialContext();
			//context.xml 에서 설정된 Resource 정보에서 해당 이름에 대한
			//값을 읽어오는 객체로 javax.sql 패키지에서 제공하는 인터페이스임.
			//톰켓이 제공하는 DBCP(DataBase Connecction Pool) 기능 사용 
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/oraDB");
            con = ds.getConnection();
            con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con){
		try{
			if(!con.isClosed())
				con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try{
			if(!stmt.isClosed())
				stmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset){
		try{
			if(!rset.isClosed())
				rset.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con){
		try{
			if(!con.isClosed())
				con.commit();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con){
		try{
			if(!con.isClosed())
				con.rollback();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}


