package kr.or.ddit.basic;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.List;

public class T05WildCardTest {
/*
 	와일드 카드란?
 	
 	와일드카드"?"는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수(Argument)로서 변수선언, 객체생성 및 메서드를 정의할때 사용된다.
 	(제너릭 타입 선언시에는 사용할 수 없음.)
 	
 	<? extends T>	=> 와일드카드의 상한 제한. T와 그 자손들만 가능
 	<? super T>		=> 와일드카드의 하한 제한. T와 그 조상들만 가능
 	<?>				=> 모든 허용가능한 타입 가능.
 	
 */
	
	public static void main(String[] args) {
		
		List<?> list1 = new ArrayList<>();	//ArrayList가 필요하긴 한데 어떤타입인지 아직 정해지지않았을때
											//우선 List타입이기만 하면 되고, 안에 뭘 담든 상관 없다는걸 표현하기 위해 "?"로 표기한다.
		
		FruitBox<Fruit> fruitBox = new FruitBox<>(); //Fruit 과일타입이면 담을 수 있는 FruitBox객체 생성
		FruitBox<Apple> appleBox = new FruitBox<>();//다이아몬드 문법 : 자바8부터 지원하는 문법(=오른쪽 제네릭타입은 생략 가능)
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox);
	}
}
 
class Fruit{
	
	private String name;

	public Fruit(String name) {
		super();
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "과일 ("+name+")";
	}
	
}

class Juicer{
//	static void makeJuice(FruitBox<Fruit> box) { 괄호안에 (FruitBox<Fruit> box)이미 타입을 정해놔서, 다른과일박스는 못들어감. =>엄격함
//	static <T>void makeJuice(FruitBox<T> box) {	<garbage>타입이 오는걸 막아야함
//	static <T extends Fruit>void makeJuice(FruitBox<T> box) {	//T는 T인데, 그중에서 과일박스만 올 수 있음
	static void makeJuice(FruitBox<? extends Fruit> box) {	
		
		String fruitListStr = "";
		
		int cnt = 0;
		for (Object f : box.getFruitList()) {
			if (cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += ", "+f;
			}
			
			cnt++;
		}
		
		System.out.println(fruitListStr+" => 쥬스 완성!!!");
		
	}
}


class Apple extends Fruit{

	public Apple() {
		super("포도");
	}
	
}


class Grape extends Fruit{

	public Grape() {
		super("사과");
	}
	
}

//과일을 담을 과일상자 클래스
//class FruitBox<? extends Fruit> { 이것도 가능(쓰레기가 못오게 아예 제너릭클래스에서 타입제한을 해줄 수도 있음)
class FruitBox<T> {
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
		
	}

	public List<T> getFruitList() {
		return fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
}