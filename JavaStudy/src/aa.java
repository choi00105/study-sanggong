package com.test.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.Webservlet;

@WebServlet("/servlet/jdbctest_mariadb")
public class JDBCTest extends HttpServlet {
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String uri = "jdbc:mariadb:127.0.0.1:3306/webdev";
        String userid = "webmaster";
        String userpw = "12345";
        String query = "select userid, username, age from tbl_test order by userid";

        Connection con =null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(uri,userid,userpw);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                out.println("<!DOCTYPE html><head><title>Servlet으로 구현 JDBC 연동 </title></head>");
                out.println("<body>");
                out.println("<h1>Servlet 으로 구현한 JDBC 연동 테스트 </h1>");
                out.println("<table border=1><tr><td>아이디</td><td>이름</td><td>나이</td></tr>");

                while(rs.next()) {
                        out.println("<tr><td>" + rs.getString("userid") +
                        "</td><td>" + rs.getString("username") +
                        "</td><td>" + rs.getString("age") + "</td></tr>");
                }
                out.println("</table></body></html>");

                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
        } catch(Exception e) {e.printStackTrace(); }
    }

        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
                doGet(request, response);
        }
}
