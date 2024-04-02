package kr.or.ddit.basic;

public class Ex6_4 {
	
	public static void main(String[] args) {
		
		MyMath mm = new MyMath();
		
		long result1 = mm.add(5L, 3L);
		long result2 = mm.subtract(5L, 3L);
		long result3 = mm.multiply(5L, 3L);
		double result4 = mm.divide(5L, 3L);
		
		System.out.println("mm.add(5L, 3L) = "+result1);
		System.out.println("mm.subtract(5L, 3L) = "+result2);
		System.out.println("mm.multiply(5L, 3L) = "+result3);
		System.out.println("mm.divide(5L, 3L) = "+result4);
		
	}
}

class MyMath {

	long add(long a, long b) {
		long result = a+b;
		return result;
	}
	
	long subtract(long a, long b) {return a-b;}
	
	long multiply(long a, long b) {return a*b;}
	
	double divide(double a, double b) {return a / b;}	//long타입(정수)의 매개값을 저장할 매개변수를 double타입으로 설정함
														//(long은 double로 자동형변환 되므로 가능한것) = 자바의정석 181p 참고
					
}
