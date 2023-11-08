<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.pro.spring.repository.GuestDAO"%>
<%@ page import="com.pro.spring.vo.GuestVO"%>
<%@ page import="java.util.List"%>
<%
	List<GuestVO> list = (List<GuestVO>) request.getAttribute("list");
	String id = (String) session.getAttribute("id");
	/* String id = (String) request.getParameter("userId"); */
	String password = (String) request.getAttribute("password");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
h2{
	margin-bottom:30px;
}

table {
	border-collapse: collapse;
	text-align: center;
}

th, td {
	border: 1px solid #000;
	padding: 5px;
}

th {
	background: #1aa3ff;
	width: 100px;
}

#delete_btn {
	display: block;
	width: 80%;
	height: 25px;
	margin: 0 auto;
	color: #000;
	text-decoration: none;
	border-radius: 5px;
	background: #ccc;
	line-height: 25px;
	border: 0;
	cursor: pointer;
}

#delete_btn:hover {
	background: #000;
	color: #fff;
}

#post,
#logout{
	display:inline-block;
	width:150px;
	height:30px;
	border:1px solid #000;
	background:#ccc;
	color:#000;
	text-align:center;
	line-height:30px;
	text-decoration:none;
	font-weight:bold;
	margin-top:10px;
	border-radius:5px;
}

#post:hover,
#logout:hover{
	background:#000;
	color:#fff;
}
</style>
<body>
	<h2>
 	<%
	if(id != null){		
		out.println(id + " 님 환영합니다!");
	} else if(id != null){
		out.println(id + " 님 환영합니다!");
	}
	else{
		out.println("로그인이 되어있지 않습니다.");
	}
	 %>
	 </h2>
	<input type="hidden" name="post_number"
		value="<%=list.get(0).getPost_number()%>">
	<table>
		<thead>
			<tr>
				<th>게시글 번호</th>
				<th>아이디</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>				
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < list.size(); i++) {
					out.println("<tr>");
					out.println("<td>" + list.get(i).getPost_number() + "</td>");
					out.println("<td>" + list.get(i).getId() + "</td>");
					out.println("<td>" + list.get(i).getTitle() + "</td>");
					if(id != null){
						out.println("<td><a href='board?post_number=" + list.get(i).getPost_number() + "&&id= " + id + "'>" + list.get(i).getContent() + "</a></td>");						
					} else{						
						out.println("<td><a href='board?post_number=" + list.get(i).getPost_number() + "'>" + list.get(i).getContent() + "</a></td>");						
					}					
					out.println("<td>" + list.get(i).getPost_date() + "</td>");					
				}				
			%>
		</tbody>
	</table>	
	
	<%
		if(id != null){
			out.println("<a id='post' href='insert?id=" + id + "'>게시판 등록하기</a>");			
			out.println("<a id='logout' href='logout'>로그 아웃하기</a>");			
		} else{
			out.println("<a id='post' href='login'>로그인 하기</a>");
		}
	%>
</body>
</html>