package kr.or.ddit.ref;

/*
 * Class 오브젝트(클래스 정보를 담고있는 클래스)를 생성하기
 */
public class T01ClassObjectCreationTest {
/*
 	Java Reflection : 리플렉션은 Java 프로그래밍 언어의 기능 중 하나로, 실행 중에 클래스나 객체의 정보를 검사하고 조작하는 데 사용되는 기술을 가리킴.
 						(JVM메모리에 저장된 클래스에 대한 정보가 마치 거울에 투영된 모습과 닮아있어, 리플렉션이라는 이름을 가지게 됨)
 		
 		- 리플렉션은 클래스의 메타데이터에 접근하거나 클래스의 동적 로딩, 인스턴스 생성, 필드 및 메서드에 접근하는 데 사용됨
		- 리플렉션  API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
 	
 		- java.lang.Class의 주요 메서드 : getName(), getSuperClass(), getInterfaces(), getModifiers() 등
 		- java.lang.reflect 패키지의 주요클래스 : Field, Method, Constructor, Modifier 등
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		//사용자정의 클래스에대한 java.lang.Class의 객체를 얻어오는 방법 = 즉, Class객체를 통해 클래스들의 메타데이터를 조회하는 방법
		
		//첫번째 방법 : Class.forName() 이용하기
		Class<?> klass = Class.forName("kr.or.ddit.ref.T01ClassObjectCreationTest");	//Class : 클래스의 모든 정보를 담을 수 있는 'Class'란 클래스
		
		//두번째 방법 : getclass() 이용하기
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		klass = obj.getClass();
		
		//세번째 방법 : .class 이용
		klass = T01ClassObjectCreationTest.class;
	}
}
