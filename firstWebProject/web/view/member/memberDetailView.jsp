<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <%@ page import="member.model.vo.Member" %>
<%
	Member m = (Member)request.getAttribute("member");
%>  --%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 보기</title>
</head>
<body>
<%@ include file="../../menu.jsp" %>
<br><br>
	<h1 align="center">${member.userName } 님의 정보는 다음과 같습니다.</h1>
	<form action="/first/mupdate" method="post">
	<table align="center" width="600" cellspacing="0" border="1" cellpadding="5">
	
	<input type="hidden" name="username" value="${member.userName }">
	<input type="hidden" name="userid" value="${member.userId }">
	
		<tr><td bgcolor="#8edbff" width="200">이 름</td>
		   <td><input type="text" name="username" value="${member.userName }" readonly></td></tr>
		<tr><td bgcolor="#8edbff">아이디</td>
		   <td><input type="text" name="userid" id="userid" value="${member.userId }" readonly> &nbsp; 
		       </td></tr>
		<tr><td bgcolor="#8edbff">암 호</td>
		   <td><input type="password" name="userpwd" id="userpwd1" value="${member.userPwd }"></td></tr>
		<tr><td bgcolor="#8edbff">암호 확인</td>
		   <td><input type="password" id="userpwd2"></td></tr>
		<tr><td bgcolor="#8edbff">이메일</td>
		    <td><input type="email" name="email" value="${member.email }"></td></tr>
		<tr><td bgcolor="#8edbff">성 별</td>
		   <td>
		   <c:if test="${member.gender == 'M' }">
		   	   <input type="radio" name="gender" value="M" checked> 남 &nbsp;
		       <input type="radio" name="gender" value="F"> 여 
		   </c:if>
		   <c:if test="${member.gender == 'F' }">
		   	   <input type="radio" name="gender" value="M" > 남 &nbsp;
		       <input type="radio" name="gender" value="F" checked> 여 
		   </c:if> 
		    </td></tr>
		<tr><td bgcolor="#8edbff">생 일</td>
			<td><input type="date" name="birth" value="${member.birth }"></td></tr>
		<tr><td bgcolor="#8edbff">전화번호</td>
		   <td><input type="tel" name="phone" value="${member.phone }"></td></tr>
		<tr><td bgcolor="#8edbff">주 소</td>
		   <td>
		   우편번호 : <input type="text" size="5" maxlength="3" name="zcode1" value="">
		   - <input type="text" size="5" maxlength="3" name="zcode2" value=""> &nbsp; 
		   <button>우편번호검색</button><br>
		   <input type="text" name="address" size="40" value="${member.address }">
		   </td></tr>
		<tr><td bgcolor="#8edbff">취 미</td>
		<td>
		<c:forTokens items="${member.hobby }" delims="," var="checkValue">
			<c:if test="${checkValue == '등산' }">
				<c:set var="check1" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '등산' }">
				<c:set var="check2" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '운동' }">
				<c:set var="check3" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '댄스스포츠' }">
				<c:set var="check4" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '여행' }">
				<c:set var="check5" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '독서' }">
				<c:set var="check6" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '영화감상' }">
				<c:set var="check7" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '요리' }">
				<c:set var="check8" value="checked"/>
			</c:if>
			<c:if test="${checkValue == '기타' }">
				<c:set var="check9" value="checked"/>
			</c:if>
		</c:forTokens>
			<table>
				<tr><td><input type="checkbox" name="hobby" value="등산" ${check1 }> 등산</td>
				<td><input type="checkbox" name="hobby" value="운동" ${check2 }> 운동</td>
				<td><input type="checkbox" name="hobby" value="댄스스포츠" ${check3 }> 댄스스포츠</td></tr>
				<tr><td><input type="checkbox" name="hobby" value="여행" ${check4 }> 여행</td>
				<td><input type="checkbox" name="hobby" value="독서" ${check5 }> 독서</td>
				<td><input type="checkbox" name="hobby" value="영화감상" ${check6 }> 영화감상</td></tr>
				<tr><td><input type="checkbox" name="hobby" value="음악듣기" ${check7 }> 음악듣기</td>
				<td><input type="checkbox" name="hobby" value="요리" ${check8 }> 요리</td>
				<td><input type="checkbox" name="hobby" value="기타" ${check9 }> 기타</td></tr>
			</table>
		</td></tr>
		<tr><td colspan="2" align="center">
			<input type="submit" value="수정하기"> &nbsp; 
			<a href="/first/mdelete?userid=${member.userId }">탈퇴하기</a>
		</td></tr>
	</table>
	</form>
</body>
</html>