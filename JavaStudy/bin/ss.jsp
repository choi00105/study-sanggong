<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>

<%
    String uri ="jdbc:mariadb://localhost:3306/webdev"; 
    String userid = "webmaster"; 
    String userpw = "12345";
	String query = "select userid, username, age from tbl_test order by username";
    
    Connection con;
	Statement stmt; 
	ResultSet rs;
    
%>

<html>
<head>
<title></title>
<style>
        .InfoTable {
            border-collapse: collapse;
            border: 3px solid #168;
            width: 800px;
            margin: auto;
	    text-align: center;
        }
        .InfoTable th {
            color: #168;
            background: #f0f6f9;
        }
        .InfoTable th, .InfoTable td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        .InfoTable th:first-child, .InfoTable td:first-child {
            border-left: 0;
        }
        .InfoTable th:last-child, .InfoTable td:last-child {
            border-right: 0;
        }
        .InfoTable tr td:first-child{
            text-align: center;
        }

    .bottom_menu {
        position:relative; 
        top: 30px;
        left: 800px;
    }

    .bottom_menu > a:link, .bottom_menu > a:visited {
        background-color: #FFA500;
        color: maroon;
        padding: 15px 25px;
        text-align: center;
        display: inline-block;
        text-decoration : none;
    }
    .bottom_menu > a:hover, .bottom_menu > a:active {
        background-color: #1E90FF;
        text-decoration : none;
    }
</style>

<script>

    const memberRegistry = () => {
        document.location.href='/jdbctest_registry.jsp';
    }    

</script>

</head>
<body>
<h1 style="text-align:center">JSP Maria DB 연동 테스트</h1>

<div>
<table class="InfoTable">
    <tr><th>아이디</th><th>이름</th><th>나이</th></tr>

    <% 
    
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(uri,userid,userpw);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        
            while(rs.next()) {

    %>

        <tr>
            <td><%=rs.getString("userid") %></td>
            <td><%=rs.getString("username") %></td>
            <td><%=rs.getInt("age") %></td>
        </tr>

    <%   
        }
    }catch(Exception e){ e.printStackTrace();  }  

    %>
 
    </table>
    <div class="bottom_menu">
        <a href="javascript:memberRegistry()">등록</a>
    </div>

</div>

</body>
</html>    
		