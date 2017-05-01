<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="member.model.vo.Member, java.util.*"     %>
<%-- <%
	Member loginMember = (Member)session.getAttribute("loginMember");
%>     --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 성공</title>
</head>
<body>
<h1>로그인 성공!  <%-- <%= loginMember.getUserName() %> --%>${loginMember.userName }님 환영합니다.</h1>
<a href="logout">로그아웃</a><br>
<a href="mselect?userid=<%-- <%= loginMember.getUserId() %> --%>${loginMember.userId}">내 정보 보기</a>
<hr>
<%-- <%  if(${loginMember.userId}.equals("admin")){ %> --%>
<c: if test="${loginMember.userId eq 'admin'}">
<a href="mall">전체 회원 정보 보기</a>
<%-- <%  } %> --%>
</c:>
<hr>
<h1>JSP : Java Server Page</h1>
HTML 태그 사이에 JAVA 소스 코드를 포함할 수 있는 파일을 말함.<br>
자바 소스 코드는 태그와 구분하기 위해 JSP 태그들을 사용한다.<br>

</body>
</html>