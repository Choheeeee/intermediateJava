package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashcodeTest {
/*
 	해시함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
 	해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 부른다.
 	-분명 다른 데이터인데도, 해쉬코드가 같아 같은 한 메모리에 저장되어 덮어쓰기가 된 상황  = '충돌' (충돌을 최소화하고, 데이터 검색이 빠른 해시함수가 좋은 함수이다.)
 	-그래서 주소비교에 내용비교까지 해야, 두 객체가 진짜 다른지를 알게됨  => 충돌이 방지됨
 	
 		*HashSet, HashMap, HashTable과 같은 객체들을 사용할 경우 객체가 서로 같은지를 비교하기 위해 equals()와 hashCode()메서드를 호출한다.
 		*그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다.
 		*객체가 같은지 여부는 데이터를 추가할 때 검사한다.
 		
 		-equals()는 두 객체의 주소가 달라도, 내용만 같으면 같은 객체라고 간주하는 관점
 		-hashCode()는 다른 내용을 가졌어도, 주소만 같으면 같은 객체라고 간주하는 관점 => 해시테이블 작성시 사용됨
 		-결론 : 내용도 같고, 주소도 같아야 비로소 같은 객체이므로 equals와 hashCode를 동시에 재정의 해줘야함.
 		
 		-equals()와 hashCode()에 관련된 규칙
 		1. 두 객체가 같으면 반드시 같은 hashCode를 가져야한다.
 		2. 두 객체가 같으면 equals()메서드를 호출했을때 true를 반환해야 한다.
 			즉, 객체 a,b가 같다면 a.equals(b)와 b.equals(a) 둘 다 true를 반환해야 한다.
 		3. 두 객체의 hashCode가 같다고 해서, 두 객체가 반드시 같은 객체는 아니다.
 			하지만, 두 객체가 같으면 반드시 hashCode가 같아야 한다.
 		4. equals()를 재정의 하면 반드시 hashCode()도 재정의 해야 한다.
 		5. Object클래스의 hashCode()는 기본적으로 Heap 메모리에 존재하는 각 객체에 대한 메모리주소를 기반으로 한 정수값을 반환하다. 
 			그러므로 hashCoe()를 재정의 하지 않으면, 절대로 두 객체가 같은 hashCode를 반환하지 않는다.(다른 객체로 간주됨)
 			
 		-hashCode()에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode값을 만들어 낼 수 있다. 그래서 객체가 같지 않아도 hashCode가 같을 수 있다.
 */
	public static void main(String[] args) {
		
		System.out.println("Aa".hashCode());	//해쉬코드 2112 반환
		System.out.println("AA".hashCode());	//해쉬코드 2080 반환
		System.out.println("BB".hashCode());	//해쉬코드 2112반환
												//결론 : 다른 문자열임에도 2112 같은 해쉬코드를 반환하여 "충돌"이 일어난걸 볼 수 있다. 
												//		충돌상황을 막기 위해, equals와 hashCode를 재정의 해줘야함
		System.out.println("=============================================================");
		
		Person p1 = new Person(1,"홍길동");
		Person p2 = new Person(1,"홍길동");
		Person p3 = new Person(1,"이순신");
		
		System.out.println("p1.equals(p2) => "+p1.equals(p2));
		System.out.println("p1.equals(p3) => "+p1.equals(p3));
		System.out.println("p1==p2  =>  "+(p1==p2));
		System.out.println("=============================================================");
		
		Set<Person> pSet = new HashSet<Person>();
		System.out.println("add(p1) 성공여부 : "+pSet.add(p1));
		System.out.println("add(p2) 성공여부 : "+pSet.add(p2));
		
		System.out.println("p1, p2 등록 후 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p1.getId()+ " : "+p1.getName());
		}
		
		System.out.println("add(p3) 성공여부 : "+ pSet.add(p3));
		System.out.println("p3 등록 후 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p.getId()+" : "+p.getName());
		}
		System.out.println("============================================================");
		System.out.println("remove(p2) 성공여부 : "+pSet.remove(p2));
		System.out.println("remove(p2) 후 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p.getId()+" : "+p.getName());
		}
	}

}



class Person{

	private int id;
	private String name;
	
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
//	@Override
//		public int hashCode() {
//		
//		
//			return (this.getName()+this.getId()).hashCode(); //String클래스에서 hashCode()를 이미 오버라이드 해놨기때문에, 같은 문자열이면 같은 해쉬코드를 반환함
//		}
//	
//	@Override
//		public boolean equals(Object obj) {
//		
//		Person p = (Person)obj;
//		
//		return (this.getId()==p.getId() && this.getName().equals(p.getName()));
//		}
	

	
	
//이클립스에서 만들어준 자동완성 오버라이드
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
}