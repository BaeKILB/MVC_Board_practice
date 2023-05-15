package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.JdbcUtil;
import vo.MemberBean;

// 싱글톤 디자인으로 만들어 
// 전 코드에 해당 클래스 인스턴스를 하나만 가지게 하기
public class MemberDAO {
	
	// 1. 생성자를 부르지 접근제한자를 private 로 하여 인스턴스를 가지지 못하게 하기
	private MemberDAO() {}
	
	// 2. 생성자 인스턴스를 만들어 두기
	// 단 인스턴스를 찍어내지 못해도 해당 클래스.메서드 로 가져올수 있게
	// 정적으로 선언 후 해당 객체를 받을수 있게 getter 만 만들기
	private static MemberDAO instance = new MemberDAO();
	 
	// 3. 커넥션 정보 외부에서 받을수 있게
	// 변수 생성 후 setter 만 만들기
	private Connection conn = null;

	// 4. 위의 instance 의 getter 와 conn 의 setter 만들기

	public static MemberDAO getInstance() {
		return instance;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	// 5. member 의 db 동작 만들기
	
	public int insertMember(MemberBean member) {
		// 1. 반환할 변수 지정 및 db 작업시 쓰일 객체 초기화
		int insertCount = 0;
		PreparedStatement pstmt = null;
		
		// 2. sql 문 작성 및 pstmt 에 적용
		// 첫번째 값(idx) 는 자동으로 지정되므로 null, 
		// 맨 마지막값은 현 시각이 들어가야 하므로 now() 넣고 나머지는 ?
		String sql = "INSERT INTO member VALUES(null,?,?,?,?,?,?,?,?,?,now())";
		try {
			// 예외 제어를위해 try catch 문 사용!
			pstmt = conn.prepareStatement(sql);
			
			// 3.pstmt 의 ? 에 값 채우기
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getJumin());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getJob());
			pstmt.setString(7, member.getGender());
			pstmt.setString(8, member.getHobby());
			pstmt.setString(9, member.getMotivation());

			// 4. pstmt 의 db sql 실행문 동작 후 리턴값 받기
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("db run error !");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { // try catch 문이 어떤 형식이로든 끝나면 실행하는 구절
			// 5. 동작 후 pstmt 를 닫기
			// 단 !! conn 은 아직 유지(결과 확인후 커밋 또는 롤백하기 위함)
			JdbcUtil.close(pstmt);
		}
		

		return insertCount;
	}
	
	public boolean selectCorrectUser(MemberBean member) {
		boolean isCorrectUser = false;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isCorrectUser = true;
			}
			// 입력받은 아이디와
		}catch(SQLException e) {
			System.out.println("db run error !");
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		
		return isCorrectUser;
	}
	
	public MemberBean selectMember(String id) {
		// 리턴할값과 pstmt, rs 초기화
		MemberBean member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// sql문 작성
		String sql = "SELECT * FROM member WHERE id = ?";
		
		// pstmt 에 sql 문 적용
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberBean();

				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setJumin(rs.getString("jumin"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setGender(rs.getString("gender"));
				member.setHobby(rs.getString("hobby"));
				member.setMotivation(rs.getString("motivation"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("MemberDAO - selectMember sql Exception");
			e.printStackTrace();
		}
		
		return member;
	}
	
	public List<MemberBean> selectMemberList() {
		// 리턴할값과 pstmt, rs 초기화
		List<MemberBean> memberList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// sql문 작성
		String sql = "SELECT * FROM member";
		
		// pstmt 에 sql 문 적용
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			memberList = new ArrayList<MemberBean>();
			while(rs.next()) {
				MemberBean member = new MemberBean();

				member.setIdx(rs.getInt("idx"));
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setJumin(rs.getString("jumin"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setGender(rs.getString("gender"));
				member.setHobby(rs.getString("hobby"));
				member.setMotivation(rs.getString("motivation"));
				member.setHire_date(rs.getDate("hire_date"));
				
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("MemberDAO - selectMember sql Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return memberList;
	}
	public int deleteMember(String id, String passwd) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		// sql 문 작성

		String sql = "DELETE FROM member WHERE id = ? AND passwd = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("MemberDAO - deleteMember() SQL Exception");
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}
	
	
}
