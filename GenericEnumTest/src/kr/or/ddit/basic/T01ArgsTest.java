package kr.or.ddit.basic;

public class T01ArgsTest {
/*
 	가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다름(들어올 매개변수가 몇개인지 모를때 ...으로 표현)
 		- 가변형 인수는 메서드 안에서 내부적으로 배열로 처리된다.
 		- 가변형 인수는 한가지 자료형만 사용될 수 있다.
 */
	/**
	 * 배열을 이용한 메서드
	 * (매개변수로 받은 정수들의 합계를 구하는 메서드)
	 * @param data
	 * @return
	 */
	
	public int sumArr(int[] data) {	//호출시 메인에서 배열 생성을 해줘야함
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형인수를 이용한 메서드
	public int sumArg(int... data) {	//int값의 매개변수가 들어오는데, 몇개인지 모를때 ' ... '사용
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형인수와 일반적인 인수를 같이 사용할 경우에는, 가변형 인수를 제일 뒤쪽에 배치해야 한다.
		public String sumArg2(String name, int... data) {	//일반적인 인수인 String을 먼저 표기
			int sum = 0;
			
			for (int i = 0; i < data.length; i++) {
				sum += data[i];
			}
			return name + "씨의 점수 : "+sum;
		}
	
	
	public static void main(String[] args) {

		T01ArgsTest at = new T01ArgsTest();
		
		int[] nums = {100, 200, 300};		//sumArr()메서드는 매개변수로 배열만 받기때문에, 매개변수로 들어갈 nums 배열변수를 준비해줘야한다.
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1,2,3,4,5}));	//배열변수 없이, 괄호안에 바로 배열 생성 가능!
		System.out.println();
		
		System.out.println(at.sumArg(100,200,300));
		System.out.println(at.sumArg(1,2,3,4,5));
		System.out.println();
		
		System.out.println(at.sumArg2("홍길동", 1,2,3,4,5,6,7,8,9));
	}

}
