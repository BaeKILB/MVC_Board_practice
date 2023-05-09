package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberInfoService {
	public MemberBean getMember(String id) {
		// 1. 리턴할 값 초기화
		MemberBean member = null;
		
		// 2. 커넥션 객체 JdbcUtil 에서 받기
		Connection conn = JdbcUtil.getConnection();
		
		// 3. MemberDAO 인스턴스 받기 (싱글톤 이기때문에 인스턴스를 받아와야함)
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4. dao에 커넥션 보내주기
		dao.setConn(conn);
		
		// 5. service 에서 멤버 받아오기
		member = dao.selectMember(id);
		
		// 6. 커넥션 반환
		JdbcUtil.close(conn);
		
		return member;
	}
}
