package kr.or.ddit.basic;

public class Ex6_15 {
	
	static int[] arr = new int[10];	//명시적 초기화를 통해 10개짜리 배열 arr을 생성함
	
	static {
		for (int i = 0; i < arr.length; i++) {	//클래스초기화 블럭을 이용해서, 배열의 각 요소에 난수로 초기화함
			arr[i] = (int)(Math.random()*10)+1;	//1~10난수 생성
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr["+i+"]"+arr[i]);
		}
		
	}

}
