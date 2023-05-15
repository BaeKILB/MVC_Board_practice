<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style>
	#passForm {
		width: 300px;
		margin: auto;
		border: 1px solid orange;
		text-align: center;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		width: 300px;
		margin: auto;
		text-align: center;
	}
	
</style>
</head>
<body>
	<script type="text/javascript">
	function confirmCheckout() {
		let result = confrim("탈퇴하시겠습니까? 복구가 불가능합니다");
		if(result){
			return true;
		}
	}
	</script>
	
	<header>
	<div align="right">
	<jsp:include page="/inc/top.jsp"></jsp:include>
	</div>
	 </header>
	
	<!-- 회원 탈퇴 -->
	<h2>회원 탈퇴</h2>
	<section id="passForm">
		<form action="MemberCheckoutPro.me" name="deleteForm" method="post">
				<%-- 입력받지 않은 글번호 페이지 번호를 hidden 속성으로 전달 --%>
			<table>
				<tr>
					<td><label>회원 비밀번호</label></td>
					<td><input type="password" name="passwd" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="회원탈퇴" onclick="return confirmCheckout()">&nbsp;&nbsp; 
						<%-- 타입 submit 은 boolean 리턴값을 받아 submit 할지 안할지 결정할수 있음--%>
						<input type="button" value="돌아가기" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>





