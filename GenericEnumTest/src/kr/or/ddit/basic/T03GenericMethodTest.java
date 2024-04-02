package kr.or.ddit.basic;
//제너릭메서드를 만들고 실행해보는 예제
//제너릭 : 선언하는 시점에 써주는 문법
class Util{
	/*
	 	제너릭 메서드<T, R> R method(T t)
	 	
	 	파라미터 타입과 리턴타입으로 타입글자를 가지는 메서드
	 	
	 	선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입파라미터를 기술 후 사용!
	 */
	public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {	//리턴타입 왼쪽에 <>를 써줌으로써, 제너릭메서드라는걸 확인함
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		
		return keyCompare && valueCompare;	//key값도 value값도 같아야 true를 반환하도록 설계
	}
}


/*
 * 	멀티타입<K, V>을 가지는 제너릭 클래스
 */
class Pair<K, V>{	//key와 value를 한쌍으로하는 정보만 관리하는 VO클래스
	
	private K key;
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
//	key와 value값을 출력하기 위한 일반메서드 (파라미터의 순서에 영향을 줌)
//	public void displayAsll(K key, V value) {
//		System.out.println(key+" : "+value);
//	}
	
	public <K, V> void displayAsll(K key, V value) {	//메서드에 제너릭타입을 주면, 제네릭 타입을 먼저 의존하고 그다음에 파라미터의 타입을 적용함
														//그래서 92, 93라인에 메서드의 파라미터 순서를 바꿔써도 됨 = 제너릭의 장점
		System.out.println(key+" : "+value);
		
	}
	
}


public class T03GenericMethodTest {
	
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<Integer, String> (1,"홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String> (1,"홍길동");
		
		//구체적인 타입을 명시적으로 지정(생략도 가능)
		boolean result1 = Util.<Integer, String> compare(p1, p2);
		
		if (result1) {
			System.out.println("논리(의미)적으로 동일한 객체임");
			
		}else {
			System.out.println("논리(의미)적으로 다른 객체임");
		}
		
		Pair<String, String> p3 = new Pair<String, String> ("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String> ("001", "홍길동");
		
		//구체적인 타입 생략함
		boolean result2 = Util.compare(p3, p4);
		if (result2) {
			System.out.println("논리(의미)적으로 동일한 객체임");
			
		}else {
			System.out.println("논리(의미)적으로 다른 객체임");
		}
		
		p1.displayAsll("키", 100);
		p1.displayAsll(100, "키");
	}

}
