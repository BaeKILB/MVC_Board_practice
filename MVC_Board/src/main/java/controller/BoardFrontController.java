package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteProAction;
import action.BoardDetailAction;
import action.BoardListAction;
import action.BoardModifyFromAction;
import action.BoardModifyProAction;
import action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 안깨지게 엔코딩 설정
		request.setCharacterEncoding("UTF8");
		
		// 현재 서블렛 주소 받아오기
		String servletPath = request.getServletPath();
		System.out.println("Board.bo : " +servletPath);
		
		// 포워드할때 사용할 클래스 미리 만들기 (null 값을 넣음)
		ActionForward forward = null;
		Action action = null;
		
		if(servletPath.equals("/BoardWriteForm.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기
			forward = new ActionForward();
			forward.setPath("board/board_write_form.jsp");
			// 페이지를 바로 띄울것이므로 디스패치 방식(url 변하지않음)
			forward.setRedirect(false);
		}
		// 글쓰기 submit 을 받으면 동작
		else if(servletPath.equals("/BoardWritePro.bo")) {

			action = new BoardWriteProAction();
			forward = action.execute(request, response);
			// 현 url 상태는 비즈니스 로직으로 가는 url 이기떄문에
			// 새페이지로 이동하는 리다이렉트 채택
			
		}
		
		// 비즈니스 로직을 필요로함 으로 
		// action 쪽으로 request 와 response를 넘겨주고
		// forward 정보를 받아오기

		else if(servletPath.equals("/BoardList.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기
			action = new BoardListAction();
			forward = action.execute(request, response);	
		}
		else if(servletPath.equals("/BoardDetail.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기
			action = new BoardDetailAction();
			forward = action.execute(request, response);	
		}		
		else if(servletPath.equals("/BoardModifyFrom.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기
			forward = new ActionForward();
			forward.setPath("board/board_modify_form.jsp");
			forward.setRedirect(false);
		}
		
		else if(servletPath.equals("/BoardDeleteForm.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기

			forward = new ActionForward();
			forward.setPath("board/board_delete_form.jsp");
			forward.setRedirect(false);
		}
		// 삭제 요청을 받으면 동작
		else if(servletPath.equals("/BoardDeletePro.bo")) {
			action = new BoardDeleteProAction();
			forward = action.execute(request, response);
			// 현 url 상태는 비즈니스 로직으로 가는 url 이기떄문에
			// 새페이지로 이동하는 리다이렉트 채택
			
		}		
		else if(servletPath.equals("/BoardModifyForm.bo")) {
			// 비즈니스 로직을 필요로 하지 않음으로
			// forward 에 인스턴스를 만들어 직접 값을 집어넣기
			System.out.println("BoardModifyFrom");

			action = new BoardModifyFromAction();
			forward = action.execute(request, response);
		}
		// 삭제 요청을 받으면 동작
		else if(servletPath.equals("/BoardModifyPro.bo")) {
			action = new BoardModifyProAction();
			forward = action.execute(request, response);
			// 현 url 상태는 비즈니스 로직으로 가는 url 이기떄문에
			// 새페이지로 이동하는 리다이렉트 채택
			
		}
		//포워드 객체 확인해서 디스패치 또는 리다이렉트 하기
		if(forward != null) {
			
			if(forward.isRedirect()) {
				//리다이렉트 방식으로 페이지 열기
				System.out.println("redirect");
				response.sendRedirect(forward.getPath());
			}
			else {
				System.out.println("dispatch");
				//디스패치 방식으로 페이지 열기
				RequestDispatcher dispatch = request.getRequestDispatcher(forward.getPath());
				dispatch.forward(request, response);
			}
		}
		else {
			
			// 만약, ActionForward 객체가 null 이면 아무 동작도 수행하지 않음(이동 X)
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
