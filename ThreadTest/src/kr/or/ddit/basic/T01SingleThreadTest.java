package kr.or.ddit.basic;

public class T01SingleThreadTest {

	//어떤 자바 실행프로그램이건 메인스레드는 반드시 존재하기 때문에, 이것도 메인인 스레드가 1개 있는 싱글스레드 프로그램임
	
	public static void main(String[] args) {

		//싱글스레드 프로그램
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			
		}
		
		System.out.println();
		
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
		}
	}

}
