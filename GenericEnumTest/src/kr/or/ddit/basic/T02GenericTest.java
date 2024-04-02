package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

class NonGenericClass{
	private Object val;			//Object타입이면 모든 타입 다 쓸 수 있다. 하지만 Casting해줘야함
								//제네릭대신 모든타입이 다 올 수 있는 Object로 쓰면 되는데, 제네릭이 필요한 이유 : 
	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}


class MyGeneric<T>{		//<T> 제너릭이 없다면, 똑같은 내용을 모든타입의 클래스로 몇십개 만들어줘야함
						//멀티타입도 가능 : <T,M,F,V,E>
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
}



public class T02GenericTest {
//Generic = 총칭하는, 통칭하는,포괄적인(타입을 총칭함)	<->	Specific = 특정한
/*
  	제너릭 클래스를 선언하는 방법
  	
  	형식)
  		class 클래스명 <제너릭타입글자> {	//특정 타입을 대표함
  		
  			제너릭타입글자 변수명;			//변수 선언에 제너릭을 사용한 경우
  			...
  		
  			제너릭타입글자 메서드명() {		//반환값이 있는 메서드에 사용한 경우
  			...
  			return 값;
  			
  			}
  			...
  		}
  		
  		--제너릭타입글자--
  		T => Type
  		K => Key 
  		V => Value
  		E => Element 예) List<E>
  		
 */

	public static void main(String[] args) {
		System.out.println("================Object type================");
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);							
		//결론 : Object타입이라서 모든 타입이 다 들어갈 수 있지만,  get()으로 꺼낸 데이터마다 원하는 타입으로 Cast해줘야해서 불편함.
		
		System.out.println("==================Generic==================");
		
		
		List<String> list1 = new ArrayList<String>();	//예시 : 제네릭 덕분에  List<Member> 라고 선언했을때, 멤버클래스의 객체만 모아놓은 List를 만들 수 있게 됨.
		//list1.add("안녕하세요");							//또한 컴파일러가 미리 오류메세지를 내주고, 타입 안정성과 타입 유효성을 보장받음.
		//list1.add(4444);
		
		
		String rtnVal1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnVal1 => "+rtnVal1);
		
		Integer rtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수형 반환값 rtnVal2 => "+rtnVal2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(400);
		
		rtnVal1 = mg1.getVal();
		rtnVal2 = mg2.getVal();
		
		System.out.println("제네릭 문자열 반환값 : "+rtnVal1);
		System.out.println("제네릭 정수형 반환값 : "+rtnVal2);
		
		
	}

}
