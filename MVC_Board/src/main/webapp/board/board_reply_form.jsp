<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
	#replyForm {
		width: 500px;
		height: 450px;
		border: 1px solid red;
		margin: auto;
	}
	
	h1 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 450px;
	}
	
	.td_left {
		width: 150px;
		background: orange;
		text-align: center;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	<div align="right">
	<jsp:include page="/inc/top.jsp"></jsp:include>
	</div>
	</header>
	<!-- 게시판 답글 작성 -->
	<section id="replyForm">
		<h1>게시판 답글 작성</h1>
		<form action="BoardReplyPro.bo" name="boardForm" method="post" enctype="multipart/form-data">
		<%-- 입력받지 않은 글번호 페이지 번호를 hidden 속성으로 전달 --%>
		<%-- 답글 작성에 필요한 정보와 페이지 번호를 전달 --%>
			<input type="hidden" name="board_num" value="${param.board_num }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="board_re_ref" value="${board.board_re_ref }">
			<input type="hidden" name="board_re_lev" value="${board.board_re_lev }">
			<input type="hidden" name="board_re_seq" value="${board.board_re_seq }">
			<table>				
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" name="board_name" required="required"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right">
						<input type="password" name="board_pass" required="required" />
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right"><input type="text" name="board_subject"  value="Re : ${board.board_subject }" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td class="td_right">
						<textarea id="board_content" name="board_content" cols="40" rows="15" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_file">파일 첨부</label></td>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td class="td_right"><input type="file" name="board_file" /></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답글 작성">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>