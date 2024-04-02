package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {

	public static void main(String[] args) {

	/*
		List와 Set의 차이점
		1.
		-입력한 데이터의 순서(인덱스)가 있다.
		-중복된 데이터를 저장할 수 있다.
		2.Set
		-입력한 데이터의 순서(인덱스)가 없다.
		-중복된 데이터를 저장할 수 없다.
	*/
		
		Set hs1 = new HashSet();
		
		//Set에 데이터를 추가할때도 add()를 이용한다. (List는인덱스가 있어서 add(인덱스,넣을 자료)로 데이터 끼워넣기가 가능하지만, Set은 불가능)
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add("1");
		hs1.add("3");
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
		//Set은 데이터 중복을 허용하지 않는다.
		//그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : "+isAdded);
		System.out.println("Set 데이터 : "+ hs1);
		System.out.println();
		
		isAdded = hs1.add("CC");
		System.out.println("중복될 때 : "+isAdded);
		System.out.println("Set 데이터 : "+ hs1);
		System.out.println();
		
		//Set데이터를 수정하려면 수정하는 메서드가 따로 없기 때문에 해당 자료를 삭제한 후 새로운 데이터를 추가해야한다.
		
		//삭제하는 메서드
		//1) clear() => 전체 데이터 삭제
		//2) remove(삭제할 자료) => 해당 자료 삭제
		
		//예) 'FF'를 'EE'로 수정하기
		hs1.remove("FF");
		System.out.println("삭제 후 Set 데이터 : "+hs1);
		System.out.println();
		
		hs1.add("EE");
		System.out.println("EE 추가 후 Set 데이터 : "+hs1);
		System.out.println();
		
		hs1.clear(); //전체자료 삭제
		System.out.println("clear() 후 Set 데이터 : "+hs1);
		System.out.println("Set의 데이터 개수 : "+hs1.size());
		System.out.println();
		
		//Set에는 인덱스가 없으므로,List처럼 get()메서드 못쓰고 Iterator(반복자) 객체를 이용하여 하나씩 꺼내야한다.
		//Set인터페이스는 추상메서드로 Iterator를 갖고있다.
		Iterator it = hs1.iterator();
		
		while (it.hasNext()) {	// hasNext() => 포인터 다음 위치에 데이터가 있으면 true, 없으면  false를 반환
								//다음데이터가 있는지 검사
			
			System.out.println(it.next());	//next() => 포인터를 다음위치로 이동하고, 이동한 위치의 데이터를 반환한다.
			
		}
		
		//1 ~ 100사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while (intRnd.size() < 5) {

			int num = (int) (Math.random() * 100 +1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : "+ intRnd);
		
		//Collections 유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른 타입의 객체를 생성할 때 생성자의 파라미터로 해당 객체를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd); //Set타입의 객체 intRnd를 ArrayList 형태로 변환할 수 있고, 이제 인덱스로 접근할 수 있다.
		System.out.println("List의 데이터를 출력 ");
		
		for (Integer num : intRndList) {
			
			System.out.print(num + " ");
		
		}
		
		for (Integer num : intRnd) {		//Set은 Iterator를 이용해도 되지만, for-each로도 꺼낼 수 있음.
			System.out.println(num+" ");
		}
	}

}
