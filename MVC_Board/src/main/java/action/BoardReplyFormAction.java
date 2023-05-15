package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardReplyFormService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		BoardBean board = null;
		BoardReplyFormService service = new BoardReplyFormService();
		
		int board_num = Integer.parseInt(request.getParameter("board_num")) ;
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		board = service.getBoard(board_num);
		
		if(board != null) {
			request.setAttribute("board", board);
			forward = new ActionForward();
			forward.setPath("board/board_reply_form.jsp");
			// 페이지를 바로 띄울것이므로 디스패치 방식(url 변하지않음)
			forward.setRedirect(false);
		}
		else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('글을 불러오지 못했습니다!');");
				out.print("history.back();");
				out.print("</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("BoardReplyFormAction - execute() IOException");

			}
		}
		
		return forward;
	}

}
