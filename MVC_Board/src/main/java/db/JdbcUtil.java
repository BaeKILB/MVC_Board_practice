package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

// 데이터베이스 작업 준비 및 해제 (=자원 반환) 작업을 공통으로 수행할 JdbcUtil 클래스 정의
public class JdbcUtil {
	// 1. 데이터베이스 접근(공통 작업)을 통해 Connection 객체를 생성하여 외부로 리턴하는
	/// getConnection() 메서드 정의(데이터베이스 작업 1단계 & 2단계에 해당)
	
	
	public static Connection getConnection() {
		// context.xml 에 설정된 DBCP(커넥션풀)로부터 Connection 객체 가져와서 외부로 리턴
		Connection conn = null;
		try {
			// 1. 톰캣으로부터 톰캣 객체에서 관리하는 context.xml 파일에 대한 정보관리를 수행
			//	  Context 객체 가져오기(context.xml 파일 내의 <Context> 태그에 해당하는 부분)
			// =>InitialContext 객체 생성후 Context 타입으로 
			Context initCtx = new InitialContext();
			
			// 2. 생성된 Context 객체 (initCtx) 로 부터 context.xml 파일에 정의된
			// 	 <Resource> 태그 부분 가져오기
//		Context envCtx = (Context)initCtx.lookup("java:comp/env");
			
			// 3. resource 태그 내의 name 속성(=리소스 이름 jdbc/MySql) 가져오기 위해
			//	  생성된 Context 객체 (envCtx) 의 lookup() 메서드를 호출하여 리소스 이름 전달
//		DataSource ds = (DataSource)envCtx.lookup("jdbc/MySql");
			
			//-----------------참고 2번과 3번 과정을 하나의 문장으로 결합
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/MySQL");
			// => 3번까지의 과정이 완료되면 커넥션 풀을 관리하는 DataSource 객체 리턴됨

			// 4 DataSoure 객체(=커넥션풀) 로 부터 미리 생성된 Connection 객체 가져오기
			conn = ds.getConnection();
			
			// 5. 자동 커밋(= Auto Commit) 기능 해제(선택 사항)
			// => 기본적으로 JDBC 사용 시 Auto Commit 기능이 동작되도록 설정되어 있음
			// => Connection 객체의 setAutoCommit() 메서드를 호출하여 설정 변경 가능
			// 
			conn.setAutoCommit(false);//자동 커밋 기능 해제
			// => 별도로 commit, rollback 작업을 수행할 메서드 필요
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("db 연결 실패 !");
			e.printStackTrace();
		}
		
		// 데이터베이스 연결 객체가 저장된 Connection 객체 리턴
		return conn;
		
	}
	
	// 데이터베이스 자원 반환(close)을 공통으로 수행할 close() 메서드 정의
	
	public static void close(Connection conn) {
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(PreparedStatement pstmt) {
		
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 데이터베이스 작업에 대한 커밋 ,롤백 작업 수행 메서드 정의
	public static void commit(Connection conn) {
		
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
