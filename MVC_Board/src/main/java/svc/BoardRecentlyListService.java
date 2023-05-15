package svc;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardRecentlyListService {
	public List<BoardBean> getRecentlyBoardList(int startLow, int listLimit){
		// 사용될 변수 초기화
		List<BoardBean> boardList = null;
		BoardDAO dao = null;
		
		// Jdbc 에서 db 객체 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// dao 객체 받아오기
		dao = BoardDAO.getInstance();
		
		// dao 객체에 커넥션 전달
		dao.setConn(conn);
		
		// dao 에서 sql 문 실행 후 결과값 가져오기
		boardList = dao.selectRecentlyList( startLow,  listLimit);
		
		JdbcUtil.close(conn);
		return boardList;
	}
}
