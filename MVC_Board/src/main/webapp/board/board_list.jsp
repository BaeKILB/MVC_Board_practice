<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
#listForm {
	width: 1024px;
	max-height: 610px;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 1024px;
}

#tr_top {
	background: orange;
	text-align: center;
}

table td {
	text-align: center;
}

#subject {
	text-align: left;
	padding-left: 20px;
}

#pageList {
	margin: auto;
	width: 1024px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 1024px;
	text-align: center;
}

#buttonArea {
	margin: auto;
	width: 1024px;
	text-align: right;
}

a {
	text-decoration: none;
}
/* 글 제목 정렬 변경 */
#subject {
	text-align: left;
	margin-left: 20px;
}
</style>
</head>
<body>
	<%-- pageNum 파라미터 가져와서 저장(없을경우 기본값 1로 설정) --%>
	
	<c:set var="pageNum" value="1" />
	<c:if test="${!empty param.pageNum }">
		<c:set var="pageNum" value="${param.pageNum }" />
	</c:if>
	
	
	<!-- 게시판 리스트 -->
	<section id="listForm">
		<section id="buttonArea">
			<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo'" />
		</section>
		<h2>게시판 글 목록</h2>
		<table>
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
					<td id="subject"><a
						href="BoardDetail.bo?board_num=${board.board_num }&pageNum=${pageNum}">${board.board_subject }</a>
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
	<section id="pageList">
		<%--
			현재 페이지 번호가 1보다 클 경우만 이전버튼 동작
			=> 클릭 시 BoardList.bo 서블릿 요청
		 --%>
		 
		<input 
		type="button" 
		value="이전" 
		<c:if test="${pageNum > 1 }">
		onclick="location.href = 'BoardList.bo?pageNum=${pageNum - 1}'"
		</c:if>
		>
		<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
			<%-- 각 페이지마다 하이퍼링크를 달고
			현 페이지 번호에는 하이퍼 링크를 빼기
			
			 --%>
			 <c:choose>
				<c:when test="${ i eq pageNum}">
				<b>${i }</b>
				</c:when>
				<c:otherwise>
				<a href="BoardList.bo?pageNum=${i }">${i }</a>
				</c:otherwise> 
			 </c:choose>
		</c:forEach>
		
		<input 
		type="button" 
		value="다음"
		<c:if test="${pageNum < pageInfo.maxPage}">
		onclick="location.href = 'BoardList.bo?pageNum=${pageNum + 1}'"
		</c:if>
		>
	</section>
</body>
</html>













