package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sId");
		if(id == null || !id.equals("admin")) {
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
			//회원 목록 조회를 위해 MemberListService - getMemberList() 메서드 호출
			MemberListService service = new MemberListService();
			List<MemberBean> memberList = service.getMemberList();
			
			request.setAttribute("memberList", memberList);
			
			forward = new ActionForward();
			forward.setPath("member/member_list.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
