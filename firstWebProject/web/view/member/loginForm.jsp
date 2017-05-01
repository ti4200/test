<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<%-- <% if(loginUser == null){ %> --%>
<c:if test="${empty loginUser }">
	<h2 align="center">로그인하세요.</h2>
	<form action="/first/login" method="post">
		<table width="300" height="120" align="center" bgcolor="yellow">
			<tr><td>아이디</td>
			<td><input type="text" name="userid"></td></tr>
			<tr><td>암 호</td>
			<td><input type="password" name="userpwd"></td></tr>
			<tr><td colspan="2" align="center">
				<input type="submit" value="로그인"> &nbsp; 
				<a href="/first/view/member/memberEnroll.html">회원가입</a>
			</td></tr>
		</table>
	</form>
<%-- <% }else{ %> --%>
</c:if>
<c:if test="${!empty loginUser }">
	<h2 align="center"><%-- <%= loginUser.getUserName() %> --%>
	${loginUser.userName } 님</h2>
	<table width="300" height="120" align="center" bgcolor="orange">
			<tr><td>메일</td><td>쪽지</td></tr>
			<tr><td>암호변경</td><td>내메모</td></tr>			
		</table>
<%-- <% } %> --%>
</c:if>
	
</body>
</html>