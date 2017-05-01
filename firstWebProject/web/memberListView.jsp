<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.model.vo.Member" %>
  <%
  	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("memberList");
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 회원 정보</title>
<script>
	function check(){
		var keyword = document.getElementById("keyword");		
		var value = keyword.innerHtml;
		if(value == undefined || value.length() == 0){
			alert("검색 단어를 입력하십시오.");			
			return false;
		}else 
			return true;
	}
</script>
</head>
<body>
<center>
<h1>전체 회원 목록</h1>
<form action="msearch" method="post" onsubmit="check()">
<label>검색할 회원 이름 : <input type="text" id="keyword" name="keyword" size="20"></label>
<input type="submit" value="검색">
</form>
<hr>
<p/>
<table width="800" border="1" cellspacing="0" cellpadding="20">
	<tr><th>번호</th><th>아이디</th><th>이름</th><th>이메일</th><th>전화번호</th></tr>
	<%  
		int num = 1;
		for(Member m : list){ %>
	<tr>
		<td><%= num++ %></td>
		<td><a href="mselect?userid=<%= m.getUserId() %>"><%= m.getUserId() %></a></td>
		<td><%= m.getUserName() %></td>
		<td><%= m.getEmail() %></td>
		<td><%= m.getPhone() %></td>
	</tr>
	<%  } %>
</table>
</center>
</body>
</html>








