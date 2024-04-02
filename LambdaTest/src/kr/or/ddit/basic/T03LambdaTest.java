package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class T03LambdaTest {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		
		list.add("홍길동1");
		list.add("홍길동2");
		list.add("홍길동3");
		
		for (String name : list) {
			System.out.println(name);
		}
		System.out.println("-------------------------------");
		
		
		list.forEach(new Consumer<String>() {	//Consumer 인터페이스도 추상메서드가 1개뿐이라 함수적인터페이스임 => 람다식으로 변환 가능

			@Override
			public void accept(String name) {
				System.out.println(name);
				
			}
		});
		
		System.out.println("--------------람다식--------------");
		list.forEach((name) -> System.out.println(name));
		
		/*
		 	메서드 참조에 대해
		 	
		 	참조변수::인스턴스메서드
		 	클래스명::정적메서드
		 	클래스명::인스턴스메서드
		 	생성자::new
		 */
		
		//람다식은 또 메서드참조하는 형태를 위 주석처럼 변환해서 쓸 수 있다. => 33라인과 똑같음
		list.forEach(System.out::println);
		
		
		//사용자정의 클래스를 사용하기 위해, 클래스를 객체생성 해주고 위의 매서드참조 문법을 이용해서  클래스객체의 참조변수를(mp::) 이용해서 메서드를 호출해봄.
		//MyPrint mp = new MyPrint();
		//list.forEach(mp::printName);
		
		//객체생성을 괄호안에서 해줌으로써, 클래스 객체의 참조변수는 없지만 위의 객체생성 + 메서드 호출을 한줄에서 다 함
//		list.forEach(new MyPrint()::printName);	//참조변수::인스턴스메서드
		
		list.forEach(MyPrint::new);	//위 문법처럼 생성자메서드 호출
	}

}


class MyPrint {
	public MyPrint(String name) {
		System.out.println("생성자 안에서 name : "+name);
	}
	public void printName(String name) {
		System.out.println("name : " + name);
	}
}
