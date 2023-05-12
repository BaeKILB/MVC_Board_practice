package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import svc.BoardModifyProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		BoardBean board = new BoardBean();
		boolean isBoardWriter = false;
		boolean isModifySuccess = false;

		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String passwd = (request.getParameter("board_pass"));

		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));

		BoardModifyProService service = new BoardModifyProService();
		isBoardWriter = service.isBoardWriter(board_num, passwd);



		try {
			response.setContentType("text/html; charset=UTF-8");
			if (isBoardWriter) {

				isModifySuccess = service.modifyBoard(board_num, passwd, board);

				if (isModifySuccess) {
					forward = new ActionForward();
					forward.setPath("BoardDetail.bo?board_num=" + board_num + "&pageNum=" + pageNum);
					forward.setRedirect(true);
				} else {
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('글 수정 실패!');");
					out.print("</script>");
					out.print("history.back();");
					out.flush();

				}
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('접근 권한이 없습니다!');");
				out.print("</script>");
				out.print("history.back();");
				out.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardModifyProAction - execute IOException");
		}

		return forward;
	}

}
