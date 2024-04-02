package kr.or.ddit.basic;

public class T13ThreadStopTest {
	
	
	public static void main(String[] args) {
	
//		ThreadStopEx1 th1 = new ThreadStopEx1();
//		th1.start();
//		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		//th1.setStopped(true);	//while문에 상태 변화를 줘서 상태값을 제어하는 변수 = flag변수
		//th1.stop();	//자원반납이나 스레드가 해야 작업을 다 마치지않고, 강제종료된 상태 (Deprecated - 추천되지 않는, 더이상 사용하지 않는)
		//flag변수를 이용하는게 권장됨
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th2.interrupt();	//인터럽트 걸기
		
	}
}


class ThreadStopEx1 extends Thread {
	
	private boolean isStopped;	//default : false

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}
	
	@Override
	public void run() {
		while (!isStopped) {
			System.out.println("스레드 작업중...");
			
		}
		
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}


//interrupt()메서드를 이용하여 스레드 중지시키기
class ThreadStopEx2 extends Thread {
	
	@Override
	public void run() {
		//방법1 => sleep()이나 join()등을 사용했을때 interrupt()를 호출하면 InterruptedException이 발생하는것을 이용
		
//		try {
//			while (true) {
//
//				System.out.println("스레드 처리중...");
//				Thread.sleep(1);
//			}
//		} catch (InterruptedException ex) {}
//			
//			System.out.println("자원정리 중...");
//			System.out.println("실행종료!");
			
			
			//interrupt()가 호출되었는지 검사하기
			while (true) {

				System.out.println("스레드 처리 중...");
				
				//검사방법1 => 스레드의 인스턴스 메서드를 이용하는 방법
//				if (this.isInterrupted()) {	//interrupt()가 호출되면 true
//					System.out.println("인스턴스 메서드 isInterrupted() 호출됨");
//					break;
				
				//검사방법2 => 스레드의 static메서드를 이용하는 방법
				if (Thread.interrupted()) {	//interrupt()가 호출되면 true
					System.out.println("정적메서드 interrupted() 호출됨");
					break;
				}
					
			}
	}
}