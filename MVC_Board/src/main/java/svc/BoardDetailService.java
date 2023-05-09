package svc;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BoardDAO;
import db.JdbcUtil;
import vo.BoardBean;

public class BoardDetailService {
	public BoardBean getBoard(int board_num) {
		// 사용될 객체, 변수 초기화
		BoardBean board = null;
		BoardDAO dao = null;
		int updateCount = 0;
		// JdbcUtil 에서 db 커넥션 받아오기
		Connection conn = JdbcUtil.getConnection();
		
		// BoardDAO 에서 인스턴스 받아오기(싱글톤 이기 때문에 인스턴스가 하나밖에 없음)
		dao = BoardDAO.getInstance();
		
		// dao 에 커넥션 정보 전달
		dao.setConn(conn);
		
		// dao 에서 board 데이터 받고 
		// dao 에서 board 조회수 증가시켜주기
		board = dao.selectBoard(board_num);
		updateCount = dao.updateReadcount(board_num);

		// 커밋(조회수 관련) 및 db 커넥션 반환
		// 조회수가 정상적으로 올라갔으면 커밋
		// 아니면 롤백
		if(updateCount != 0)
		{				
			JdbcUtil.commit(conn);
				board.setBoard_readcount(board.getBoard_readcount() + 1);
		}
		else {
			JdbcUtil.rollback(conn);
		}

		JdbcUtil.close(conn);
		
		return board;
	}
}
