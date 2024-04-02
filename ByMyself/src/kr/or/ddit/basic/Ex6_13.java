package kr.or.ddit.basic;

public class Ex6_13 {

	public static void main(String[] args) {
		
		Car2 c1 = new Car2();
		Car2 c2 = new Car2("blue");
		
		System.out.println("c1의 color = "+c1.color+", gearType = "+c1.gearType+", door = "+c1.door);
		System.out.println("c1의 color = "+c2.color+", gearType = "+c2.gearType+", door = "+c2.door);
		
	}

}



//생성자에서 다른 생성자를 호출하는 키워드 : this()
class Car2 {
	String color;
	String gearType;
	int door;
	
	
	//세 개의 파라미터를 받는 생성자로, 모든 속성을 초기화
	Car2(String color, String gearType, int door) {
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	
	//이 생성자는 color 파라미터만 받는 생성자로, color를 초기화하고 나머지 속성은 기본값으로 설정
	Car2(String color) {
		this(color,"auto",4);
	}
	
	
	//이 생성자로 만들어진 인스턴스들은 color, gearType, door 등의 멤버 변수는 항상 "white", "auto", 4의 값을 갖게됨
	Car2() {
		
		//방법1 - 1번째 생성자를 호출해서 인스턴스변수들의 값을 고정된 기본값으로 초기화함
		this("red","auto",4);
		
		//방법2 - 방법1처럼 다른생성자(1번째생성자)를 호출하지 않고, 멤버변수에 직접 값을 할당하여 고정된 기본값을 갖게 해도 됨.
		color = "white";
		gearType = "auto";
		int door = 4; 
	}
	
}