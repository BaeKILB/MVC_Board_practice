package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.BoardBean;

public class BoardDAO {
	// 싱글톤 디자인으로 클래스 만들기
	
	// 1. 인스턴스 함부로 만들지 못하게 생성자 private 접근제한자로 만들기
	private BoardDAO() {}
	
	// 2. 클래스 내에 돌려가며 쓸 인스턴스 만들기 
	// 단 static으로 만들기
	private static BoardDAO instance = new BoardDAO();
	private  Connection conn = null;
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public  void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public int insertBoard(BoardBean board) {
		//사용될 변수 초기화
		int result = 0;
		int board_num = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 일단 글 번호 지정을 위해 sql 문 만들어 
		// 글 번호 최대값 찾은 뒤 글번호 지정
		String sql = "SELECT MAX(board_num) FROM board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board_num = rs.getInt(1) + 1;
			}
			else {
				board_num += 1;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		Exception boardNumExecption = new Exception("board_num exception");

		
		//
		sql = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?, now()) ";
		// 예외 처리
		try {
			if(board_num <= 0) {
				throw boardNumExecption;
			}
			pstmt = conn.prepareStatement(sql);
			//4. pstmt 에 ? 문자에 변수 채워주기
			
			pstmt.setInt(1, board_num);
			pstmt.setString(2, board.getBoard_name());
			pstmt.setString(3, board.getBoard_pass());
			pstmt.setString(4, board.getBoard_subject());
			pstmt.setString(5, board.getBoard_content());
			pstmt.setString(6, board.getBoard_file());
			pstmt.setString(7, board.getBoard_real_file());
			pstmt.setInt(8, board_num);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.setInt(11, 0);
			
			// 5. sql문 적용 및 결과 가져오기
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("BoardDAO - insertBoard SQLException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (Exception e) {
			System.out.println("BoardDAO - insertBoard board_num Exception");
			e.printStackTrace();
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		return result;
	}
	
	public int boardCount() {
		// 사용될 객체 변수 초기화 
		int listCount = 0;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT COUNT(*) FROM board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDAO - boardCount() SQL Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	public List<BoardBean> boardSelect(int startLow, int listLimit){
		// 사용될 객체 변수 초기화 
		List<BoardBean> boardList = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		// sql 문 작성
		String sql = "SELECT * FROM board ORDER BY board_re_ref DESC, board_re_seq ASC LIMIT ?,?;";
		
		// sql 문 pstmt 에 적용
		try {
			// => 정렬 기준 : 참조글 번호(board_re_ref) 기준 내림차순
			//				: 순서번호(board_re_seq) 기준 오름차순
			// => 조회 레코드 갯수 제한(LIMIT 절 사용)
			pstmt = conn.prepareStatement(sql);
			
			// 셀렉느로 불러올 데이터 리밋 걸기
			pstmt.setInt(1, startLow);
			pstmt.setInt(2, listLimit);
			
			// sql문 실행 후 반환
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<BoardBean>();
			// rs 에 next() 로 값을 꺼내서 배열에 저장
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt(1));
				board.setBoard_name(rs.getString(2));
				board.setBoard_pass(rs.getString(3));
				board.setBoard_subject(rs.getString(4));
				board.setBoard_content(rs.getString(5));
				board.setBoard_file(rs.getString(6)); // 원본 파일 명
				board.setBoard_real_file(rs.getString(7)); // 실제 업로드된 파일 명
				board.setBoard_re_ref(rs.getInt(8));
				board.setBoard_re_lev(rs.getInt(9));
				board.setBoard_re_seq(rs.getInt(10));
				board.setBoard_readcount(rs.getInt(11));
				board.setBoard_date(rs.getTimestamp(12));

				boardList.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDAO - boardSelect() SQL Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return boardList;
	}
	
	public int updateReadcount(int board_num) {
		int countUpdate = 0;
		PreparedStatement pstmt = null;
		
		// sql 문 작성

		String sql = "UPDATE board SET board_readcount = board_readcount + 1 WHERE board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			
			countUpdate = pstmt.executeUpdate();
			System.out.println(countUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDAO - updateReadcount() SQL Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return countUpdate;
	}
	
	public BoardBean selectBoard(int board_num) {
		// 사용될 변수 객체 초기화
		BoardBean board = null;
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		// sql 문 작성

		String sql = "SELECT * FROM board WHERE board_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				board = new BoardBean();
				board.setBoard_num(rs.getInt(1));
				board.setBoard_name(rs.getString(2));
//				board.setBoard_pass(rs.getString(3)); // 비밀번호는 생략
				board.setBoard_subject(rs.getString(4));
				board.setBoard_content(rs.getString(5));
				board.setBoard_file(rs.getString(6)); // 원본 파일 명
				board.setBoard_real_file(rs.getString(7)); // 실제 업로드된 파일 명
				board.setBoard_re_ref(rs.getInt(8));
				board.setBoard_re_lev(rs.getInt(9));
				board.setBoard_re_seq(rs.getInt(10));
				board.setBoard_readcount(rs.getInt(11));
				board.setBoard_date(rs.getTimestamp(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDAO - selectBoard() SQL Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return board;
	}
	
}
