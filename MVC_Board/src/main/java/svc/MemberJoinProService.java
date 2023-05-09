package svc;

import java.sql.Connection;

import dao.MemberDAO;
import db.JdbcUtil;
import vo.MemberBean;

public class MemberJoinProService {
	public boolean joinMember(MemberBean member) {
		// 1 . 리턴할 boolean 값 만들기
		boolean isJoinSuccess = false;
		
		// 2. dao 에 사용될 db Connection 객체 JdbcUtil 에서 가져오기
		Connection conn = JdbcUtil.getConnection();
		
		// 3. dao 인스턴트 가져오기
		// 싱글톤 디자인으로 만들어졌기때문에 인스턴스 만드는게 아닌
		// 만들어진 인스턴스를 가져옴
		MemberDAO dao = MemberDAO.getInstance();
		
		// 4. dao에 커넥션 객체 보내주기
		dao.setConn(conn);
		
		// 5. dao에 member 데이터 보낸 후 결과값 받기(int)
		int insertCount = dao.insertMember(member);
		
		// 6. dao 결과값에 따라 result 값 설정
		if(insertCount > 0) {
			// 값이 insert 동작으로 db에 들어갔으면 커밋 으로 db 에 적용 시키기
			JdbcUtil.commit(conn);
			isJoinSuccess = true;
		}
		else {
			// 값이 올바르게 저장이 되지 않았으면 롤백 !
			JdbcUtil.rollback(conn);
		}
		
		// 7. 사용한 Jdbc 커넥션 반환
		JdbcUtil.close(conn);
		
		return isJoinSuccess;
	}

	
}
