package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action {

	// 프론트 컨트롤러 호출 받아
	// 회원가입 비즈니스 로직 수행할 execute() 메서드 정의
	// => 파라미터 : HttpServletRequest request, HttpServletResponse response
	// 해당 클래스에서 request response 를 그대로 받아 쓰기 위함 !
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 리턴할 forward 객체와 db 서비스 결과값,서비스 객체 미리 만들어 두기
		ActionForward forward = null;
		boolean isJoinSuccess = false;
		MemberJoinProService service = new MemberJoinProService();
		// 2. 파라미터 값 가져오기
		MemberBean member = new MemberBean();

		// idx 는 자동 생성이므로 가져오기 X
		// hire_date 는 sql문 작성시 now()로 자동 생성 예정

		member.setName(request.getParameter("name"));
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setJumin(request.getParameter("jumin1") + request.getParameter("jumin2"));
		member.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2"));
		member.setJob(request.getParameter("job"));
		member.setGender(request.getParameter("gender"));
		// 취미는 값을 여러개 가지고 있으므로 getParameterValues() 메서드를 사용
		String[] hobbys = request.getParameterValues("hobby");

		// 오류 방지 위해 널스트링 먼저 넣기
		member.setHobby("");

		// 만약 hobbys가 null 아닐때만 동작
		if (hobbys != null) {

			for (int i = 0; i < hobbys.length; i++) {
		
				if (i != hobbys.length - 1) {
					member.setHobby(member.getHobby() + hobbys[i] + "/");
				}
				else {
					member.setHobby(member.getHobby() + hobbys[i]);					
				}
			}
		}
		
		member.setMotivation(request.getParameter("motivation"));

		// 3.서비스로 전달하여 결과 판별 결과 받기
		isJoinSuccess = service.joinMember(member);

		if (isJoinSuccess) {
			// 성공
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);

		} else // 실패
		{
			try {
				//자바스크립트를 사용하여 alert으로 오류 띄운 후 뒤로가기
				
				// html 작성을 위한 문자셋 설정
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원 가입 오류!');");
				out.print("history.back();");
				out.print("</script>");
			} catch (IOException e) {
				System.out.println("out 실패");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
		}

		// 4. 결과 판별

		return forward;
	}
}
