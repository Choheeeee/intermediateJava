package kr.or.ddit.basic;
//Enum 상수 객체 : 열거형 상수를 나타내는 특수한 형태의 클래스. Enum 상수는들은 열거형 클래스 내부에 정의되며, 각각 고유한 객체임.
//상수는  항상 동일한 값을 갖기때문에 객체생성 하는것에 의미가 없음. 따라서 private 생성자를 디폴트로 가짐. 

//Enum이 갖는 메서드 : name(), valueOf(), ordinal(), values()
//Enum은 자체적으로 name() 값으로 Enum 값을 찾는 valueOf() 메소드를 제공
//valueOf() : 해당 Enum의 모든 상수를 저장한 배열을 생성하여 반환

//기존 상수변수는 각각 다른 클래스에서 다른 상수로 선언했음에도, 값이 같으면 다른 클래스의 다른 객체임에도  컴파일러는 같은 객체로 취급하여 오류를 내주지 않았음.
//즉, 다른 클래스에서 정의된 다른 상수 변수들이어도 가진 값으로만 같은객체인지 다른객체인지 식별해줬음
//하지만 Enum 상수객체는 각각 다른 클래스 타입의 다른 객체로 식별해줌. 값이 같아도 정의한 Enum클래스(타입)이 다르면 다른 객체임
class Flower {
	static final int ROSE = 1;
	static final int TULIP = 2;
}

class Animal{
	static final int LION = 1;
	static final int TIGER = 2;
	
}

public class T07EnumTest {
/*
 	열거형 => 상수값들을 선언하는 방법
 	
 	static final int A = 0;
 	static final int B = 1;
 	static final int C = 2;
 	static final int D = 3;
 	
 	enum Data {A, B, C, D};
 	
 	열거형 선언하는 방법
 		enum 열거형이름 {상수값1, 상수값2, ..., 상수
 		
 */
	public enum HomeTown{광주, 목포, 진주, 대전, 대구};
	
	//City 열거형 객체 선언 (기본값을 이용하는 열거형)
	public enum City {서울(), 부산(), 대구(), 광주, 대전};		//괄호 생략 가능
	
	//데이터값을 임의로 저장하는 열거형 객체 선언
	//데이터값을 지정해 줄 경우 생성자를 만들어서 괄호안의 값이 변수에 저장되도록 해야함.
	
	public enum Season {	//클래스 선언과 비슷한 양상
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");	//결국 총 4개의 상수 객체가 만들어짐
		
		//괄호속의 값이 저장될 변수 선언
		private String data;
		
		//생성자 만들기(열거형의 생성자는 접근제어자가 쓰든안쓰든 묵시적으로 'private'임)	/ (싱글톤 객체만들때도 생성자가 private임)
		//따라서, 자신 클래스 외부에서 호출이 안되므로 객체생성 불가 => 상수이므로 여러객체를 찍어내면 안됨
		Season(String data) {
			this.data = data;
		}
		
		//값을 제공하기 위한 getter메서드 작성
		public String getData() {
			return data;
		}
		
	}
	
	public static void main(String[] args) {
		
		int a = Animal.LION;
		
//		if (a == Flower.ROSE) { 
//			System.out.println("이것은 확실히 장미입니다.");
//		}												//내용상 오류인데도, 막연하게 숫자만 비교해줘서 컴파일 오류를 내주지않음	=> 로직에서 의도치않은 결과가 발생함
														//Enum이 Lion과 Rose를 인식하는게 아니라, 값인 1만 인식함
		if (a == Flower.ROSE) {
			System.out.println("이것은 확실히 장미입니다.");
		}
		
	/*
	 	열거형에서 사용되는 메서드(열거형 상수라면 꼭 갖는 3가지 메서드)
	 	
	 	1. name() => 열거형 상수의 이름을 문자열로 반환한다. (=열거형 상수가 의미하는 문자열값을 반환)
	 	2. ordinal)() => 열거형 상수가 정의된 순서값을 반환한다.(기본적으로 0부터 시작함)
		3. valueOf("열거상수이름") => 지정된 열거형에 '열거형상수이름'과 일치하는 열거형 상수 객체를 반환한다.
	 */
		
		City myCity1; //열거형 객체변수 선언
		City myCity2; //열거형 객체변수 선언
		
		//열거형 객체변수에 값 저장하기
		myCity1 = City.서울;
		myCity2 = City.valueOf("서울");
		
//		System.out.println("myCity1 : "+myCity1.name().toString());	//열거형객체는 toString()을 이미 오버라이딩 해서, 생략해도 되고 표기해도 됨
		System.out.println("myCity1 : "+myCity1.name());	//toString() 생략
		System.out.println("myCity1의 ordinal : "+myCity1.ordinal());
		System.out.println();
		System.out.println("myCity2 : "+myCity2.name());
		System.out.println("myCity2의 ordinal : "+myCity2.ordinal());
		System.out.println("================================================");
		
		Season ss = Season.valueOf("가을");
		System.out.println("name => "+ ss.name());
		System.out.println("ordinal => "+ss.ordinal());
		System.out.println("getter 호출 => "+ss.getData());
		System.out.println("================================================");
		
		//열거형이름.values() => 열거형 상수 배열을 가져온다.
		Season[] enumArr = Season.values();	//map컬렉션에도 values()메서드가 있음(값들만 반환)
											//열거형.values()는 열거형상수 객체를 반환해줌
		for (Season s : enumArr) {
			
			System.out.println("Season 열거형상수 배열의 객체인 "+s.name()+" : "+s.getData());
		}
		System.out.println();
		
		for (City city : City.values()) {
			System.out.println("City 열거형상수 배열의 객체인 "+city.name()+" : "+city.ordinal());
			
		}
		
		City city = City.대구;
		
		System.out.println(city == City.대전);
		System.out.println(city == City.대구);
		
//		System.out.println(city == HomeTown.대구);	=> LION과 ROSE에선 나지 않았던 컴파일오류를 내주고 있음
													//단지 값만 비교해주는게 아니고, 객체 타입 자체도 비교해주므로 타입안전한 코딩을 도와줌
		
		System.out.println("대구와 대구를 비교 => "+city.compareTo(City.대구));	//대구의 ordinal = 2 (대구 == 대구를 비교함 => 같으므로 0반환)
		System.out.println("대구와 서울을 비교 => "+city.compareTo(City.서울));	//서울의 ordinal = 0 / (대구==서울을 비교함 => 앞에가 크므로양수 반환)
		System.out.println("대구와 대전을 비교 => "+city.compareTo(City.대전));	//대전의 ordinal = 4 / (대구==대전을 비교함=> 뒤에가 크므로 음수 반환)
		//결론 : Enum상수들은 Comparable한 객체라서 compareTo()를 사용한 예시
	}

}
