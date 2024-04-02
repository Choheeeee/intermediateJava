package kr.or.ddit.basic;

/*
 * 제너릭 : 제한된 타입 파라미터 문법	-	제너릭을 <T> 라고 줬을때, 모든타입이 오는게 아니라 그 모든 타입중에서도 올수있는 타입을 제한해줄 수 있음.
 */

class Util2{
	
//	public static <T> int compare(T t1, T t2) {
	public static <T extends Number> int compare(T t1, T t2) {	//타입 제한 : T라고해서 모든타입이 다 오는게 아니고, T중에서도 Number나 Number의 하위클래스들만 오게끔 함.
																//Number는 모든 숫자타입의 슈퍼클래스
		
		double v1 = t1.doubleValue();							//doubleValue() : 숫자를 double형식으로 변환하기위해 사용되는 메서드
		double v2 = t2.doubleValue();							
																//doubleValue()와 valueOf() 헷갈리지 말것!!: 래퍼클래스와 String을 다른 데이터타입으로 변환하는데 사용됨
																//			  	Double.parseDouble() :문자열을 double타입으로 변환해줌
		
		return Double.compare(v1, v2);
	}
}
public class T04GenericMethod {

	public static void main(String[] args) {
		
		int result = Util2.compare(10, 20);
		System.out.println(result);
		
		result = Util2.compare(3.14, 3);
		System.out.println(result);
	}
}
