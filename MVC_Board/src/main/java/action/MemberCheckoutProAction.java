package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberCheckoutProService;
import vo.ActionForward;

public class MemberCheckoutProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 사용할 변수, 객체 선언
		ActionForward forward = null;
		boolean isMemberDelete = false;

		// 회원탈퇴를 위해 적은 비밀번호와 현재 세션에 있는 아이디와 매칭이 되는지 확인

		// 세션을 request 에서 들오고기
		HttpSession session = request.getSession();

		// 세션에서 id 가져오기
		String sId = (String) session.getAttribute("sId");

		// 비밀번호 가져오기
		String passwd = request.getParameter("passwd");

		// 서비스로 넘겨서 삭제작업 후 결과값 들고오기
		MemberCheckoutProService service = new MemberCheckoutProService();
		isMemberDelete = service.checkoutMemberDelete(sId, passwd);
		// 결과값따라 동작을 달리 하기

		response.setContentType("text/html; charset=UTF-8");
		try {
			if (isMemberDelete) {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('회원탈퇴가 정상적으로 처리되었습니다');");
				out.print("location.href = './';");
				out.print("</script>");
				out.flush();
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('접근 권한이 없습니다!');");
				out.print("location.href = './MemberInfo.me';");
				out.print("</script>");
				out.flush();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

}
