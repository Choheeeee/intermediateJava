package kr.or.ddit.basic;

public class Ex6_7 {

	public static void main(String[] args) {

		Data2 d = new Data2();	//Data2의 인스턴스의 주소를 d에 저장
								//인스턴스의 주소를 담고있는 d를 통해 인스턴스를 다룸.
		
		d.x = 10;	//d에 저장된 주소에서 멤버변수인 x에 10을 저장한다.
		System.out.println("main()의 x = "+d.x);
		
		change(d);	//Data2의 인스턴스의 주소를 담고있는 d를 매개변수로 넘겨줌
		System.out.println("change() 실행 후 ");
		System.out.println("main()의 x = "+d.x);
	}

	static void change(Data2 d) {	//매개변수로 받은 인스턴스의 주소가 d에 복사됨
		d.x = 1000;	//d에 저장된 주소값으로 x에 직접 접근하고 1000으로 값을 변경함.
		System.out.println("change()의  x = "+d.x);	//1000이 출력됨
	}
	
}


class Data2 {
	
	int x;
}