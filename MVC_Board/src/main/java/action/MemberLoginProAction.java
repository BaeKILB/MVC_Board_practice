package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import svc.MemberLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("MemberLoginProAction");
		
		ActionForward forward = null;
		
		MemberBean member = new MemberBean();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		
		// 서비스 클래스 인스턴스 생성 후 isCorrectUser() 메서드 호출
		MemberLoginProService service = new MemberLoginProService();
		boolean isCorrectUser = service.isCorrectUser(member);
		
		//로그인 성공 여부 판별
		if(!isCorrectUser) {
			//로그인 실패 띄우고 이전페이지로
			try {
				//자바스크립트를 사용하여 alert으로 오류 띄운 후 뒤로가기
				
				// html 작성을 위한 문자셋 설정
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인 실패!');");
				out.print("history.back();");
				out.print("</script>");
			} catch (IOException e) {
				System.out.println("memberLoginProAction - out 실패");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			// request 에서 session 받아오기
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(36000);
			session.setAttribute("sId", member.getId());
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
