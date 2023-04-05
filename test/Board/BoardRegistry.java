package com.test.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/write")
public class BoardRegistry extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = "jdbc:mariadb://127.0.0.1:3306/webdev";
		String uid = "webmaster";
		String pwd = "12345";
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String query = "insert into tbl_board (writer,title,content)	 values"
						+ "('" + writer + "','" + title + "','" + content +"')";
		System.out.println("추가 됨  "+query);
		
		Connection con = null;
		Statement stmt = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(uri,uid,pwd);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			
			if(con != null) con.close();
			if(stmt != null) stmt.close();
			
			response.sendRedirect("/board/list.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

}
