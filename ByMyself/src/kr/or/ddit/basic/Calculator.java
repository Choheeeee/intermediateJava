package kr.or.ddit.basic;

//여러 사람이 같이쓰는 계산기 클래스
class Calculator {

	private int memory;
	
	public int getMemory(){
		return memory;
	}
	
	public void setMemory(int memory) {
		this.memory = memory;
		try {Thread.sleep(2000);} catch (Exception e) {}
		System.out.println(Thread.currentThread().getName()+"가 설정한 값 : "+ this.memory);
	}
}



//계산기 사용자1
class User01 extends Thread{
	
	private Calculator calc;
	
	public void setCalc(Calculator calc) {
		this.setName("User01");
		this.calc = calc;
	}
	
	@Override
	public void run() {
		calc.setMemory(100);
	}
}



//계산기 사용자2
class User02 extends Thread{
	
	private Calculator calc;
	
	public void setCalc(Calculator calc) {
		this.setName("User02");
		this.calc = calc;
	}
	
	@Override
	public void run() {
		calc.setMemory(50);
	}
}