package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

//JDBCUtil3은 JDBCUtil2와 똑같지만 ResourceBundle클래스를 이용해 properties파일에 국제화 및 지역화 속성을 셋팅한 후, 코드에 적용함
public class JDBCUtil3 {
	
	
	//properties 파일은 주로 텍스트 데이터를 저장하기 위한 것으로 다국어 메시지 문자열, 이미지 파일 경로, 설정 값 등을 포함할 수 있다.
	//ResourceBundle클래스는 별도로 작성된 리소스들의 확장자가 properties인 파일을 읽어와 각 언어 및 지역에 맞게 설정하며 로드하는걸 지원함
	//즉, Properties클래스는 텍스트 기반의 설정 파일을 로드하고 관리하는 데 사용되며, 
	//		ResourceBundle클래스는 Properties클래스의 properties파일에 대한 기능을 포함하여 국제화 및 지역화를 지원하는 클래스이다.
	static ResourceBundle bundle;
	
	
	//클래스 초기화블럭 : 프로그램 실행중 이 클래스가 로딩될때 딱 한번 실행되어 딱 한번만 로딩을 체크하게 하려고
	static {
		
		
		
		
		
		//ResourceBundle는 클래스의 생성자(new)를 직접 호출하여 객체를 생성하는게 아닌, 객체 생성을 위해 메서드를 이용한것 => ("객체 생성 패턴" , "객체생성메서드패턴", "팩토리메서드패턴"이라 함)
		bundle = ResourceBundle.getBundle("db");	//ResourceBundle 객체 생성하기
													//=> 이 객체는 properties만을 위한 객체이므로 파일을 지정할때는 '파일명'만 지정해도 확장자는 자동으로 properties로 인식함. 따라서  확장자를 생략한다.
													//파일경로가 아닌, 클래스패스(=bin폴더)부터 루트토 삼아 properties파일을 찾는다. 그래서 Build Path에서 src파일로 바꿔줘야 컴파일 대상이 됨
		
		try {
			
			Class.forName(bundle.getString("driver")); 	
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}

	
	/*
	 	Connection객체를 얻어오는 getConnection()를 정의함
	 	예외발생시 null
	 */
	public static Connection getConnection() {
		try {
			
			

			return DriverManager.getConnection(bundle.getString("url"),bundle.getString("username"),bundle.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//자원반납을 위한 메서드
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		
		//자원 반납 부분의 코드는 null이 아닐 때만 반납하고, 자원이 null인 경우 아무 동작도 수행하지 않는다. 
		//이렇게 구현된 이유는 null인 자원에 대해서 close를 호출하면 NullPointerException 예외가 발생할 수 있기 때문. 
		//따라서, 코드에서 null인 자원에 대해서 close를 호출하지 않고 그냥 넘어가도록 구현하는 것이 안전한 방법
		//rs, stmt, pstmt, conn이 null이면 Connection, Statement, PreparedStatement, ResultSet을 사용하지 않았다는 의미이므로 따라서 반납할 객체도 없다는 의미
		//null이 아닐때만,  Connection, Statement, PreparedStatement, ResultSet을 사용했다는 의미이므로, 사용한 자원들은 반드시 반납 해야함.
		if (rs != null) try {rs.close();} catch (SQLException e2) {}
		if (stmt != null) try {stmt.close();} catch (SQLException e2) {}
		if (pstmt != null) try {pstmt.close();} catch (SQLException e2) {}
		if (conn != null) try {conn.close();} catch (SQLException e2) {}
	}
}
