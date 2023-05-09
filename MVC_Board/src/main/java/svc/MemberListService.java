package svc;

import java.sql.Connection;
import java.util.List;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberListService {
	public List<MemberBean> getMemberList(){
		// 1. 리턴변수 초기화
		List<MemberBean> memberList = null;

		// 2. jdbUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기(공통)
		Connection conn = JdbcUtil.getConnection();
		
		// 3. BoardDAO 클래스로 부터 MemberDAO 객체 가져오기(공통)
		// 싱글톤 디자인 패턴(클래스 인스턴스 객체가 단 하나 있는것)
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4. MemberDAO 객체의 setConnection 메서드 호출 하여 객체 전달(공통)
		dao.setConn(conn);
		
		// 5. dao selectMemberList() 메서드 호출하여 가져오기
		memberList = dao.selectMemberList();
		// 7. Connection 풀 에서 가져온 connection 객체 반환 (공통)
		JdbcUtil.close(conn);
		return memberList;
	}
}
 