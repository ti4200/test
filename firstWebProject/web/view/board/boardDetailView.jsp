<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetailView</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<h1 align="center">게시글 상세보기 페이지</h1>
<br>
<table border="1" align="center" width="700" cellspacing="0" cellpadding="10">
<tr><th width="200">글 번 호</th><td>${requestScope.board.boardNum }</td></tr>
<tr><th width="200">글 제 목</th><td>${board.boardTitle }</td></tr>
<tr><th width="200">작 성 자</th><td>${board.boardWriter }</td></tr>
<tr><th width="200">작성날짜</th><td>${board.boardDate }</td></tr>
<tr><th width="200">조 회 수</th><td>${board.boardReadCount }</td></tr>
<tr><th width="200">첨부파일</th>
<td>
	<c:if test="${not empty board.originalFileName }">
		<a href="/first/fdown?ofile=${board.originalFileName }&rfile=${board.renameFileName }">
		${board.originalFileName }</a>
	</c:if>
	<c:if test="${empty board.originalFileName }">
		없음
	</c:if>	
</td></tr>
<tr><th width="200">글 내 용</th><td>${board.boardContent }</td></tr>
<tr><th width="200" colspan="2">
	<c:if test="${loginUser.userId eq board.boardWriter }">
		<button onclick="javascript:location.href='/first/bupdateView?num=${board.boardNum}'">수정페이지로 이동</button> &nbsp;
	</c:if>
	
	<c:if test="${board.boardLev eq 0 }">
		<button onclick="javascript:location.href='/first/view/board/replyForm.jsp?num=${board.boardNum}&level=${board.boardLev }&seq=${board.boardSeq }'">답글달기</button> &nbsp;
	</c:if>
	<c:if test="${board.boardLev eq 1 }">
		<button onclick="javascript:location.href='/first/view/board/replyForm.jsp?num=${board.boardNum}&level=${board.boardLev }&seq=${board.boardSeq }&ref=${board.boardRef }'">답글달기</button> &nbsp;
	</c:if>
	
	<c:if test="${loginUser.userId eq board.boardWriter or loginUser.userId eq 'admin'}">
		<button onclick="javascript:location.href='/first/bdelete?num=${board.boardNum}&level=${board.boardLev }'">글삭제</button> &nbsp;
	</c:if>
	
	<button onclick="javascript:location.href='/first/blist?page=${currentPage}'">목록보기</button>
</th></tr>
</table>
</body>
</html>




