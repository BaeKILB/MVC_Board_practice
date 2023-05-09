<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h1>로그인</h1>
		<form action="MemberLoginPro.me" method="post" name="fr">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" required="required">
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<!-- 키 누를때마다 checkPasswd() 함수에 입력받은 패스워드 전달하여 호출 --> <input
						type="password" name="passwd" required="required">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">
					</td>
				</tr>
			</table>
			
		</form>

	</article>

</body>
</html>