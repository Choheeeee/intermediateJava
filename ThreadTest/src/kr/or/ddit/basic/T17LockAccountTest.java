package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T17LockAccountTest {
/*
 	락(Lock)기능을 제공하는 클래스
 	
 	- ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로 동기화처리를 위해 사용함.
 						synchronized를 이용한 동기화처리보다 부가적인 기능이 제공됨.
 						ex) Fairness 설정 등 => 가장 오래 기다린 스레드가 가장 먼저 락을 획득함
 						
	-ReentrantReadWriteLock : Read 및 Write 락을 구분하여 사용가능함
				여러 스레드가 동시에 Read작업은 가능하지만, Write작업은 단 하나의 스레드만 가능함.
				=>Write보다 Read 위주의 작업이 많이 발생하는 경우에 사용하면 성능향상에 도움이 된
 */
	public static void main(String[] args) {

		Lock lock = new ReentrantLock(true);
		
		LockAccount lAcc = new LockAccount(lock);
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
		
	}

}



//입출금 담당하는 공유 클래스
class LockAccount {
	private int balance;
	
	//Lock객체 변수 선언 : 가급적 private final로 선언한다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}
	
	
	//입금처리 메서드
	public void deposit(int money) {
		//Lock객체의 lock()메서드가 동기화 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		//lock()메서드로 동기화 설정한 곳에서는 반드시 unlock()메서드로 해제해 줘야한다.
		lock.lock();	//락 설정 (락을 획득하기 전까지 BLOCKED됨)
		balance += money;	//동기화 처리 부분
		lock.unlock();	//락 해제
	}
	
	
	//출금처리하는 메서드(출금성공 true, 출금실패 false)
	public boolean withdraw(int money) {
		
		boolean chk = false;
		
		//try ~ catch 블럭을 사용할 경우엔 unlock()메서드 호출은 finally블럭에서 하도록 해야한다.
		try {
			lock.lock();
			if (balance >= money) {
				for (int i = 0; i < 1000000000; i++) {} //시간 떼우기
				
				balance -= money;
				System.out.println("메서드 안에서 balance = "+getBalance());
				chk = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			lock.unlock();	//락해제
		}
		
		return chk;	
	}
}


//은행업무를 처리하는 스레드
class BankThread2 extends Thread {
	private LockAccount lAcc;

	public BankThread2(LockAccount lAcc) {
		super();
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		
		boolean result = lAcc.withdraw(6000);
		
		System.out.println(Thread.currentThread().getName()+"스레드 안에서 result = "+result+", balance = "+ lAcc.getBalance());
		
	}
}