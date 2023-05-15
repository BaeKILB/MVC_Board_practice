<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="./css/default.css" rel="styleSheet">
</head>
<body>

	<header>
	<div align="right">
	<jsp:include page="/inc/top.jsp"></jsp:include>
	</div>
	</header>

	<article>
		<h1>회원 가입</h1>
		<form action="Member.me" method="post" name="fr">
			<table border="1">
				<tr>
					<th>회원번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>E-Mail</th>
					<th>가입일</th>
					<th>상세정보</th>
				</tr>
				<c:forEach items="${memberList }" var="member">
				<tr>
					<td>${member.idx }</td>
					<td>${member.name}</td>
					<td>${member.id}</td>
					<td>${member.email}</td>
					<td>${member.hire_date}</td>
					<td>
					<input type="button" value="상세정보" onclick="location.href = 'MemberInfo.me?id=${member.id }'" />
					</td>
				</tr>	
				</c:forEach>
			
				<%--
				</tr>
					<td colspan="2" align="center"><input type="submit" value="가입">
						<input type="reset" value="초기화"> <input type="button"
						value="돌아가기"></td>
				</tr>
				 --%>
			</table>
		</form>

	</article>

</body>
</html>