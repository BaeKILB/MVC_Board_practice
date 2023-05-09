package vo;
/*
 * member table 정의
 * 
 * 번호(idx) - 정수 pk auto_increment
 * 이름(name) - 문자(10자) NN
 * 아이디(id) - 문자(16자) UN, NN
 * 비밀번호(passwd) - 문자(16자), NN
 * 주민번호(jumin) - 문자(14자), UN , NN
 * 이메일(email) - 문자(50자), UN, NN
 * 직업(job) - 문자(10자) , NN
 * 성별(gender) - 문자(1자), NN
 * 취미(hobby) - 문자(50자), NN
 * 가입동기(motivation) - 문자(500자), NN
 * 
CREATE TABLE MEMBER
( idx int primary key AUTO_INCREMENT,
	name varchar(10) not null,
id varchar(16) not null unique,
passwd varchar(16) not null,
jumin varchar(14) not null unique,
email varchar(50) not null unique,
job varchar(10) not null ,
gender varchar(1) not null,
hobby varchar(50) not null,
motivation varchar(500) not NULL,
hire_date date
)
 */

import java.sql.Date;

public class MemberBean {
	private int idx;
	private String name;
	private String id;
	private String passwd;
	private String jumin;
	private String email;
	private String job;
	private String gender;
	private String hobby;
	private String motivation;
	private Date hire_date;
	
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	
	@Override
	public String toString() {
		return "MemberVO [idx=" + idx + ", name=" + name + ", id=" + id + ", passwd=" + passwd + ", jumin=" + jumin
				+ ", email=" + email + ", job=" + job + ", gender=" + gender + ", hobby=" + hobby + ", motivation="
				+ motivation + "]";
	}
}
