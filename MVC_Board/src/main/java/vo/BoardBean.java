package vo;

import java.sql.Timestamp;

/*
 * mvc_board5.board 테이블 생성
 * ----------------------------
 * 글번호(board_num) - 정수, PK
 * 작성자(board_name) - 문자열(16) NN
 * 패스워드(board_pass) - 문자열(16) NN
 * 제목(board_subject) - 문자열(50) NN
 * 내용(board_content) - 문자열(2000) NN
 * 파일명(board_file) - 문자열(200) NN
 * 진짜 파일명(board_real_file) - 문자열(200) NN
 * 참조글번호(board_re_ref) - 정수 NN
 * 들여쓰기레벨(board_re_lev) - 정수,NN
 * 순서번호(board_re_seq) - 정수 NN
 * 조회수(board_readcount) - 정수 NN
 * 작성일(board_date) - 날짜 NN
 */

public class BoardBean {

	private int board_num;
	private String board_name;
	private String board_pass;
	private String board_subject;
	private String board_content;
	private String board_file;
	private String board_real_file;
	private int board_re_ref;
	private int board_re_lev;
	private int board_re_seq;
	private int board_readcount;
	private Timestamp board_date;

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_pass() {
		return board_pass;
	}

	public void setBoard_pass(String board_pass) {
		this.board_pass = board_pass;
	}

	public String getBoard_subject() {
		return board_subject;
	}

	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_file() {
		return board_file;
	}

	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}

	public String getBoard_real_file() {
		return board_real_file;
	}

	public void setBoard_real_file(String board_real_file) {
		this.board_real_file = board_real_file;
	}

	public int getBoard_re_ref() {
		return board_re_ref;
	}

	public void setBoard_re_ref(int board_re_ref) {
		this.board_re_ref = board_re_ref;
	}

	public int getBoard_re_lev() {
		return board_re_lev;
	}

	public void setBoard_re_lev(int board_re_lev) {
		this.board_re_lev = board_re_lev;
	}

	public int getBoard_re_seq() {
		return board_re_seq;
	}

	public void setBoard_re_seq(int board_re_seq) {
		this.board_re_seq = board_re_seq;
	}

	public int getBoard_readcount() {
		return board_readcount;
	}

	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}

	public Timestamp getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Timestamp board_date) {
		this.board_date = board_date;
	}
	@Override
	public String toString() {
		return "BoardBean [board_num=" + board_num + ", board_name=" + board_name + ", board_pass=" + board_pass
				+ ", board_subject=" + board_subject + ", board_content=" + board_content + ", board_file=" + board_file
				+ ", board_real_file=" + board_real_file + ", board_re_ref=" + board_re_ref + ", board_re_lev="
				+ board_re_lev + ", board_re_seq=" + board_re_seq + ", board_readcount=" + board_readcount
				+ ", board_date=" + board_date + "]";
	}

}
