package kr.or.ddit.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class T03ResourceBundleTest {

	public static void main(String[] args) {

		/* 
		 	ResourceBundle객체 (=resource 모음집): 리소스들의 확장자가 properties인 파일 정보를 읽어와, key값과 value값을 분리한 정보를 갖는 객체
		 										=> 읽어올 파일은 'key = value' 쌍의 형태로 되어 있어야 한다.
		 										
		 	(properties 파일은 주로 텍스트 데이터를 저장하기 위한 것으로 다국어 메시지 문자열, 이미지 파일 경로, 설정 값 등을 포함할 수 있다.
		 	ResourceBundle클래스는 별도로 작성된 리소스들의 확장자가 properties인 파일을 읽어와 각 언어 및 지역에 맞게 설정하며 로드하는걸 지원함)
		 	
		 	
		 	결론 : Properties클래스는 텍스트 기반의 설정 파일을 로드하고 관리하는 데 사용되며, 
		 		ResourceBundle클래스는 Properties클래스의 properties파일에 대한 기능을 포함하여 국제화 및 지역화를 지원하는 클래스이다.
		 */
		
		
		
		/*
		 	폴더src (소스폴더) : 개발 소스코드 즉, .java파일을 담고있고 폴더src안에 있는 모든 .java파일들은  컴파일 대상이 된다.
		 					(따라서 일반폴더를 src폴더로 설정해 주면 컴파일 대상 폴더로 변경해준다는 의미)
		 	
		 	일반폴더 - 그냥 정리하기 위한 폴더
		 */
		
		//ResourceBundle 객체 생성하기
		//=> 이 객체는 properties만을 위한 객체이므로 파일을 지정할때는 '파일명'만 지정해도 확장자는 자동으로 properties로 인식함. 따라서  확장자를 생략한다.
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.KOREAN);	//파일경로가 아닌, 클래스패스(=bin폴더)부터 루트토 삼아 properties파일을 찾음
																				//그래서 Build Path에서 src파일로 바꿔줘야 컴파일 대상이 됨
		
		//key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
					
			String value = bundle.getString(key);
			
			System.out.println(key + " => "+ value);
			
		}
		System.out.println("출력 끝!");
	}

}
