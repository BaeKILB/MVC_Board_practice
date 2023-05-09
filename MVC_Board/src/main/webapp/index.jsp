<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css 파일 불러오기 -->
<link href="css/default.css" rel="styleSheet">
</head>
<body>
	<header>
	<jsp:include page="inc/top.jsp"></jsp:include>
	</header>
	<article>
	<!-- 본문 표시 영역 -->
		<h1>MVC 게시판</h1>
		<h3><a href="BoardWriteForm.bo">글쓰기</a></h3>
		<h3><a href="BoardList.bo">글목록</a></h3>
		<h3><a href="MemberListPro.me">멤버 리스트 보기</a></h3>
	</article>
</body>
</html>