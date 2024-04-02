package kr.or.ddit.util;
//Data Access Object 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//드라이버 로딩 -> 로딩된 드라이버로 Connection객체 생성 -> 생성된 Connection객체로 Statement 또는 PreparedStatement 객체 생성 -> 
//-> Statement객체로 execute()를 호출하여 sql문을 실행 -> 실행결과를 ResultSet에 저장
public class JDBCUtil {
	
	//클래스 초기화블럭 : 프로그램 실행중 이 클래스가 로딩될때 딱 한번 실행되어 딱 한번만 로딩을 체크하게 하려고
	static {
		try {
			//Oracle JDBC 드라이버 클래스(oracle.jdbc.driver.OracleDriver)를 로드함
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}

	
	/*
	 	Connection 객체 생성 메서드
	 	Connection 객체, 예외발생시 null
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC04","java");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//자원반납을 위한 메서드
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if (rs != null) try {rs.close();} catch (SQLException e2) {}
		if (stmt != null) try {stmt.close();} catch (SQLException e2) {}
		if (pstmt != null) try {pstmt.close();} catch (SQLException e2) {}
		if (conn != null) try {conn.close();} catch (SQLException e2) {}
	}
}
