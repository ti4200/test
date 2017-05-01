<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%
	String code = (String)request.getAttribute("code");
	String message = (String)request.getAttribute("message");
%> --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 에러</title>
</head>
<body>
<h1>${requestScope.message }</h1>
<c:if test="${not empty requestScope.code }">
	<c:choose>
		<c:when test="${requestScope.code eq 'nlistFail' }">
			<h2><a href="/first/index.jsp">시작페이지로 이동</a></h2><br>
		</c:when>
		<c:otherwise>		
			<h2><a href="/first/nlist">공지사항 목록보기로 이동</a></h2><br>
		</c:otherwise>
	</c:choose>
</c:if>
<c:if test="${not empty pageScope.exception }">
	  <%= exception.getClass().getName() %><br>
	  <%= exception.getMessage() %><br>
</c:if>
</body>
</html>