<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 쓰기</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<br><hr><br>
<h2 align="center">공지글 등록 페이지</h2>
<form action="/first/ninsert" method="post">
<%-- <input type="hidden" name="writer" value="<%= loginUser.getUserId() %>"> --%>
<input type="hidden" name="writer" value="${sessionScope.loginUser.userId }"> 	
	<table align="center" width="600">
	<tr><td>공지글 제목</td>
		<td><input type="text" name="title"></td></tr>
	<tr><td>작성자 아이디</td>
		<td><%-- <input type="text" value="<%= loginUser.getUserId() %>" readonly> --%>
			<input type="text" value="${sessionScope.loginUser.userId }" readonly>
		</td></tr>
	<tr><td>공지글 내용</td>
		<td><textarea name="content" rows="7" cols="50"></textarea></td></tr>
	<tr><td colspan="2" align="center">
		<input type="submit" value="등록하기"> &nbsp; &nbsp; 
		<a href="/first/nlist">목록으로</a>
	</td></tr>
	</table>
</form>
</body>
</html>






