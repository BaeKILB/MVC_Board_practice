package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

// XXXAction 클래스에서 공통으로 사용될 메서드를 제약을주기위한 인터페이스 정의
public interface Action {
	//공통 메서드
	ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}
