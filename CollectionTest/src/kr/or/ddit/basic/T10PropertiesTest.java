package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class T10PropertiesTest {

	public static void main(String[] args) throws IOException {
	/*
	 	Properties클래스는 Map보다 축소된 기능의 객체라고 할 수 있다.
	 	Map은 모든 타입의 객체데이터를 key와 value값으로 사용할 수 있지만
	 	Properties객체는 key와 value값으로 String만 사용할 수 있다.
	 	
	 	Map은 put(), get()을 이용하여 데이터를 입출력 하지만,
	 	Properties는 setProperty(), getProperty()를 이용하여 데이터를 입출력한다.
	 	Properties 파일은 key = value 의 형태로 출력해줌
	 */
		
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1111-1111");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름 : "+ name);
		System.out.println("전화 : "+ tel);
		System.out.println("주소 : "+ prop.getProperty("addr"));
		
		//데이터를 파일로 저장하기
		//파일경로 표시할때 . 점1개 : 현재 프로젝트의 위치를 의미(현재 경로)
		//		.. 점2개 : 상대 경로
		prop.store(new FileOutputStream("src/kr/or/ddit/basic/test.properties"), "comments(주석)");	//Properties(key값 = value값으로 이루어진)파일로 저장 생성됨
		
		//Properties파일 내용 읽어오기
		prop.load(new FileReader("src/kr/or/ddit/basic/test2.properties"));
		
		System.out.println("읽어온 age : "+prop.getProperty("age"));
	}

}
