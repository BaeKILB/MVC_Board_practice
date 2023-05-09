package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("member info action");
		ActionForward forward = null;
		// 세션에 저장된 세션 아이디 가져오기
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		
		if(id.equals("admin") && request.getParameter("id") != null) {
			id = request.getParameter("id");
			
		}
		
		// 세션 아이디가 존재하지 않을경우 자바스크립트로 "잘못된 접근입니다" 출력 후 이전페이지로 돌아가기
		if(id == null) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('잘못된 접근입니다!');");
				out.print("history.back();");
				out.print("</script>");
			} catch (IOException e) {
				System.out.println("MemberInfoAction - out exception");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			//회원 정보 조회 요청을 위해 MemberService - getMember() 메서드 호출
			// 파라미터 id 리턴타입 MemberBean
			MemberInfoService service = new MemberInfoService();
			MemberBean member = service.getMember(id);
			
			if(member == null) {
				try {
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('이상 발생');");
					out.print("history.back();");
					out.print("</script>");
				} catch (IOException e) {
					System.out.println("MemberInfoAction - out exception");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return forward;
			}
			
			request.setAttribute("member", member);
			// member/member_info.jsp 페이지로 포워딩
			forward = new ActionForward();
			forward.setPath("member/member_info.jsp");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}
	
}
