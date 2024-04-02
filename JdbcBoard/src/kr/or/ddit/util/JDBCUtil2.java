package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



//JDBCUtil은 모든 설정값들을 하드코딩한것이고, JDBCUtil2는 JDBCUtil과 똑같지만 Properties객체를 이용해 설정값들을 설정함.
public class JDBCUtil2 {
	
	static Properties prop;
	
	//클래스 초기화블럭 : 프로그램 실행중 이 클래스가 로딩될때 딱 한번 실행되어 딱 한번만 로딩을 체크하게 하려고
	static {
		
		//Properties클래스는 Hashtable을 상속받아 구현되었으며, 키(Key)와 값(Value)을 해시 테이블의 형태로 저장함.
		//따라서 Properties 클래스는 Hashtable 클래스의 모든 메서드와 멤버 변수를 상속받아 사용할 수 있으며, 
		//Properties 클래스에는 속성(Properties) 설정과 관련된 추가적인 메서드 load(),store()등을 제공함
		prop = new Properties();
		
		try {
			
			//load()는 InputStream객체를 인자로 받아서 해당 스트림을 통해 Properties파일(키-값 쌍으로 설정 정보를 저장한 텍스트 파일)을 읽어옴.
			prop.load(new FileInputStream("res/db.properties"));
			
			
			
//			forName() : java.lang.Class 클래스의 정적 메서드이고, 인자에서 문자열로 표현된 클래스의 이름을 가지고 해당 클래스를 동적으로 로드함. 
//							클래스의 메타데이터에 접근하고 클래스를 동적으로 생성하는 데 유용하게 사용됨
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");	=> 이렇게 하드코딩된것을 지양하고, 아래처럼 properties파일을 이용하자.
			Class.forName(prop.getProperty("driver")); //key값인 "driver"를 인자로 넣음으로써, 키값에 해당하는 value값인 (oracle.jdbc.driver.OracleDriver)를 반반환해줌
			System.out.println("드라이버 로딩 완료!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*
	 	Connection 객체 생성 메서드
	 	Connection 객체, 예외발생시 null
	 */
	public static Connection getConnection() {
		try {
			
			//아래처럼 직접 코드를 입력하는 하드코딩을 지양하고, 변수나 properties파일 등을 이용하는걸 지향함 -> properties 설정파일의 key = value를 이용한것.
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC04","java");	
			return DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
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
		if (rs != null) try {rs.close();} catch (SQLException e2) {}
		if (stmt != null) try {stmt.close();} catch (SQLException e2) {}
		if (pstmt != null) try {pstmt.close();} catch (SQLException e2) {}
		if (conn != null) try {conn.close();} catch (SQLException e2) {}
	}
}
