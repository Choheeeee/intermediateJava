package kr.or.ddit.basic;

public class CalculatorExample {

	public static void main(String[] args) {

		Calculator calc1 = new Calculator();
		
		User01 u1 = new User01();
		u1.setCalc(calc1);
		u1.start();
		
		User02 u2 = new User02();
		u2.setCalc(calc1);
		u2.start();
		
	}

}
