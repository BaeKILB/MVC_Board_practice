package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDetailService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyFromAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 사용될 객체 변수 초기화
		ActionForward forward = null;
		BoardDetailService service = new BoardDetailService();
		BoardBean board = null;
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		// 서비스에 getBoard() 호출
		board = service.getBoard(board_num);
		
		// 파라미터로 board 정보 보내기
		request.setAttribute("board", board);
		
		if(board != null) {			
			forward = new ActionForward();
			forward.setPath("board/board_modify_form.jsp");
			forward.setRedirect(false);
		}
		else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.print("alert('게시글 불러오기 실패하였습니다');");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return forward;
	}

}
