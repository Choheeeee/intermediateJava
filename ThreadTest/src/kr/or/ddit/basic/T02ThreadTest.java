package kr.or.ddit.basic;

public class T02ThreadTest {

	public static void main(String[] args) {
		//방법1과 2의 차이 : 상속을 받냐, Runnable 인터페이스를 구현하냐의 차이
		//자바는 다중상속이 안되므로 이미 다른걸 상속중인 객체는 Runnable인터페이스를 이용
		
		//멀티스레드 프로그램 만들기
		//방법 1 : Thread클래스를 상속한 사용자정의class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
//		th1.start();
		th1.run();
		
		//방법2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후, 이 인스턴스를 Thread객체의 인스턴스를 생성할때 생성자 매개변수로 넘겨준다.
		//객체를 자주 생성해야한다면 2번과 같은 방법을 쓰고, 일회적으로 쓸땐 3번과 같은 방법을 쓴다
		
//		Runnable r = new MyThread2();	//MyThread2 클래스의 참조변수인 r을 만들어서 생성하냐, 21번라인처럼 참조변수 없이 MyThread2 클래스를 생성하냐의 차이
//		Thread th2 = new Thread(r);

		Thread th2 = new Thread(new MyThread2());
//		th2.start();
		th2.run();

		//방법3 : 익명클래스를 이용하는 방법
		//		Runnable인터페이스를 구현한 익명클래스를 Thread인스턴스를 생성할때 매개변수로 넣어준다.
		//이때 생성된 스레드객체의 인스턴스 메서드 start()를 호출한다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					
					try {
						//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
						//시간은 밀리세컨 단위를 사용한다.
						//즉, 1000은 1초를 의미한다.
						Thread.sleep(100);	//0.1초동안 잠들었다가 별찍기 
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
//		th3.start();	//각각의 쓰레드객체를 병렬적으로 실행되게 해주는 메서드
		th3.run();		//start()없이 run()만 호출하면, 병렬적인 실행이 아닌 일반메서드의 호출과 다를바 없이 여태 해온 싱글스레드일때 처럼 동작함
		
		System.out.println("main()메서드 끝!");
		//"main()메서드 끝!" 이 문장이 뒤에 있음에도, 먼저 출력되는 이유
		//main쓰레드까지 총 4개의 쓰레드가 작동하고 있는 프로그램
		//각각의 모든 쓰레드가 종료될때 프로그램 전체가 종료됨
		
	}

}


//MyThread1도 Thread를 상속했으므로 쓰레드 클래스임
class MyThread1 extends Thread{
	
	@Override
	public void run() {

		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			
			
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨 단위를 사용한다.
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100);	//0.1초동안 잠들었다가 별찍기 
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class MyThread2 implements Runnable {

	@Override
	public void run() {

		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
				
				
			try {
				//Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨 단위를 사용한다.
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100);	//0.1초동안 잠들었다가 별찍기 
					
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
