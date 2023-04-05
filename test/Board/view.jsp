<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
    
<%String seqno = request.getParameter("seqno"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 내용 보기</title>
<script>
	const deleteBorad = () => {
		if(confirm("정말로 삭제 하시겠습니까?")){
			document.location.href='/board/delete?seqno=<%=seqno%>';
		}
	}
</script>
</head>
<body>

<%
response.setCharacterEncoding("utf-8"); // js에서 제공하는 내장 객체

String uri = "jdbc:mariadb://127.0.0.1:3306/webdev";
String uid = "webmaster";
String pwd = "12345";

String query_view = "select seqno, title, writer, to_char(regdate,'YYYY-MM-DD HH24:MI:SS')as regdate, content "
				+ "as regdate,hitno,content from tbl_board where seqno = "+seqno+" order by seqno desc";
String query_hitno = "update tbl_board set hitno ="
			+"(select hitno from tbl_board where seqno =" +seqno+") + 1 where seqno = " +seqno;		

System.out.println("게시물 보기 SQL = "+query_view);
System.out.println("조회수 증가 SQL = "+query_hitno);

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
	stmt.executeUpdate(query_hitno);
	
}catch(Exception e){
		
}

%>

<h1>게시물 내용 보기</h1>

<table border=1>
	<tr><td>작성자 : <%=writer %></td></tr>
	<tr><td>제목 : <%=title %></td></tr>
	<tr><td>작성일 : <%=regdate %></td></tr>
	<tr><td colspan=2>내용 </td></tr>	
	<tr><td colspan=2><pre><%=content %></pre></td></tr>
</table>
<a href="/board/list.jsp">목록가기</a>&nbsp;&nbsp<a href="/board/modify.jsp?seqno=<%=seqno%>">글 수정</a>&nbsp;&nbsp<a href="javascript:deleteBorad()">글 삭제</a>
</body>
</html>