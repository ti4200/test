<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardListView</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<h2 align="center">답변형 게시글 목록 보기 페이지</h2>
<h3 align="center">게시글 총 갯수 : ${totalCount }</h3>
<div align="center">
<c:if test="${not empty sessionScope.loginUser }">
	<button onclick="javascript:location.href='/first/view/board/boardInsertForm.jsp';">게시글 추가</button>
</c:if>
</div>
<br>
<table align="center" width="700" border="1" cellspacing="0">
	<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th></tr>
	<c:forEach var="b" items="${boardList }">
		<tr>
			<td>${b.boardNum }</td>
			<td>
				<c:if test="${b.boardLev == 1 }">
					&nbsp;&nbsp;> 
				</c:if>
				<c:if test="${b.boardLev == 2 }">
					&nbsp;&nbsp;&nbsp;&nbsp;>>
				</c:if>
				<c:if test="${not empty loginUser }">
					<a href="/first/bdetail?num=${b.boardNum }&page=${currentPage }">${b.boardTitle }</a>
				</c:if>
				<c:if test="${empty loginUser }">
					${b.boardTitle }
				</c:if>
			</td>
			<td>${b.boardWriter }</td>
			<td>${b.boardDate }</td>
			<td>${b.boardReadCount }</td>
		</tr>
	</c:forEach>
</table>
<hr width="50%" size="1" color="gray">
<div id="pageSection" align="center">
<a href="/first/blist?page=1">[처음] </a> 
<c:if test="${startPage gt 1 }">
	<a href="/first/blist?page=${startPage - 1 }">[이전] </a> 
</c:if>
<c:if test="${startPage eq 1 }">[이전] </c:if>
<c:forEach var="num" begin="${startPage }" end="${endPage }" step="1">
	<c:if test="${num eq currentPage }">
		<font color="green"><b><a href="/first/blist?page=${num }">${num }</a></b></font>&nbsp;
	</c:if>
	<c:if test="${num ne currentPage }">
		<a href="/first/blist?page=${num }">${num }</a> &nbsp;
	</c:if>
</c:forEach>
<c:if test="${endPage lt maxPage }">
	<a href="/first/blist?page=${endPage + 1 }"> [다음] </a>
</c:if>
<c:if test="${endPage eq maxPage }">
	[다음]
</c:if>
<a href="/first/blist?page=${maxPage }"> [마지막]</a>
</div>
</body>
</html>







