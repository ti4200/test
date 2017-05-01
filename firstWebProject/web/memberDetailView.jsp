<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%-- <% Member member = (Member)request.getAttribute("member"); %> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 보기</title>
</head>
<body>
	<h2><%-- <%=member.getUserName()%> --%>${member.userName } 정보 보기</h2>
	<form action="mupdate" method="post">
		<input type="hidden" name="huserid" value="<%-- <%= member.getUserId() %> --%>${member.userId}">
		<input type="hidden" name="huserpwd" value="<%-- <%= member.getUserPwd() %> --%>${member.userPwd}">
		<input type="hidden" name="husername" value="<%-- <%= member.getUserName() %> --%>${member.userName}">
		
		<label>I D : <input type="text" name="userid" readonly value="<%-- <%=member.getUserId()%> --%>${member.userId}"></label><br>
		<label>P W : <input type="password" name="userpwd1" id="userPwd"></label><br />
		<label>P W 확인 : <input type="password" id="userPwdChk"></label> &nbsp;
		<input type="button" id="pwdCheck" value="확인"><br />
			<label id="pwdCheckResult"></label>
		<label>이 름 : <input type="text" name="username" readonly value="<%-- <%=member.getUserName()%> --%>${member.userName}"></label><br />
		<label>생 일 : <input type="date" id="birth" name="birthday" value="<%-- <%=member.getBirth()%> --%>${member.birth}"></label><br />
		<fieldset style="width:180px;">
			<label>성별</label>
			<%-- <% if(member.getGender() == "M"){ %> --%>
			<c:if test="${member.gender eq 'M' }">
			<input type = "radio" name = "gender" checked value="M"/>남자
			<input type = "radio" name = "gender" value="F"/>여자
			<%-- <% } else { %> --%>
			</c:if><c:if test="${member.gender ne 'M' }">
			<input type = "radio" name = "gender" value="M"/>남자
			<input type = "radio" name = "gender" checked value="F"/>여자
			<%-- <% } %> --%>
			</c:if>
		</fieldset>
		<label>Email : <input type="email" name="email" value="<%-- <%=member.getEmail()%> --%>${member.email}"></label><br />
		<label>Phone : <input type="tel" name="phone" value="<%-- <%=member.getPhone()%> --%>${member.phone}"></label><br />
		<%-- <%
			String zcode1 = member.getAddress().substring(0,3);
			String zcode2 = member.getAddress().substring(4,7);
			String zcode3 = member.getAddress().substring(8);
		%> --%>
		<c:set var="zcode1" value="(${member.address }).substring(0,3)"/>
		<c:set var="zcode2" value="(${member.address}).substring(4,7)"/>
		<c:set var="zcode3" value="(${member.address}).substring(8)"/>
		
		<label>우편번호 : <input type="text" maxlength="3" id="post_no1" name="zcode1" style="width:40px;" value="<%-- <%=zcode1%> --%>${zcode1}"> - 
				 <input type="text" maxlength="3" id="post_no2" name="zcode2" style="width:40px;" value="<%-- <%=zcode2%> --%>${zcode2}"></label>
				 <input type="button" id="postSearch" value="우편번호 검색"><br />
		<label>상세주소 : <input type="text" name="address" value="<%-- <%=member.getAddress()%> --%>${member.address}"></label><br />
		<label>취 미 :</label>
		<%-- <%
			String[] checked = new String[6];
			String[] hobbys = member.getHobbys();
			for(int i=0; i<hobbys.length; i++){
				if(hobbys[i].equals("등산")){	
					checked[0] = "checked";
				}
				if(hobbys[i].equals("여행")){ 
					checked[1] = "checked";
				}
				if(hobbys[i].equals("게임")){ 
					checked[2] = "checked";
				}
				if(hobbys[i].equals("운동")){ 
					checked[3] = "checked";
				}
				if(hobbys[i].equals("영화")){ 
					checked[4] = "checked";
				}
				if(hobbys[i].equals("음악")){ 
					checked[5] = "checked";
				}
			}%> --%>
			<%-- <c:set var="checked" value="${member.hobby }"/>
			<c:set var="checked0" value="unchecked"/>
			<!-- <c:set var="checked1" value="unchecked"/> -->
			<c:set var="checked2" value="unchecked"/>
			<c:set var="checked3" value="unchecked"/>
			<c:set var="checked4" value="unchecked"/>
			<c:set var="checked5" value="unchecked"/> --%>
			
			<c:if test="${member.hobby eq  '등산'}">
			<c:set var="checked0" value="checked"/>
			</c:if>
			<c:if test="${member.hobby eq  '여행'}">
			<c:set var="checked1" value="checked"/>
			</c:if>
		 	<c:if test="${member.hobby eq  '게임'}">
			<c:set var="checked2" value="checked"/>
			</c:if>
			<c:if test="${member.hobby eq  '운동'}">
			<c:set var="checked3" value="checked"/>
			</c:if>
			<c:if test="${member.hobby eq  '영화'}">
			<c:set var="checked4" value="checked"/>
			</c:if>
			<c:if test="${member.hobby eq  '음악'}">
			<c:set var="checked5" value="checked"/>
			</c:if>
			
			<input type="checkbox" name="hobbys" value="등산" <%-- <%=checked[0] %> --%>${checked0 }>등산
			<input type="checkbox" name="hobbys" value="여행" <%-- <%=checked[1] %> --%>${checked1 }>여행
			<input type="checkbox" name="hobbys" value="게임" <%-- <%=checked[2] %> --%>${checked2 }>게임
			<input type="checkbox" name="hobbys" value="운동" <%-- <%=checked[3] %> --%>${checked3 }>운동
			<input type="checkbox" name="hobbys" value="영화" <%-- <%=checked[4] %> --%>${checked4 }>영화
			<input type="checkbox" name="hobbys" value="음악" <%-- <%=checked[5] %> --%>${checked5 }>음악
		<br />
		<input type="submit" value="수정하기"> &nbsp; &nbsp; 
		<a href="mdelete?userid=<%-- <%=member.getUserId()%> --%>${member.userId}">탈퇴하기</a>
	</form>
</body>
</html>