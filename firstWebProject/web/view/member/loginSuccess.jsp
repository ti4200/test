<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"  errorPage="error.jsp" %>
<%
	//전달된 request 객체와 response 객체를 사용할 수 있음
	//sessoin 객체도 사용할 수 있음
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<body>
	<h2><%= loginUser.getUserName() %> 님, 로그인 성공하였습니다.</h2>
	<a href="/first/logout">로그아웃</a>
</body>
</html>






