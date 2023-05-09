package vo;

public class ActionForward {
	private String path; // 포워딩 주소
	private boolean isRedirect; // 리다이렉트 여부

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	
	
}
