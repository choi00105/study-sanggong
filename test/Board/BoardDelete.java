package com.test.board;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seqno = request.getParameter("seqno");
		
		request.setCharacterEncoding("utf-8");
		String uri = "jdbc:mariadb://127.0.0.1:3306/webdev";
		String uid = "webmaster";
		String pwd = "12345";
		System.out.println("리퀘"+seqno);
		String query = "delete from tbl_board where seqno =" + seqno;
		System.out.println("삭제 됨  "+query);
		
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
	}

}
