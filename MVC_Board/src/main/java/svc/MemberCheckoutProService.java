package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;

public class MemberCheckoutProService {
	public boolean checkoutMemberDelete(String id, String passwd) {
		// 사용할 변수 객체 초기화 
		boolean isMemberDelete = false;
		int countDelete = 0;
		// db 커넥션 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// dao 에서 객체 받아오기 
		MemberDAO dao = MemberDAO.getInstance();
		
		// dao 에 커넥션 전달해주기
		dao.setConn(conn);
		
		// 회원 삭제 동작하고 결과값 받아오기
		countDelete = dao.deleteMember(id, passwd);
		
		// 결과값 따라서 commit 또는 rollback 수행 후 커넥션 반환
		if(countDelete > 0) {
			JdbcUtil.commit(conn);
			isMemberDelete = true;
		}
		else {
			JdbcUtil.rollback(conn);
		}
		JdbcUtil.close(conn);
		
		return isMemberDelete;
	}
}
