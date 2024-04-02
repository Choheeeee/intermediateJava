package kr.or.ddit.basic;

class Ex6_14 {
	static {
		System.out.println("static {클래스 초기화 블럭}");
	}
	
	{
		System.out.println("{인스턴스 초기화 블럭}");
	}
	
	public Ex6_14() {
		System.out.println("생성자");
	}
	
	
	public static void main(String[] args) {
		Ex6_14 bt = new Ex6_14();
		
		Ex6_14 bt2 = new Ex6_14();
		
		//실행결과 : 클래스 초기화 블럭은 처음 메모리에 로딩될때 한번만 수행되지만, 인스턴스 초기화 블럭은 인스턴스가 생성될 때 마다 수행된다.
	}
}
