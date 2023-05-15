package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardReplyFormService {
	public BoardBean getBoard(int board_num) {
		//사용될 변수 객체 초기화
		BoardBean board = null;
		
		//db 커넥션 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// dao 객체 빌려오기
		BoardDAO dao = BoardDAO.getInstance();
		
		// dao에 커넥션 등록
		dao.setConn(conn);
		
		//게시판 글 받아오기
		board = dao.selectBoard(board_num);
		
		//커넥션 반환
		JdbcUtil.close(conn);
		
		return board;
	}
}
