package svc;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import db.JdbcUtil;

public class BoardDeleteProService {
	public boolean isBoardWriter(int board_num, String board_pass) {
		//service 에서 사용될 변수 객체 초기화
		boolean result = false;
		BoardDAO dao = null;
		//db에서 정보 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		//5. dao 의 selectBoardWriter() 메서드 호출 후 결과값 받아오기
		result = dao.selectBoardWriter(board_num,board_pass);
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		return result;
	}
	
	public boolean removeBoard(int board_num) {
		int deleteCount = 0;
		boolean isDeleteSuccess = false;
		BoardDAO dao = null;
		//db에서 정보 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		//5. dao 의 deleteBoard() 메서드 호출 후 결과값 받아오기
		deleteCount = dao.deleteBoard(board_num);
		
		// 6. 결과값 확인하여 커밋 또는 롤백
		if(deleteCount > 0) {
			JdbcUtil.commit(conn);
			isDeleteSuccess = true;
		}
		else {
			JdbcUtil.rollback(conn);
		}
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		return isDeleteSuccess;
	}
	
	public String getBoardRealFile(int board_num) {
		String board_real_file = null;
		

		BoardDAO dao = null;
		//db에서 정보 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		//5. dao 의 selectBoardRealfile() 메서드 호출 후 결과값 받아오기
		board_real_file = dao.selectBoardRealfile(board_num);
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		
		return board_real_file;
	}
}
