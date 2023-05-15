package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardRecentlyListAction;
import vo.ActionForward;

/**
 * Servlet implementation class MainFrontController
 */
@WebServlet("/")
public class MainFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 안깨지게 엔코딩 설정
		request.setCharacterEncoding("UTF8");
		
		Action action = null;
		ActionForward forward = null;
		
		// BoardRecentlyListAction 클래스를 통해 최근 게시물 5개 조회
		
		action = new BoardRecentlyListAction();
		forward = action.execute(request, response);
		
		//
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
