package kr.or.ddit.basic;

public class T19WaitNotifyTest {
/*
 	wait() => 동기화 영역에서 락을 풀고, 공유객체마다 갖고있는 Wait-Set영역으로 이동시킨다.
 	notify() 또는 notifyAll() => Wait-Set영역에 있는 스레드를 깨워서 실행할 수 있게 한다.
 								(notify()는 Wait-Set에 있는 객체중 랜덤으로 1개, notifyAll()은 Wait-Set영역에 있는 모든 객체를 깨운다.)
 								
	
	=> wait(), notify(), notifyAll() 메서드는 동기화 영역에서만 실행할 수 있고, Object클래스에서 제공하는 메서드임.
 */
	
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		thA.start();
		
		Thread thB = new ThreadB(workObj);
		thB.start();
		
	}
}

//공유객체 클래스
class WorkObject {
	public synchronized void methodA() {
		
		System.out.println("methodA()에서 작업 시작...");
		
		System.out.println(Thread.currentThread().getName() + " : notify() 호출!");
		
		notify();
		
		System.out.println(Thread.currentThread().getName() + " : wait() 호출!");
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("methodA()에서 작업 끝!");
	}
	
	
	public synchronized void methodB() {
		
		System.out.println("methodA()에서 작업 시작...");
		
		System.out.println(Thread.currentThread().getName() + " : notify() 호출!");
		
		notify();
		
		System.out.println(Thread.currentThread().getName() + " : wait() 호출!");
		
		try {
			wait(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("methodB()에서 작업 끝!");
	}
}


//WorkObject의 methodA()만 호출하는 스레드
class ThreadA extends Thread {
	
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super("ThreadA");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			workObj.methodA();
		}
		
		System.out.println(this.getName() + " => 스레드 종료");
	}
	
	
}


//WorkObject의 methodB()만 호출하는 스레드
class ThreadB extends Thread {
	
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		super("ThreadB");
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			workObj.methodB();
		}
		
		System.out.println(this.getName() + " => 스레드 종료");
	}
	
	
}
