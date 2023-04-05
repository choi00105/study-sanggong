<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>
<%String seqno = request.getParameter("seqno"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<script>	
	const modifyForm = () =>{	
		let writer = document.querySelector('#writer');	
		let title = document.querySelector('#title');
		let content = document.querySelector('#content');
		
		document.WriteForm.action = '/board/modify';
		document.WriteForm.submit();
	}
	</script>
</head>

<%
response.setCharacterEncoding("utf-8"); // js에서 제공하는 내장 객체

String uri = "jdbc:mariadb://127.0.0.1:3306/webdev";

String uid = "webmaster";
String pwd = "12345";

String query_view = "select seqno, title, writer, to_char(regdate,'YYYY-MM-DD HH24:MI:SS')as regdate, content "
				+ "as regdate,hitno,content from tbl_board where seqno = "+seqno+" order by seqno desc";

System.out.println("게시물 보기 SQL = "+query_view);

Connection con = null;
Statement stmt = null;
ResultSet rs = null;

String writer = "";
String title = "";
String regdate = "";
String content = "";

try{
	Class.forName("org.mariadb.jdbc.Driver");
	con = DriverManager.getConnection(uri,uid,pwd);
	stmt = con.createStatement();
	stmt.executeUpdate(query_view);
	rs = stmt.executeQuery(query_view);
	
	
	if(rs.next()) {
		writer = rs.getString("writer");
		title = rs.getString("title");
		regdate = rs.getString("regdate");
		content = rs.getString("content");		
	}
	//stmt.executeUpdate(query_hitno);
	
}catch(Exception e){
		
}

%>
<body>
	<h1>게시물 수정</h1>
	
	<br>
	<form name="WriteForm" method="post">
		<input type="hidden" id="writer" name="seqno" value="<%=seqno %>"><br>
		<input type="text" id="title" name="writer" value="<%=writer %>"><br><br>
		<input type="text" id="title" name="title" value="<%=title %>"><br><br>
		<textarea cols="20" rows="20" id="content" name = "content"><%=content %></textarea>
		<br/><br/>
		<input type="button" value="수정" onclick="modifyForm()">
		<input type="button" value="취소" onclick="history.back()">
	</form>
</body>
</html>