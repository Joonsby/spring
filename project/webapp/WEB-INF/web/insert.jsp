<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String id = (String) request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insertDB" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="<%=id %>">
		제목을 입력해주세요 : <input type="text" name="title"> <br> <br>		
		내용을 입력해주세요 : <input type="text" name="content"> <br> <br>
		파일을 등록해주세요 : <input type="file" name="file"> <br> <br>
		<input type="submit" value="등록하기">
	</form>
</body>
</html>