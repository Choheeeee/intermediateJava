package kr.or.ddit.basic;


public class T16SyncAccountTest {

	public static void main(String[] args) {
		
		SyncAccount sAcc = new SyncAccount();
		sAcc.deposit(10000);
		
		Thread th1 = new BankThread(sAcc);
		Thread th2 = new BankThread(sAcc);
		
		th1.start();
		th2.start();
	}
}


//은행의 입출금 관리를 위한 공유 클래스 정의
class SyncAccount {
	
	private int balance;	//잔액이 저장될 변수

	public synchronized int getBalance() {	//withdraw()에서 호출되므로 동기화 처리 함
		return balance;
	}
	
	//입금처리를 수행하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	//출금처리를 수행하는 메서드(출금 성공 : true, 출금실패 : false반환)
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해줘야한다.
	public synchronized boolean withdraw(int money) {
		
		if (balance >= money) {	//잔액이 충분할 경우
			for (int i = 1; i <= 1000000000; i++) {}
			
			balance -= money;
			System.out.println("메서드 안에서 balance = "+getBalance());	//동기화메서드 안에서 호출될 getBalance()도 동기화처리 해줘야함.
			
			return true;
			
		}else {
			return false;
		}
	}
}


class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		
		boolean result = sAcc.withdraw(6000);	//6000원 인출
		System.out.println("스레드 안에서 result = "+result+", balance = "+sAcc.getBalance());
	}
}