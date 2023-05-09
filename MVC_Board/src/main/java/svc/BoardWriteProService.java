package svc;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardWriteProService {
	public boolean registBoard(BoardBean board) {
		// 1. 객체및 변수 초기화
		Connection conn = null;
		BoardDAO dao = null;
		boolean isWriteSuccess = false;
		int insertCount = 0;
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		//5. dao 의 insertBoard() 메서드 호출 후 결과값 받아오기
		insertCount = dao.insertBoard(board);
		
		// 6. 결과값에 따라 isWriteSuccess 값 정해준 뒤 커밋 하기
		if(insertCount > 0) {
			isWriteSuccess = true;
			try {
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("BoardWriteProService - commit exception !");
				e.printStackTrace();
			}
			
		}
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		return isWriteSuccess;
	}
}
