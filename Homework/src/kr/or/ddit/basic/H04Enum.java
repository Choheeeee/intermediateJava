package kr.or.ddit.basic;
//Enum 상수 객체
//상수이므로 객체생성 하는것에 의미가 없음 => private 생성자 

//Enum 이 갖는 메서드 : name(), valueOf(), ordinal(), values()
//Enum 은 자체적으로 name() 값으로 Enum 값을 찾는 valueOf() 메소드를 제공
//valueOf() : Enum 값을 순회하면서 일치하는 값이 있는지 찾음

//기존 상수변수는 각각 다른 클래스에서 상수변수들로 만들고, 다른 클래스들의 다른 상수임에도 값이 같아서 컴파일 오류를 내주지 않았음
//상수변수들이 가진 값으로만 같은객체인지 다른객체인지 식별해줬음
//하지만 Enum 상수객체는 클래스처럼 객체이므로 각각 다른 타입으로 식별해줌

//상수변수 예시
//class Flower {
//	static final int ROSE = 1;
//	static final int TULIP = 2;
//}
//
//class Animal{
//	static final int LION = 1;
//	static final int TIGER = 2;
//	
//}

public class H04Enum {
	static final double PI = 3.14;

	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private int radius;

		private Planet(int radius) {
			this.radius = radius;
		}

		public int getRadius() {
			return radius;
		}

		public double getArea() {	//면적구하는 메서드
			return Math.pow(getRadius(),2)*PI*4;
		}
	}

	public static void main(String[] args) {
		
		Planet[] pArr = Planet.values();
		
		for (Planet p : pArr) {
			System.out.println(p.name() + "의 면적은 " + p.getArea() + "입니다");
		}
	
		
	}
}
