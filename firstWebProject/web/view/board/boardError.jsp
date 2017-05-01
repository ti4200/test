<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board error</title>
</head>
<body>
<h1>게시글 관련 에러 페이지</h1>
<h2>${requestScope.message }</h2>
<h2><a href="/first/index.jsp">시작페이지로 이동</a></h2>
</body>
</html>