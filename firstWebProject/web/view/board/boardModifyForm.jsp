<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardModifyForm</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<hr>
<h1 align="center">${board.boardNum } 번 글 수정 페이지</h1>
<c:if test="${board.boardLev eq 0 }">
	<form action="/first/bmodify0" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bnum" value="${board.boardNum }">
	<input type="hidden" name="rfile" value="${board.renameFileName }">
	<input type="hidden" name="ofile" value="${board.originalFileName }">
	<table align="center" width="700" border="1" cellspacing="0" cellpadding="10">
	<tr><td width="200" align="center" valign="middle">글 제 목</td>
		<td><input type="text" name="btitle" value="${board.boardTitle }"></td></tr>
	<tr><td width="200" align="center" valign="middle">작 성 자</td>
		<td><input type="text" readonly value="${board.boardWriter }"></td></tr>
	<tr><td width="200" align="center" valign="middle">첨부파일</td>
		<td><input type="file" name="upfile" value="${board.originalFileName }"></td></tr>
	<tr><td width="200" align="center" valign="middle">글 내 용</td>
		<td><textarea rows="7" cols="50" name="bcontent">${board.boardContent }</textarea></td></tr>
</c:if>
<c:if test="${board.boardLev gt 0 }">
	<form action="/first/bmodify" method="post">
	<input type="hidden" name="bnum" value="${board.boardNum }">
	<table align="center" width="700" border="1" cellspacing="0" cellpadding="10">
	<tr><th>답글 제목</th><td><input type="text" name="btitle" value="${board.boardTitle }"></td></tr>
	<tr><th>답글작성자</th><td><input type="text" readonly value="${board.boardWriter }"></td></tr>
	<tr><th>답글 내용</th><td><textarea rows="7" cols="50" name="bcontent">${board.boardContent }</textarea></td></tr>
</c:if>
<tr><td colspan="2" align="center">
	<input type="submit" value="수정하기"> &nbsp;
	<button onclick="javascript:history.go(-1);">취소</button>
</td></tr>
</table>
</form>
</body>
</html>