package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BoardDeleteProService;
import vo.ActionForward;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isBoardWriter = false;
		boolean isDeleteSuccess = false;
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String passwd = (request.getParameter("board_pass"));
		
		BoardDeleteProService service = new BoardDeleteProService();
		
		isBoardWriter = service.isBoardWriter(board_num, passwd);

		forward = new ActionForward();
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			if(isBoardWriter) {
				String board_real_file = service.getBoardRealFile(board_num);
				isDeleteSuccess = service.removeBoard(board_num);
				
				if(isDeleteSuccess) {
					
					try {
						// -------- 삭제 성공 시 실제 업로드 된 파일 삭제 작업 수행 --------
						// 1. 가상 업로드 디렉토리(이클립스 상의 폴더) 지정
						String uploadPath = "upload";
						// 2. 실제 톰캣 상의 업로드 디렉토리 알아내기
						String realPath = request.getServletContext().getRealPath(uploadPath);
						// 3. java.nio.file.Paths 클래스의 get() 메서드를 호출하여
						//    파일 경로를 관리하는 객체 생성(파라미터 : "경로명/파일명")
						//    => 리턴타입 : java.nio.file.Path
						Path path = Paths.get(realPath + "/" + board_real_file);
						// 4. java.nio.file.Files 클래스의 deleteIfExists() 메서드를 호출하여
						//    실제 파일 삭제 작업 수행(파라미터 : Path 객체)
						Files.deleteIfExists(path);
					} catch (IOException e) {
						System.out.println("삭제할 파일이 존재하지 않습니다!");
					}
					
					forward.setPath("BoardList.bo?pageNum=" + pageNum);
					forward.setRedirect(true);
				}
				else {

					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('삭제 실패!');");
					out.print("history.back();");
					out.print("</script>");
					out.flush();
				}
			}
			else {

				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('삭제 실패! 비밀번호를 확인해 주세요');");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("BoardDeleteProAction - execute() IOException");
		}
		
		return forward;
	}

}
