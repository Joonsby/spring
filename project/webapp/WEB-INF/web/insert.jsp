<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String id = (String) request.getParameter("id");
	String password = (String) request.getParameter("password");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertDB" method="post">
		<input type="hidden" name="id" value="<%=id %>">
		<input type="hidden" name="password" value="<%=password %>">
		제목을 입력해주세요 : <input type="text" name="title"> <br> <br>		
		내용을 입력해주세요 : <input type="text" name="content"> <br> <br>
		<input type="submit" value="등록하기">
	</form>
</body>
</html>