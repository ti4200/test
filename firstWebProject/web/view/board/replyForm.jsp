<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%
	int num = Integer.parseInt(request.getParameter("num"));
	int level = Integer.parseInt(request.getParameter("level"));
	int seq = Integer.parseInt(request.getParameter("seq"));
%> --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>replyForm</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<hr>
<c:if test="${param.level eq 0 }">
	<h1 align="center">${param.num } 번 게시글에 답글달기 페이지</h1>
</c:if>
<c:if test="${param.level eq 1 }">
	<h1 align="center">${param.ref } 번 게시글의 ${param.num } 번 답글의 답글달기 페이지</h1>
</c:if>
<form action="/first/breply" method="post">
<input type="hidden" name="bwriter" value="${loginUser.userId }">
<input type="hidden" name="level" value="${param.level }">
<input type="hidden" name="seq" value="${param.seq }">
<c:if test="${param.level eq 0 }">
	<input type="hidden" name="ref" value="${param.num }">
</c:if>
<c:if test="${param.level eq 1 }">
	<input type="hidden" name="ref" value="${param.ref }">
</c:if>
<table width="700" align="center" border="1" cellspacing="0" cellpadding="10">
<tr><th>답글 제목</th><td><input type="text" name="btitle"></td></tr>
<tr><th>답글작성자</th><td><input type="text" readonly value="${loginUser.userId }"></td></tr>
<tr><th>답글 내용</th><td><textarea rows="7" cols="50" name="bcontent"></textarea></td></tr>
<tr><th colspan="2" align="center">
	<input type="submit" value="답글등록"> &nbsp;
	<button onclick="javascript:history.go(-1);">취소</button>	
</th></tr>
</table>
</form>
</body>
</html>





