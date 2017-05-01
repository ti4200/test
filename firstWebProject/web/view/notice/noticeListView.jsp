<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="noticeError.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%@ page import="java.util.*, notice.model.vo.Notice" %>  
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>  --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 목록보기</title>
<script type="text/javascript" src="/first/js/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#stitleView").hide();
		$("#swriterView").hide();
		$("#sdateView").hide();
		
		$("#stitle").click(function(){			
			if($(this).is(":checked")){
				$("#stitleView").show();
				$("#swriterView").hide();
				$("#sdateView").hide();
			}
		});
		
		$("#swriter").click(function(){
			if($(this).is(":checked")){
				$("#stitleView").hide();
				$("#swriterView").show();
				$("#sdateView").hide();
			}
		});
		
		$("#sdate").click(function(){
			if($(this).is(":checked")){
				$("#stitleView").hide();
				$("#swriterView").hide();
				$("#sdateView").show();
			}
		});
	});
</script>
</head>
<body>
<%-- <%@ include file="/first/menu.jsp" %> --%>
<%@ include file="../../menu.jsp" %>
<br><hr><br>
<div align="center">
	<h2>검색할 항목을 선택하시오.</h2>
	<a href="/first/nlist">전체 목록 보기</a><br>
	<input type="radio" id="stitle" name="select" value="title"> 제목 &nbsp; 
	<input type="radio" id="swriter" name="select" value="writer"> 작성자 &nbsp; 
	<input type="radio" id="sdate" name="select" value="date"> 작성날짜
</div>
<div id="stitleView" align="center" style="background:orange;">
	<h2>검색할 제목을 입력하시오.</h2>
	<form action="/first/nsearch" method="post">
	<input type="hidden" name="item" value="title">
	<input type="search" name="keyword"> &nbsp;
	<input type="submit" value="검색">
	</form><br>
</div>
<div id="swriterView" align="center" style="background:orange;">
	<h2>검색할 작성자 아이디를 입력하시오.</h2>
	<form action="/first/nsearch" method="post">
	<input type="hidden" name="item" value="writer">
	<input type="search" name="keyword"> &nbsp;
	<input type="submit" value="검색">
	</form><br>
</div>
<div id="sdateView" align="center" style="background:orange;">
	<h2>검색할 날짜를 입력하시오.</h2>
	<form action="/first/nsearch" method="post">
	<input type="hidden" name="item" value="date">
	<input type="date" name="start"> ~ <input type="date" name="end">
	<input type="submit" value="검색">
	</form><br>
</div>
<%-- <% if(loginUser != null){ %> --%>
<c:if test="${!empty loginUser }">
<div align="center"><button onclick="javascript:location.href='/first/view/notice/noticeInsert.jsp';">공지글 쓰기</button></div>
<%-- <% } %> --%>
</c:if>
<br>
<table align="center" width="700" border="1" cellspacing="0">
	<tr><th>번호</th><th width="300">공지제목</th><th>작성자</th><th>작성날짜</th><th>조회수</th></tr>
	<%-- <% for(Notice n : list){ %> --%>
	<c:forEach items="${list }" var="n">
	<tr>
		<td><%-- <%= n.getNoticeNo() %> --%> ${n.noticeNo }</td>
		<c:url var="detail" value="ndetail"> <%-- 상대경로만 사용가능 --%>
			<c:param name="no" value="${n.noticeNo }" />
		</c:url>
		<td><a href="${detail }">
		<%-- <%= n.getNoticeTitle() %> --%> ${n.noticeTitle }</a></td>
		<td><%-- <%= n.getNoticeWriter() %> --%> ${n.noticeWriter }</td>
		<td><%-- <%= n.getNoticeDate() %> --%> ${n.noticeDate }</td>
		<td><%-- <%= n.getHit() %> --%> ${n.hit }</td>
	</tr>
	<%-- <% } %> --%>
	</c:forEach>
</table>
</body>
</html>





