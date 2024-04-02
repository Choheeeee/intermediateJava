package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class T08MapTest {

	public static void main(String[] args) {
	/*
	 	Map => key값과 value값을 한 쌍 (=엔트리)으로 관리하는 객체
	 		=> key값은 중복을 허용하지 않고 (= 식별자), 인덱스가 존재하지 않는다.(Set의 특징)
	 		=> value값은 중복을 허용한다.(List의 특징)
	 */
		Map<String, String> map = new HashMap<String, String>();
		
		//데이터 추가 => put()
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1111-1111");
		System.out.println("map => "+map);
		
		//데이터 수정 => put()
		//데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.	 [put(수정할key값, 새로운 value값)]
		map.put("addr", "서울");	//key값이 이미  addr로 들어가있으므로, 값만 대전 -> 서울(나중값)로 변경
		System.out.println("map => "+ map);
		
		//데이터 삭제 => remove(삭제할 데이터의 key값)
		map.remove("name");
		System.out.println("map => "+map);
		
		
		//데이터 읽기 => get(key값) : key값을 알때
		System.out.println("key값을 알때는 map.get(addr)으로 데이터 읽기  = "+map.get("addr"));
		System.out.println("============================================================================");
		
		
		
		//key값을 몰라서 먼저 전체 key값들을 읽어와 어떤 key값들이 있는지 알아내야 한다. 그다음에 알아낸 key을 통해 value값에 접근하여, 데이터를 출력하는 방법
		//방법1 => keySet()이용하기 (keyList로 만들수도 있는데 keySet으로 만든 이유 : Set은 중복을 허용하지 않기때문)
		Set<String> keySet = map.keySet();	//keySet에는 key값들만 들어있음(key들만 모아놓은 집합)
		
		System.out.println("Iterator를 이용하는 방법");
		
		Iterator<String> it = keySet.iterator();
		
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(keySet + " : "+map.get(key));
		}
		System.out.println("============================================================================");
		System.out.println("향상된 for문을 이용하는 방법");	//Iterable를 구현한 모든 객체는 for-each문을 쓸 수 있음
		
		for (String key : keySet) {
			System.out.println("key"+" : "+map.get(key));
			
		}
		System.out.println("============================================================================");
		
		//방법 3 => key값 필요 없이, value값만 필요할 때
		//방법1,2와 달리 key값에 먼저 접근 하는게 아니고, value에만 접근해서 value값만 읽어온다.
		System.out.println("values() 메서드 이용한 방법");
		for (String value : map.values()) {
			System.out.println(value);
		}
		
		
		System.out.println("============================================================================");
		//방법4 => Map관련 클래스에는 Map.Entry타입의 내부 클래스가 만들어져 있다.
		//		Map에서 이 Map.Entry타입의 객체들을 Set타입으로 제공하는 메서드를  사용한다.
		//key+value를 묶은 단위 = Entry	
		//그런 Entry들을 모아놓은 집합 = EntrySet
		
		//Map.Entry 타입의 객체를 담은 Set객체 가져오기
		System.out.println("entrySet()을 이용한 방법");
		
		Set<Map.Entry<String, String>> entrySet = map.entrySet();	//Entry는 Map의 내부 인터페이스인데, 굳이 Map안에 내부인터페이스로 설정한 이유 :
																	//Entry는 key + value이므로 Map 자료구조에서만 의미가 있음
		
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();	//Entry타입은 for-each문으로도 꺼내오기 가능
		while (entryIt.hasNext()) {

			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : "+ entry.getKey());
			System.out.println("value값 : "+ entry.getValue());	//Entry타입의 객체라면 getKey(), getValue()메서드를 가져야함
			System.out.println();
		}
	}

}
