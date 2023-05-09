package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberJoinProService_backup {
	public boolean joinMember(MemberBean bean) {
		System.out.println("MemberJoinProService - joinMember()");
		
		// 1. 작업 요청 처리 결과를 저장할 변수
		boolean isJoinSuccess = false;
		// 2. jdbUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기(공통)
		Connection conn = JdbcUtil.getConnection();
		
		// 3. BoardDAO 클래스로 부터 MemberDAO 객체 가져오기(공통)
		// 싱글톤 디자인 패턴(클래스 인스턴스 객체가 단 하나 있는것)
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4. MemberDAO 객체의 setConnection 메서드 호출 하여 객체 전달(공통)
		dao.setConn(conn);
		
		// 5. MemberDAO 객체의 xxx() 메서드를 호출하여 xxx 작업 수행 요청 및 결과 리턴받기
		int insertCount = dao.insertMember(bean);
		
		
		// 6. DB 작업 요청처리결과 에 따른 트랜잭션 처리
		if(insertCount > 0) { // 성공시
			// insert 작업 성공시 트랜잭션 처리 중 commit 작업 수행 위해 
			// jdbc 유닛의 commit() 메서드 호출
			//		파라미터 : Connection 객체
			JdbcUtil.commit(conn);
			isJoinSuccess = true;
		} else { //실패시
			JdbcUtil.rollback(conn);
		}
		
		// 7. Connection 풀 에서 가져온 connection 객체 반환 (공통)
		JdbcUtil.close(conn);
		return isJoinSuccess;
	}
}
