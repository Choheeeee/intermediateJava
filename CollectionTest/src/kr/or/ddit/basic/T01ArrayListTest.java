package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import com.sun.glass.ui.Size;
import com.sun.org.apache.bcel.internal.generic.NEW;


public class T01ArrayListTest {

	public static void main(String[] args) {

		//기본용량 : 10 (기본적으로 10개짜리 list가 생성됨)
		//크기를 기본용량보다 늘리고 싶으면 List list1 = new ArrayList(100); 괄호안에 임의적으로 원하는 크기를 넣어주면 됨
		List list1 = new ArrayList();	//List타입이기 때문에, new옆에 List타입을 구현한 모든 클래스인 ArrayList, LinkedList, Vector가 다 올 수 있다.
		
		//add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); //기본형 Integer타입으로 초기화했지만, 실제로 내부적으로 컬렉션에 들어갈땐 Object타입으로 들어감.
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		
		//size() => 데이터 개수
		System.out.println("size() => " + list1.size());
		System.out.println("list1 => "+ list1);
		System.out.println("=====================================================");
		
		//get()을 이용하여 데이터 꺼내오기
		System.out.println("1번째 자료 꺼내기: "+ list1.get(0));	//get()괄호 안의 값은 Index (=Index의 의미 : 순서, 정렬이 가능하다.)
		System.out.println("=====================================================");
		
		//이미 초기화한 데이터들 사이에, 다른 데이터 끼워넣기(18라인 이하의 add()메서드는 파라미터가 1개, 끼워넣기할때의 add()메서드는 끼워넣을 위치까지 지정해줘야 하므로 2개)
		list1.add(0,"zzz");
		System.out.println("list1(끼워넣기 후) => "+list1);
		System.out.println("=====================================================");
		
		//데이터 변경
		String temp = (String) list1.set(0,"yyy");	//데이터수정은 set메소드 이용 (파라미터가 2개) = 0번째 zzz를 yyy로 변경
													//set() : 0번째를 yyy로 변경하는 동시에, 원래 있던 zzz를 String타입으로 반환함
		System.out.println("temp => "+temp);		//따라서 temp엔 zzz가 담겨있음.
		System.out.println("list1(데이터 변경 후) => "+list1);
		System.out.println("=====================================================");
		
		//데이터 삭제
		list1.remove(0);
		System.out.println("데이터 삭제 후 => "+ list1);
		
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 => "+list1);
		list1.remove(1);	//1번째 인덱스를 지워라	
		//list1.remove(111);	//111번째 인덱스를 지워라 라고 인식하게됨 따라서, Integer객체로써 지워야함
		list1.remove(new Integer(111));	//Integer객체로 만들어서 삭제함
		System.out.println("111 삭제 후 => "+list1);
		System.out.println("=====================================================");		
		
		
		//제너릭 => 리스트에 들어갈 타입을 제한해주는 도구
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
			
		for (String s : list2) {
			
			System.out.println(s);
		}
		
		System.out.println("=====================================================");
		//contains(비교객체) => 리스트가 '비교값'을 갖고있으면 true, 안 갖고있으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		System.out.println("=====================================================");
		
		
		//indexOf (비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 존재하는 index값을 반환한다. (없으면 -1을 반환)
		System.out.println("DDD의 index값 : "+ list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : "+ list2.indexOf("ZZZ"));
		System.out.println("=====================================================");		
		
		for (int i = list2.size()-1; i >= 0; i--) {	//리스트의 크기만큼 for문을 다 돌면서 삭제했음에도, 여전히 2개가 남아있음. 
			list2.remove(i);
		}
			
		//여전히 2개가 남은 이유 : for문이 첫번째 실행됐을때, 인덱스0번째가 처음으로 지워지면 다음내용들이 0번을 또 채우게 됨(남은 나머지 내용들이 계속 인덱스0으로 오게됨). 따라서 마지막내용부터 지워줘야함
		System.out.println("list2 size() => "+ list2.size()); 
	}
}
