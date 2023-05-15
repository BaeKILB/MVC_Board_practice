<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<section>
		<h3>최신글 목록</h3>
		<table border="1">
			<tr id="tr_top">
				<th width="100px">번호</th>
				<th>제목</th>
				<th width="150px">작성자</th>
				<th width="150px">날짜</th>
				<th width="100px">조회수</th>
			</tr>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.board_num }</td>
					<td id="subject">
					<a href="BoardDetail.bo?board_num=${board.board_num }&pageNum=1">
						${board.board_subject }
						</a>
					</td>
					<td>${board.board_name }</td>
					<td>
						<%-- 
				jstl 의 fmt(format) 라이브러리를 활용 날짜형식 지정하기
				1) fmt:formatDate - Date 타입 날짜 형식 변경
					<fmt:formatDate value="날짜 및 시각객체" pattern="표현형태">
				2) fmt:parseDate - String 타입 날짜 형식 변경
				 --%> <fmt:formatDate value="${board.board_date }"
							pattern="yy-MM-dd HH:mm" />
					</td>
					<td>${board.board_readcount }</td>
				</tr>

			</c:forEach>
		</table>
	</section>
</body>
</html>