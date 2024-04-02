package kr.or.ddit.basic;

public class MySingleton {
/*
 	- 싱글턴패턴이란? 객체(인스턴스)를 한개만 만들어지게 하는 프로그래밍 방법	=> 불필요한 객체생성을 막을 수 있음.
 	
 	- Singleton Class를  구성하는 방법
 		1.자기자신 class의 참조변수를 멤버변수로 선언한다.
 		(이 변수는 private static으로 지정한다.)
 		
 		2. 생성자를 private으로 한다.
 		(외부에서 생성자에 접근하지 못하게 하려고, 즉 외부에서 new키워드를 시용하지 못하게하기 위해)
 		
 		3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
 		(이 메서드는 static으로 선언하고 이름은 보통 getInstance()로 지정한다.)
 */

	
	//자기 자신의 타입 객체를 담기위한 참조변수 생성.
	private static MySingleton instance;
	
	
	//생성자를 private으로 지정한다.
	private MySingleton() {
		System.out.println("생성자가 호출되었습니다.");
	}
	
	
	//외부에서 객체를 가져가 쓸 수 있게끔 해주는  유일한 방법(인스턴스가 단 하나만 만들어지도록, null일때만 만들고 null이 아니면 이미 민들어져있던 기존객체를 반환)
	public static MySingleton getInstance() {
		if(instance == null) {
			instance = new MySingleton();
		}
		return instance;
	}
	
	
	public void displayText() {
		System.out.println("안녕하세요 싱글턴 객체입니다.");
	}
}
