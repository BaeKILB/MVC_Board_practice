package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class MemberLogoutProAction implements Action {
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		request.getSession().invalidate();
		
		forward.setPath("./");
		forward.setRedirect(true);
		return forward;
	}
}
