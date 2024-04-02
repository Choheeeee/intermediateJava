package kr.or.ddit.basic;

public class ThreadNameExample {

	public static void main(String[] args) {

		Thread t1 = Thread.currentThread();	//currentThread() : 현재 실행중인 스레드 객체를 반환해줌 (현재실행중인 스레드의 메타데이터가 아닌, 실제 객체를 반환해주는 메서드)
		System.out.println(t1);	//콘솔에 표현되는 문자열은 스레드클래스가 toString()을 재정의했기때문!
		System.out.println(t1.getName());	//스레드 이름 출력 (스레드의 메타데이터)
		
		ThreadA t2 = new ThreadA();
		System.out.println(t2.getName());
		
		try {Thread.sleep(1000);} catch (Exception e) {}
		t2.start();
	}

}


class ThreadA extends Thread {
	
	public ThreadA() {
		setName("내이름은 스레드 A");
//		super.setName("나는 뭐지?");
	}
	@Override
	public void run() {
		
		for (int i = 0; i < 3; i++) {
			System.out.println(getName()+"의 작업");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}