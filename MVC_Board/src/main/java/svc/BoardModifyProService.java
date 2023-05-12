package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardModifyProService {
	
	public boolean isBoardWriter(int board_num, String board_pass) {
		BoardDAO dao = null;
		boolean isBoardWriter = false;
		//db에서 정보 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		//5. dao 의 selectBoardWriter() 메서드 호출 후 결과값 받아오기
		isBoardWriter = dao.selectBoardWriter(board_num,board_pass);
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		return isBoardWriter;
	}
	
	public boolean modifyBoard(int board_num, String board_pass, BoardBean board) {
		//service 에서 사용될 변수 객체 초기화
		boolean result = false;
		int updateCount = 0;
		BoardDAO dao = null;
		//db에서 정보 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// 2. db 커넥션 객체 받아오기
		conn = JdbcUtil.getConnection();
		
		//3. BoardDAO 객체 받아오기(싱글톤 이므로 만들어진 객체를 빌려오기)
		dao = BoardDAO.getInstance();
		
		// 4. db 커넥션 객체 dao에 보내기
		dao.setConn(conn);
		
		// dao 의 updateBoard() 메서드 호출 후 결과값 받아오기
		updateCount = dao.updateBoard(board_num, board);
		
		// 6. 결과값 확인하여 커밋 또는 롤백
		if(updateCount > 0) {
			JdbcUtil.commit(conn);
			result = true;
		}
		else {
			JdbcUtil.rollback(conn);
		}
	
		
		// 7. 커넥션 반환
		JdbcUtil.close(conn);
		
		return result;
	}

}
