package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardListProService;
import vo.ActionForward;
import vo.BoardBean;
import vo.PageInfo;

public class BoardListAction implements Action {

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
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			
		}
		int startLow = (pageNum - 1) * listLimit;
		int listCount = 0;
		System.out.println("현재 페이지 : " + pageNum);
		//------------------------------------------------------------------------
		BoardListProService service = new BoardListProService();
		List<BoardBean> boardList = service.getBoardList(startLow,listLimit);
		
		
		//------------------------------------------------------------------------
		// JSP 페이지에서의 페이징 처리
		// 한 페이지에서 표시할 페이지 목록(번호) 계산
		// 1. BoardListService - getBoardListCount() 메서드를 호출하여
		//	전체 게시물 수 조회
		listCount = service.getBoardListCount();
		
		// 2. 한 폐이지에서 표시할 목록 갯수 설정
		int pageListLimit = 3;
		// 3.
		int maxPage = (listCount / listLimit) + (listCount % listLimit == 0 ? 0 : 1) ;
		
		// 4. 시작 페이지 번호 계산
		int startPage = (pageNum-1) / pageListLimit * pageListLimit + 1;
		
		// 5. 끝 페이지 계산
		int endPage = startPage + pageListLimit - 1;
		
		// 6. 만약 끝 페이지 번호가 전체(최대) 페이지 번호보다 큰 경우
		// 끝 페이지 번호를 최대페이지 번호로 교체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(listCount,pageListLimit,maxPage,startPage,endPage);
		
		//------------------------------------------------------------------------
		request.setAttribute("pageInfo", pageInfo);			
		request.setAttribute("boardList", boardList);
		// 포워드 객체 인스턴스 만들기
		forward = new ActionForward();
		forward.setPath("board/board_list.jsp");
		// 비즈니스 로직이 끝났으니 리다이렉트 로 다른주소에서 뷰가 뜨게 하기
		forward.setRedirect(false); 
		return forward;
	}

}
