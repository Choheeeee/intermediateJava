package kr.or.ddit.basic;

public class Ex6_6 {

	public static void main(String[] args) {

//		Data d = new Data();
//		d.x = 10;	//Data타입 인스턴스의 멤버인 x에 10을저장
		
		int x = 10;
		
		System.out.println("main()에서의 x = "+x);
		
		change(x);
		System.out.println("change() 실행 후");
		System.out.println("main()의 x = 여전히 " +x);
		
	}
	static void change(int x) {
		x = 1000;
		System.out.println("change()의  x  = "+ x);
	}

}


//class Data {int x;}