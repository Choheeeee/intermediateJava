package kr.or.ddit.basic;

public class Ex6_9 {

	public static void main(String[] args) {

//		System.out.println(MyMath2.add(200L, 100L));
//		System.out.println(MyMath2.subtract(200L, 100L));
//		System.out.println(MyMath2.multiply(200L, 100L));
//		System.out.println(MyMath2.divide(200L, 100L));
//		
//
		MyMath2 mm = new MyMath2();
		System.out.println(mm.add(200L, 100L));
		System.out.println();
	}

}


class MyMath2 {
	
	long a, b;
	
	long add() {return a+b;}
	long subtract( ) {return a-b;}
	long multiply() {return a*b;}
	double divide() {return a/b;}
	
	
	static long add(long a, long b) {return a+b;}
	static long subtract(long a, long b) {return a-b;}
	static long multiply(long a, long b) {return a*b;}
	static double divide(long a, long b) {return a/b;}
	
}