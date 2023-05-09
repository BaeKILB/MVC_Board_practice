<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	function logout() {
		let isLogout = confirm("로그아웃 하시겠습니까?");
		
		if(isLogout){
			location.href = "./MemberLogout.me";
		}
	}
</script>

<div id="member_area">
	<c:choose>
		<c:when test="${empty sessionScope.sId}">
		<a href="./">Home</a>
		<a href="./MemberLoginForm.me">Login</a>
		<a href="./MemberJoinForm.me">Join</a>
	
		</c:when>
		<c:otherwise>	
		<%-- 아이디 클릭 시 회원정보 조회 --%>	
		<a href="./">Home</a>
		<a href="./MemberInfo.me">${sessionScope.sId } 님</a>		
		<a href="javascript:logout();">Logout</a>	
		<%-- 만약 세션아이디가 admin 일 경우 관리자 페이지 표시 --%>
		<c:if test="${sessionScope.sId eq 'admin'}">
			<a href="./MemberList.me">관리자 페이지</a>
		</c:if>
		</c:otherwise>
	</c:choose>
	

</div>

