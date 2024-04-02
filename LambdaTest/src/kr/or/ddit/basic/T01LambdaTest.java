package kr.or.ddit.basic;

public class T01LambdaTest {
/*
 	람다식 => 익명함수를 생성하기 위한 식. (장점 : 코드가 많이 줄어듦)
 			자바에서는 '매개변수를 가진 코드 블럭' => 런타임시 '익명구현객체'로 생성된다.
 			
 			사용 예) 인터페이스명 객체변수명 = 람다식;
 			
 			람다식의 형식) (매개변수들...) -> {처리할 코드들...}
 			
 			=> 람다식으로 변환할 수 있는 인터페이스는 추상메서드가 1개뿐인 인터페이스만 가능하다.	(ex - Runnable인터페이스는 run()이란 추상메서드 딱 1개만 갖고있음)
 			이렇게 추상메서드가 1개뿐인 인터페이스를 '함수적 인터페이스'라고 한다.
 			이 함수적 인터페이스를 만들때엔 '@FunctionalInterface'로 명시적으로 표시할 수 있다.
 */
	
	public static void main(String[] args) {
		
		//람다식을 사용하지 않는 경우 - 익명객체 이용
		Thread th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				for (int i = 1; i <= 10; i++) {
					System.out.println(i);
				}
			}
		});
		
		th1.start();
		
		// 람다식을 사용하는 경우 (=th1이 람다식으로 변환된것) => 내부적으론 객체처럼 처리된다
		// 람다식을 사용하면, 함수를 객체처럼 다룰 수 있어서 함수를 인자로 전달하거나 반환할 수 있음.
		Thread th2 = new Thread(
				() -> {		//람다 연산자인 화살표(->)를 기준으로 왼쪽은 메서드의 매개변수, 오른쪽은 메서드의 바디
					for (int i = 1; i <= 10; i++) {
						System.out.println("람다-" + i);
					}
				}
		);
		th2.start();
				
	}
	
}
