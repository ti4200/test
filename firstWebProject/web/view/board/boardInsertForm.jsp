<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInsertForm</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<hr>
<h1 align="center">게시글 쓰기 페이지</h1>
<form action="/first/binsert" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bwriter" value="${sessionScope.loginUser.userId }">
<table border="1" align="center" width="700" cellspacing="0" cellpadding="10">
<tr><td width="200" align="center" valign="middle">글 제 목</td>
	<td><input type="text" name="btitle"></td></tr>
<tr><td width="200" align="center" valign="middle">작 성 자</td>
	<td><input type="text" readonly value="${sessionScope.loginUser.userId }"></td></tr>
<tr><td width="200" align="center" valign="middle">첨부파일</td>
	<td><input type="file" name="upfile"></td></tr>
<tr><td width="200" align="center" valign="middle">글 내 용</td>
	<td><textarea rows="7" cols="50" name="bcontent"></textarea></td></tr>
<tr><td width="200" align="center" valign="middle" colspan="2">
	<input type="submit" value="등록하기"> &nbsp; 
	<a href="/first/blist?page=1">목록으로 이동</a></td></tr>
</table>
</form>
</body>
</html>