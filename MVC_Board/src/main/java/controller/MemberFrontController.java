package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.MemberCheckoutFormAction;
import action.MemberCheckoutProAction;
import action.MemberInfoAction;
import action.MemberJoinProAction;
import action.MemberListAction;
import action.MemberLoginProAction;
import action.MemberLogoutProAction;
import vo.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController");
		
		// post 방식 요청에 대한 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		//공통으로 사용할 변수 선언
		ActionForward forward = null;
		Action action = null; // 업캐스팅
		
		
		//서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// 추출된 서블릿주소를 비교 수행후
		// 각 주소에 따른 액션 작업 요청	
		
		
		if(command.equals("/MemberLoginForm.me")) {
			System.out.println("회원가입 폼");
//			RequestDispatcher dispatch = request.getRequestDispatcher("member/member_login_form.jsp");
//			dispatch.forward(request, response);
//			
			
			//공통 포워딩 작업을 위한 ActionForward 클래스 활용
			forward = new ActionForward();
			forward.setPath("member/member_login_form.jsp");
			forward.setRedirect(false);
			
			
		}
		//비즈니스 로직(db 작업 등) 불필요하무로 뷰 페이지로 바로 이동
		// 뷰페이지 바로이동시 url 안바뀌게 하기
		else if (command.equals("/MemberJoinForm.me")) {
			System.out.println("회원로그인 폼");
//			RequestDispatcher dispatch = request.getRequestDispatcher("member/member_join_form.jsp");
//			dispatch.forward(request, response);
			
			forward = new ActionForward();
			forward.setPath("member/member_join_form.jsp");
			forward.setRedirect(false);
			
			
		}
		else if (command.equals("/MemberLoginPro.me")) {
			// 회원 로그인 비즈니스 로직
			// 비즈니스 로직을 처리할 코드 불러오기
			action = new MemberLoginProAction();
			forward = action.execute(request, response);
			
		}
		else if (command.equals("/MemberJoinPro.me")) {
			// 회원 가입 비즈니스 로직
			// 비즈니스 로직을 처리할 코드 불러오기
			 action = new MemberJoinProAction();
			
			forward = action.execute(request, response);
		}
		else if(command.equals("/MemberListPro.me")) {
			//멤버 리스트 보여주기 비즈니스 로직
			action = new MemberListAction();
			
			forward = action.execute(request, response);
		}
		else if(command.equals("/MemberLogout.me")){
			action = new MemberLogoutProAction(); 
			forward = action.execute(request, response);
		}
		else if(command.equals("/MemberInfo.me")){
			action = new MemberInfoAction(); 
			forward = action.execute(request, response);
		}
		else if(command.equals("/MemberList.me")){
			action = new MemberListAction(); 
			forward = action.execute(request, response);
		}		
		else if(command.equals("/MemberCheckoutForm.me")){
			// 멤버 회원탈퇴 물어보는 form
			// 세션에 sId 있는지(로그인 여부) 확인 후 화면 띄우기
			action = new MemberCheckoutFormAction(); 
			forward = action.execute(request, response);
		}
		else if(command.equals("/MemberCheckoutPro.me")){
			// 회원탈퇴 동작
			action = new MemberCheckoutProAction(); 
			forward = action.execute(request, response);
		}
		
		//ActionForward 객체의 포워딩 정보를 사용 공통으로 포워드 처리
		// 1.null 이 아닐경우 판별
		if(forward != null) {
			// 2. 포워딩 방식 판별
			if(forward.isRedirect()) {
				System.out.println("redirect");
				// 3. 포워딩
				// 리다이렉트 방식 수행
				response.sendRedirect(forward.getPath());
			}
			else {
				System.out.println("dispatch");
				// 3. 포워딩
				// 디스패치 방식 수행
				RequestDispatcher dispatch = request.getRequestDispatcher(forward.getPath());
				dispatch.forward(request, response);
			}
		}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
