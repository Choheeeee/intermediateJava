package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {

		//MySingleton test1 = new Mysingleton();	//new명령 사용 불가
		
		//getInstance()이용해서 객체를 제공받는다.
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();	//생성자는 한번만 호출되고, test2와 test3이 동일한 single객체를 참조함
		
		test2.displayText();
		test3.displayText();
		
		System.out.println("test2 => " +test2);
		System.out.println("test3 => "+test3);	//동일한 객체를 참조하고 있어, 동일한 객체 주소를 반환함
		
		System.out.println(test2 == test3);	//한 객체로 test2와 test3이 돌려쓰고 있음
	}

}
