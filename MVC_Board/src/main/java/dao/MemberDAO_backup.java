package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.JdbcUtil;
import vo.MemberBean;

public class MemberDAO_backup {
	// 실제 비즈니스 로직을 수행하는 MemberDoa 클래스 정의
	// 비즈니스 로직만을 수행하므로 메모리 낭비 최소화를 위해
	// 싱글톤 디자인 패턴으로 자신의 클래스에 인스턴스를 만들어 돌려 쓰기

	// 1. 먼저 생성자를 만들지 못하게 생성자를 private 접근제한자로 선언
	private MemberDAO_backup() {
	}

	// 2. 자신의 클래스 내에서 직접 인스턴스 생성
	private static MemberDAO_backup instance = new MemberDAO_backup();
	// 인스턴스 값을 함부로 값을 변경할 수 없도록 접근 제한자로 선언

	// 3. 인스턴스 객체를 받아서 사용할 수 있게 getter 만 만들기
	// 단 클래스명 만으로 접근 가능하도록 static으로 만들기
	public static MemberDAO_backup getInstance() {
		return instance;
	}

	// 4. MemberDAO 객체의 setConnection() 메서드를 호출하여
	// Connection 타입 멤버변수 선언 및 setter 메서드 선언

	private Connection conn;

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	//---------------------------------------------------------------
	// 회원가입 작업 수행
	
	// 
	public int insertMember(MemberBean Member) {
		System.out.println("MemberDAO - insertMember()");

		// insert 작업 결과를 리턴받아 저장할 변수 선언
		int result = 0;
	
		// DB 작업에 필요한 변수 선언
		PreparedStatement pstmt = null;
		
		//3. sql 구문 작성 및 전달
		String sql = "INSERT INTO member values( null,?,?,?,?,?,?,?,?,?,now())";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Member.getName());
			pstmt.setString(2, Member.getId());
			pstmt.setString(3, Member.getPasswd());
			pstmt.setString(4, Member.getJumin());
			pstmt.setString(5, Member.getEmail());
			pstmt.setString(6, Member.getJob());
			pstmt.setString(7, Member.getGender());
			pstmt.setString(8, Member.getHobby());
			pstmt.setString(9, Member.getMotivation());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insertMember 구문 오류발생! - insertMember()");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);			
		}
		
		return result;
	}
	
}
