<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	<form action="check" method="post">
		<input type="hidden" name="post_number" value="<%=request.getParameter("post_number")%>">
		<input type="hidden" name="id" value="<%=session.getAttribute("id")%>">
		비밀 번호를 입력하세요 : <input type="password" name="password"> <br>
		<input type="submit" value="확인">
	</form>	
</body>
</html>