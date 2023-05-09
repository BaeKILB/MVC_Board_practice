package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction_backup implements Action{

	// 프론트 컨트롤러 호출 받아
	// 회원가입 비즈니스 로직 수행할 execute() 메서드 정의
	// => 파라미터 : HttpServletRequest request, HttpServletResponse response
	// 해당 클래스에서 request response 를 그대로 받아 쓰기 위함 !
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("MemberJoinProAction");
		ActionForward forward = null;
		
		// 파라미터 값 가지오기
		MemberBean mb = new MemberBean();
		
		mb.setId(request.getParameter("id"));
		mb.setName(request.getParameter("name"));
		mb.setPasswd(request.getParameter("passwd"));
		mb.setJumin(request.getParameter("jumin1") + request.getParameter("jumin2"));
		mb.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2") );
		mb.setJob(request.getParameter("job"));
		mb.setGender(request.getParameter("gender"));
//		mb.setHobby(request.getParameter("hobby"));
		//취미는 복수 개 선택이 가능하므로 배열로 전달받음
		// 따라서 파라미터를 배열로 받을수있는 getParameterValues() 를 사용
		// 배열로 받기 전 null 값 으로 인한 문자열 추가 문제를 해결하기 위해 
		// 널 스트링 추가!
		String[] hobbys = request.getParameterValues("hobby");
		
		mb.setHobby("");
		
		// 나중에 값을 꺼낼때 / 로 구분 지을수 있게 추가해주기
		if(hobbys != null) {
			for(int i = 0; i < hobbys.length; i++) {
				mb.setHobby(mb.getHobby() + hobbys[i]); 
				if(i != hobbys.length-1) mb.setHobby(mb.getHobby() + "/"); 
			}
			
		}
		
		
		mb.setMotivation(request.getParameter("motivation"));
		
		
		// 회원가입 비즈니스 로직 요청 수행
		MemberJoinProService service = new MemberJoinProService();
		boolean isJoinSuccess = service.joinMember(mb);
		
		//가입결과 판별
		if(!isJoinSuccess) {
//			System.out.println("가입 실패 !");
			// 자바스크립트를 사용하여 "회원 가입 실패!" 출력 후 이전페이지로 돌아갈
			// 응답데이터를 생성
			// 웹 브라우저로 html 태그 등을 내보내기
			
			//2. thml 문서타입 설정
			response.setContentType("text/html; charset=UTF-8");
			try {
				// 3. PrintWriter 객체의 print() 또는 println() 사용
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원가입 실패');");
				out.println("history.back();");
				out.println("</script>");
				
				out.flush(); // out 객체의 모든 데이터 출력(내보내기) 호출가능
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			// 가입성공

			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
			
		}
		return forward;
	}
}
