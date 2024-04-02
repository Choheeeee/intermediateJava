package kr.or.ddit.basic;


public class Ex6_1 {

	public static void main(String[] args) {

		int height = Card.height;
		int width = Card.width;	//Card클래스의 static변수인 height, width는 인스턴스 생성 없이, Card.변수명으로 사용가능
		
		Card c1 = new Card();
		c1.kind = "Heart";
		c1.number = 7;
		System.out.println("c1은 "+c1.kind+", "+c1.number+"이며, 크기는( "+c1.width+", "+c1.height+")입니다.");
		
		Card c2 = new Card();
		c2.kind = "Spade";
		c2.number = 4;
		System.out.println("c2는 "+c2.kind+", "+c2.number+"이며, 크기는( "+c2.width+", "+c2.height+")입니다.");
		
		c1.height = 80;
		c1.width = 50;
		System.out.println("c1은 "+c1.kind+", "+c1.number+"이며, 크기는( "+c1.width+", "+c1.height+")입니다.");
		System.out.println("c2는 "+c2.kind+", "+c2.number+"이며, 크기는( "+c2.width+", "+c2.height+")입니다.");
		
	}

}


class Card {
	String kind;
	int number;
	
	static int height = 100;
	static int width = 250;
}