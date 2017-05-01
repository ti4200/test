<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome first!</title>

</head>
<body>
<%@ include file="menu.jsp" %>
<%-- <jsp:include page="menu.jsp"> --%>
<hr style="clear: left;">
<div style="width:80%; float:left; height: 300px; background:cyan;">영역1</div>
<div style="width:20%; float:left;">
	<%@ include file="view/member/loginForm.jsp" %>
	<%-- <jsp:include page="view/member/loginForm.jsp"> --%>
</div>
</body>
</html>