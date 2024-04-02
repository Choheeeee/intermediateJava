package kr.or.ddit.basic;

public class T03ThreadTest {

	public static void main(String[] args) {
		//여기의 모든 코드는 메인스레드를 위한 코드
		
		
		//스레드의 수행시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		//UTC(Universal Time Coordinated 협정 세계 표준시)를 사용하여 1970-01-01 0시 0분 0초를 기준이고, 현재까지 경과한 시간을 밀리세컨(1/1000초)단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		
		th.start();	//스레드 작업시작
		
		try {
			th.join();	//메인스레드가 현재 실행중인 스레드에서 작업중인 스레드(지금은 th스레드)가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간(ms) : "+ (endTime - startTime));	//th가 작업이 끝날때까지 메인쓰레드가 기다려준 시간 = th가 작업하는데 걸린시간
	}

}


class MyRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0;
		for (int i = 0; i <= 1000000000; i++) {
			sum += i;
		}
		
		System.out.println("누적합계 : "+sum);
	}
	
}