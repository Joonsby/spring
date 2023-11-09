<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pro.spring.vo.GuestVO" %>
<%	
	List<GuestVO> boardList = (List<GuestVO>) request.getAttribute("boardList");
	String userId = (String) request.getAttribute("id");
	String postId = boardList.get(0).getId();	
	boolean isTrue = false;
	if(userId != null){
		userId = userId.trim();
		postId = postId.trim();
		if(userId.equals(postId)){
			isTrue = true;			
		}		
	}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.btn{
		display:inline-block;
		width:100px;
		height:30px;
		color:#000;
		text-decoration:none;
		background:#ccc;
		border:1px solid #999;
		text-align:center;
		line-height:30px;
		border-radius:5px;
	}
	
</style>
<body>
	<p>게시글 번호 : <%= boardList.get(0).getPost_number() %></p>
	<p>아이디 : <%= postId %></p>
	<p>제목 : <%= boardList.get(0).getTitle() %></p>
	<p>내용 : <%= boardList.get(0).getContent() %></p>
	<p>파일 : <a href="download?file=<%=boardList.get(0).getFile() %>"><%=boardList.get(0).getFile() %></a></p>
	<p>작성일 : <%=boardList.get(0).getPost_date() %></p>
  	<%
		if(isTrue){
	%>
		<a class="btn" href="insert_password?post_number=<%=boardList.get(0).getPost_number() %>">삭제하기</a> <a class="btn" href="print">뒤로 가기</a>
	<%
		} else{
	%>		
		<a class="btn" href="print?userId=<%=userId %>">뒤로 가기</a>
	<%
		}
	%>	  
	
</body>
</html>