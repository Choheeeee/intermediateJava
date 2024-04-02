package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class T03ListSortTest {

	/*
	 *	정렬과 관련된 Interface는 Comparable과 Comparator 이렇게 두가지가 있다.
	 *	보통,같은 클래스 안에서 객체들 간의 정렬 순서를 정의할 때  Comparable인터페이스의 compareTo()메소드를 이용,
	 *	Comparator인터페이스를 통해, 정렬 기능만 가진 사용자가 정의한 외부클래스를 정의하고  compare()메소드를 구함으로써 정렬기준을 제공함
	 */
	
	public static void main(String[] args) {

		List<String> list = new ArrayList<String>()	;
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		System.out.println("정렬 전 : "+list);
		
		//static 정적메소드 : 객체생성 없이 "클래스명. " 으로 모든 객체가 전역에서 사용가능 <-> 인스턴스 메소드(해당 객체만이 가지는 고유한 속성)
		//정렬은 Collections.sort() 메소드를 이용하여 정렬한다.
		//sort(): Collections클래스의 static 메소드임
		//sort()는 디폴트로 '오름차순 정렬'(=사전순)
		
		Collections.sort(list);	//list에 들어있는 요소들을 오름차순 정렬함
		
		System.out.println("오름차순 정렬 후 : "+list);
		System.out.println("===================================================");
		
		Collections.shuffle(list);	//list에 드어있는 요소들을 다시 섞기
		System.out.println("섞은 후 : "+list);
		System.out.println();
		
		
		System.out.println("-------------------이번엔 Desc 정렬--------------------");
		Collections.sort(list, new Desc());	//변수없이, 매개변수에서 바로 객체 생성
		System.out.println("Desc 정렬 후 : "+list);
	}

}

//정렬방식을 구현하기 위한 외부 비교자(=Comparator) 인터페이스
//내림차순 정렬 예제
class Desc implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {	//매개변수가 2개 : 둘중에 str1가 크면 양수, str2가 크면 음수, 같으면 0 (오버라이드하여 이 기준을 정해주는 것)
/*
 *	compare()메소드의 반환값을 결정하는 방법
 *	-오름차순일 경우 : 앞의값이 크면 양수, 작으면 음수, 같으면 0을 리턴 = 오름차순 
 */
		return str1.compareTo(str2)*-1;
	}
	
}