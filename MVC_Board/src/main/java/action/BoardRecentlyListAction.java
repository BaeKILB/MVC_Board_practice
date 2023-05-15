package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListProService;
import svc.BoardRecentlyListService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardRecentlyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// 사용할 객체 생성 및 초기화
		ActionForward forward = null;
		//------------------------------------------------------------------------
		// 페이징 처리를 위해 목록 갯수 제한에 사용될 변수 선언
		int listLimit = 5; // 한 펭지에서 표시할 목록 갯수 지정
		// 파라미터로 전달받은 pageNum 값 가져오기
		//=> 단 전달받은 파라미터가 없을 경우 기본값 1 지정
		int pageNum = 1;

		int startLow = (pageNum - 1) * listLimit;
		int listCount = 0;
		//------------------------------------------------------------------------
		BoardRecentlyListService service = new BoardRecentlyListService();
		List<BoardBean> boardList = service.getRecentlyBoardList(startLow,listLimit);
		
		
		//------------------------------------------------------------------------
		// JSP 페이지에서의 페이징 처리
		// 한 페이지에서 표시할 페이지 목록(번호) 계산
		// 1. BoardListService - getBoardListCount() 메서드를 호출하여

		

		//------------------------------------------------------------------------

		request.setAttribute("boardList", boardList);
		// 포워드 객체 인스턴스 만들기
		forward = new ActionForward();
		forward.setPath("main.jsp");
		// 비즈니스 로직이 끝났으니 리다이렉트 로 다른주소에서 뷰가 뜨게 하기
		forward.setRedirect(false); 
		return forward;
	}

}
