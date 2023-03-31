<%@ language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>

<%
String url = "jdbc:mariadb://127.0.0.1:3306/webdev";
String userid = "webmaster";
String userpw = "12345";
String query = "select userid, username,age, from tbl_test order by userid";

Connection con;
Statement stmt;
ResultSet rs;
%>


<html>
        <head>
                <title>JSP Maria DB 연동 테스트 </title>
        </head>

        <body>
                <h1>JSP Maria DB 연동 테스트~</h1>
                <table border=1>
                 <tr>
                  <td>아이디</tb>
                  <td>이름</tb>
                  <td>나이</tb>
                 </tr>

                <%
                try {
                        Class.forName("org.mariadb.jdbc.Driver");
                        con = DriverManager.getConnection(url, userid, userpw);
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(query);

                        while(rs.next()) {
                %>
                 <tr>
                  <td><%=rs.getString("userid")%></td>
                  <td><%=rs.getString("username")%></td>
                  <td><%=rs.getString("age")%></td>
                 </tr>
                <%
                        }
                } chatch(Exception e){
                        e.printStackTrace();
                } finally {
                //      if(rs!=null) rs.close();
                //      if(stmt!=null) stmt.close();
        //              if(con!=null) con.clise();
                }
                %>
                </tabel>
        </body>
</html>
