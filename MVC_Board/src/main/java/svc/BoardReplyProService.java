package svc;

import java.sql.Connection;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardReplyProService {
	public boolean registReplyBoard(BoardBean board) {
		int insertCount = 0;
		boolean isWriteSuccess = false;
		
		Connection conn = JdbcUtil.getConnection();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.setConn(conn);
		
		insertCount = dao.insertReplyBoard(board);
		
		if(insertCount > 0) {
			JdbcUtil.commit(conn);
			isWriteSuccess = true;
		}
		else {
			JdbcUtil.rollback(conn);
		}
		JdbcUtil.close(conn);
		
		return isWriteSuccess ;
	}
}
