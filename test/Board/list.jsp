<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록 보기</title>
<style>
	#banner {
		width: 500px;
		height: auto;
	}
</style>
</head>
<body>
<img id="banner" src="/img/logo.jpg">
<h1>게시물 목록 보기</h1>

<table border=1>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<tbody>
	<%
		//request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); // js에서 제공하는 내장 객체
		
		String uri = "jdbc:mariadb://127.0.0.1:3306/webdev";
		String uid = "webmaster";
		String pwd = "12345";
		String query = "select seqno, title, writer, to_char(regdate,'YYYY-MM-DD HH24:MI:SS') as regdate, hitno from tbl_board order by seqno desc";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(uri,uid,pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				
	%>
	<tr>
		<td><%=rs.getInt("seqno") %></td>
		<td><a href="/board/view.jsp?seqno=<%=rs.getInt("seqno") %>"><%=rs.getString("title") %></a></td>
		<td><%=rs.getString("writer") %></td>
		<td><%=rs.getString("regdate") %></td>
		<td><%=rs.getInt("hitno") %></td>
	</tr>
	<%
		}
	} catch(Exception e) {
		e.printStackTrace();
	}

	%>
	</tbody>
</table>
<br/>
<a href="/board/writer.jsp">글쓰기</a>
</body>
</html>