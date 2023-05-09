package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;

public class BoardWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// 사용될 변수,객체 초기화
		ActionForward forward = null;
		BoardWriteProService service = null;
		
		boolean isWriteSuccess = false;
		//--------------------------------------------------------
		// 게시물 작성자(=클라이언트)의 IP주소를 알아내야하는 경우
//		String writerAddr = request.getRemoteAddr();
//		System.out.println("작성자 IP : " + writerAddr);
		//--------------------------------------------------------
		
		
		try {
			//업로드에 사용될 가상의 디렉토리 지정
			// => upload 폴더 이클립스 프로젝트에 생성
			String uploadPath = "upload"; // webapp 폴더 안 upload 폴더 지정
			// 단 이클립스 프로젝트에 생성한 디렉토리는 실제 업로드 되는 공간은 아님
			// => 톰캣이 관리하는 실제 업로드 경로를 알아내야함
			// => request 객체의 getServletContext() 메서드를 호출
			//		서블릿 컨텍스트 객체를 리턴받아 다시 getRealPath() 메서드를 호출하여
			//		실제 업로드 경로 알아낼 수 있음(파라미터:가상 업로드 경로)
			
			
			String realPath = request.getServletContext().getRealPath(uploadPath);
			
			//C:\Users\ user\Documents\workspace_jsp5_myfix\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC_Board\ upload
			
			//최대 업로드 파일 사이즈 지정
			// =>10MB 제한
			int fileSize = 1024 * 1024  * 10;
			
			// 파일 업로드 처리를 위한 MultipartRequest 객체 생성 => cos.jar 라이브러리 필요
			
			MultipartRequest multi = new MultipartRequest(
					request, 	//1. 실제 요청 정보 request 객체
					realPath, //2. 실제 업로드경로(getRealPath() 로 알아낸 경로)
					fileSize, 	//3. 파일 사이즈
					"UTF-8", 	//4. 한글 파일명 처리를 위한 인코딩 방식
					new DefaultFileRenamePolicy() // 5. 중복파일명을 처리할 객체
					);
			// => MultipartRequest 객체가 생성되는 시점에 업로드 됨
			
			//전달받은 폼 파라미터(데이터) 를 BoardBean 객체에 저장
			BoardBean board = new BoardBean();
			board.setBoard_subject(request.getParameter("board_name"));
			System.out.println(board.getBoard_subject());
			
			// 주의 MultipartRequest 사용시 request 객체의 데이터는 가져올 수 없음
			// => 따라서 MultipartRequest 객체를 사용하여 파라미터 값을 받아와야한다
			board.setBoard_name(multi.getParameter("board_name"));
			board.setBoard_pass(multi.getParameter("board_pass"));
			board.setBoard_subject(multi.getParameter("board_subject"));
			board.setBoard_content(multi.getParameter("board_content"));
			board.setBoard_file(multi.getOriginalFileName("board_file")); // 원본 파일 명
			board.setBoard_real_file(multi.getFilesystemName("board_file")); // 실제 업로드된 파일 명
			board.setBoard_re_ref(0);
			board.setBoard_re_lev(0);
			board.setBoard_re_seq(0);
			board.setBoard_readcount(0);
			
			// 주의 파일명은 getParameter 메서드로 단수 처리 불가능
			// => 원본 파일명: getOriginalFileName
			//		실제 업로드 되는 파일 명 : getFileSystemName()
			
			System.out.println(board);
			//서비스 객체 만들어서 메서드 호출
			service = new BoardWriteProService();
			isWriteSuccess = service.registBoard(board);
			
			if(isWriteSuccess) {
				forward = new ActionForward();
				forward.setPath("./BoardList.bo");
				forward.setRedirect(true);
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('글 쓰기 실패!);'");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward ;
	}

}
