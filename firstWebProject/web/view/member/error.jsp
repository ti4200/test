<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member error</title>
</head>
<body>
<%
	String code = (String)request.getAttribute("code");
	String message = (String)request.getAttribute("message");
if(code != null){	
	switch(code){
	case "loginFail": 	
%>
	<h2><%= message %> : 아이디나 암호가 잘못 되었습니다.</h2>
	<a href="/first/index.jsp">로그인 페이지로 이동</a><p>
<% 
		break;
	case "enrollFail":
%> 
	<h2><%= message %></h2>
	<a href="/first/view/member/memberEnroll.html">회원가입 페이지로 이동</a><p>
<%		break;
	case "detailFail":
	case "updateFail":
	case "deleteFail":
%> 
	<h2><%= message %></h2>
	<a href="/first/index.jsp">시작 페이지로 이동</a><p>
<%		break;
}}else{ %>
	<h2><%= exception.getMessage() %></h2>
	<h2><%= exception.getClass().getName() %></h2>
<% } %>				
</body>
</html>





