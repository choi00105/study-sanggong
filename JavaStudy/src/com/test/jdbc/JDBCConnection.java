package com.test.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.test.exam03.Member;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        // String url = "jdbc:mariadb://localhost:3306/webdev";
        // String userid = "webmaster";
        // String userpw = "12345";
        String query = "select userid,username,age from tbl_test order by userid";

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/webdev");
        hikariConfig.setUsername("webmaster");
        hikariConfig.setPassword("12345");
    // 현재시간 불르는 쿼리문
        hikariConfig.setConnectionTestQuery("select now() from dual"); 
        hikariConfig.setMaximumPoolSize(20);
    // 3000ms 동안 응답 겂으면 쓰레드 끊음?    
        hikariConfig.setLeakDetectionThreshold(3000);
        hikariConfig.setPoolName("mariaDB-HikariCP");


        Connection con; // Statement 객체 생성 및 JDBC 종료
        Statement stmt; // ResiltSet 객체를 생성
        ResultSet rs; // SQL문 실행 결과를 얻어 오는 객체로 현재 데이터의 행(레코드 위치)의 위치를 나타내는 커서를 사용

        HikariDataSource ds = new HikariDataSource(hikariConfig);

        con = ds.getConnection();

        // Class.forName("org.mariadb.jdbc.Driver"); // JDBC 드라이버를 로딩
        // con = DriverManager.getConnection(url,userid, userpw); // DB Connection 객체 생성
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        

        List<Member> list = new ArrayList<>(); // DTO

        while(rs.next()) {

            list.add(new Member(rs.getString("userid"),
                rs.getString("username"),
                rs.getInt("age")));
        }

    // 닫는건 여는 순서 반대로
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
        if(con != null) con.close();

        for(Member member:list) {
            System.out.println("아이디 = " + member.getUserId() 
                + "\t 이름 : " + member.getUserName()
                + "\t 나이 : " + member.getAge());
        }
    }
}
