package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

//Properties클래스는 Hashtable을 상속받아 구현되었으며, 키(Key)와 값(Value)을 해시 테이블의 형태로 저장함.
//따라서 Properties 클래스는 Hashtable 클래스의 모든 메서드와 멤버 변수를 상속받아 사용할 수 있으며, 
//Properties 클래스에는 속성(Properties) 설정과 관련된 추가적인 메서드 load(),store()등을 제공함
public class T02PropertiesTest {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		try {
			//파일 내용 읽기 (읽기전에 미리 각 경로와 db.properties파일을 만들어 줘야함 안그러면,class not found exception발생)
			prop.load(new FileInputStream("./res/db.properties"));	//(./는 상대경로인데 현재위치는 JDBCTest프로젝트임)
			
			
			//읽어온 내용 출력하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key+"=> "+value);
				
			}
			System.out.println("출력 끝!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
