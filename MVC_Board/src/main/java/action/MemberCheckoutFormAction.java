package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

public class MemberCheckoutFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 사용될 변수 객체 초기화
		ActionForward forward = null;
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		//세션에서 sId 값 받아오기
		String sId = (String)session.getAttribute("sId");
		
		// sId 값 있는지 없는지 확인후 없으면 alert 띄우고 뒤로가기 동작
		response.setContentType("text/html; charset=UTF-8");
		if(sId != null) {
			forward = new ActionForward();
			forward.setPath("member/member_checkout_form.jsp");
			forward.setRedirect(false);
		}
		else {
			try {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('잘못된 접근입니다!');");
				out.print("history.back();");
				out.print("</script>");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("MemberCheckoutFormAction - ActionForward() IOException");
			}
		}
		
		return forward;
	}

}
