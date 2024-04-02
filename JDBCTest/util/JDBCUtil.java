package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtil {
	
	//클래스 초기화블럭 : 프로그램 실행중 이 클래스가 로딩될때 딱 한번 실행되어 딱 한번만 로딩을 체크하게 하려고
	static {
		try {
			
			//forName() : java.lang.Class 클래스의 정적 메서드이고, 인자에서 문자열로 표현된 클래스의 이름을 가지고 해당 클래스를 동적으로 로드함. 
			//				클래스의 메타데이터에 접근하고 클래스를 동적으로 생성하는 데 유용하게 사용됨
			
			//Oracle JDBC 드라이버 클래스(oracle.jdbc.driver.OracleDriver)를 로드만하고, 아래에서 DriverManager를 통해 연결함
			//해당 JDBC 드라이버 클래스 자체는 주로 클래스를 로드할 때 필요한 메타데이터 정보와 드라이버 등록을 수행하는 역할을 함 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}

	
	/*
	 	Connection객체 생성 메서드 = getConnection()
	 	Connection 객체, 예외발생시 null
	 */
	public static Connection getConnection() {
		try {
			//드라이버 매니저를 사용하여 데이터베이스와의 연결을 설정하고 관리함
			//생성자 직접 호출을 통한 객체생성이 아닌, "객체생성메서드패턴"으로 Connection객체를 생성하고 있다.
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC04","java");	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//T01MemberInfoTest에서 사용한 자원을 반납하기 위한 메서드( T01MemberInfoTest에서 작업때 사용한 변수들을 매개변수로 받는다.)
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		//자원 반납 부분의 코드는 null이 아닐 때만 반납하고, 자원이 null인 경우 아무 동작도 수행하지 않는다. 
		//이렇게 구현된 이유는 null인 자원에 대해서 close를 호출하면 NullPointerException 예외가 발생할 수 있기 때문. 
		//따라서, 코드에서 null인 자원에 대해서 close를 호출하지 않고 그냥 넘어가도록 구현하는 것이 안전한 방법
		if (rs != null) try {rs.close();} catch (SQLException e2) {}		//데이터베이스 결과셋을 위한 객체
		if (stmt != null) try {stmt.close();} catch (SQLException e2) {}	//일반 SQL 문 실행을 위한 객체
		if (pstmt != null) try {pstmt.close();} catch (SQLException e2) {}	//준비된 SQL 문을 실행하기 위한 객체
		if (conn != null) try {conn.close();} catch (SQLException e2) {}	//데이터베이스 연결을 위한 객체
	}
}
