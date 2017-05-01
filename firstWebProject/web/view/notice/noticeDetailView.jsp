<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="noticeError.jsp" %>
<%-- <%@ page import="notice.model.vo.Notice" %>    
<%
	Notice n = (Notice)request.getAttribute("n");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 상세보기</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<br><hr><br>
<h1 align="center">공지글 상세 보기 페이지</h1>
<form action="/first/nupdate" method="post">
 <input type="hidden" name="no" value="${requestScope.n.noticeNo }">
	<table width="600" align="center">
	<tr><td>공지글번호</td>
	   <td><input type="text" readonly value="${requestScope.n.noticeNo }"></td></tr>
	<tr><td>공지글제목</td>
	   <td><input type="text" name="title" value="${requestScope.n.noticeTitle }"></td></tr>
	<tr><td>작 성 자</td>
	   <td><input type="text" readonly value="${requestScope.n.noticeWriter }"></td></tr>
	<tr><td>작성날짜</td>
	   <td><input type="text" readonly value="${requestScope.n.noticeDate }"></td></tr>
	<tr><td>조회수</td>
		<td><input type="text" readonly value="${requestScope.n.hit }"></td></tr>
	<tr><td>공지글내용</td>
	   <td><textarea name="content" rows="7" cols="50">${requestScope.n.noticeContent }</textarea></td></tr>
 
 	<tr><td colspan="2" align="center"><input type="submit" value="수정하기">
	삭제하기 &nbsp; &nbsp;
	<a href="/first/nlist">목록으로</a></td></tr>
	</table>
</form>
</body>
</html>