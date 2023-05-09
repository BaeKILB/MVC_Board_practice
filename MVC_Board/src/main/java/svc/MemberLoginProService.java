package svc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberLoginProService {
	
	public boolean isCorrectUser(MemberBean member) {
		// 1. 작업결과 리턴 변수 선언
		boolean isCorrectUser = false;
		
		// 2. jdbUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기(공통)
		Connection conn = JdbcUtil.getConnection();
		
		// 3. BoardDAO 클래스로 부터 MemberDAO 객체 가져오기(공통)
		// 싱글톤 디자인 패턴(클래스 인스턴스 객체가 단 하나 있는것)
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4. MemberDAO 객체의 setConnection 메서드 호출 하여 객체 전달(공통)
		dao.setConn(conn);
		
		// 5. MemberDAO 객체의 selectCorrectUser() 메서드 호출하여 로그인 작업 요청
		isCorrectUser = dao.selectCorrectUser(member);
		
		// 7. Connection 풀 에서 가져온 connection 객체 반환 (공통)
		JdbcUtil.close(conn);
		return  isCorrectUser ;
	}
}
