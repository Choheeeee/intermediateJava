package kr.or.ddit.basic;
//결론 : 스레드가 상태변화를 하고 있다	=> 상태변화 이해하기위한 예제
public class T10ThreadStateTest {
/*
 	##스레드 상태에 대해
 	
 	New : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 	Runnable : 실행 중 또는 실행 가능한 상태
 	Blocked : 동기화블럭에 의해서 일시정지된 상태 (락이 풀릴때까지 기다리는 상태)
 	Waiting, Timed_Waiting : 스레드의 종료되진 않았지만 실행가능하지 않은 일시정지상태
							(Timed_Waiting은 일시정지 시간이 지정된 경우임.)
	Terminated : 스레드의 작업이 종료된 상태					
 */
	public static void main(String[] args) {

		Thread target = new TargetThread();
		
		Thread statePrint = new StatePrintThread(target);
		statePrint.start();
	}

}



//스레드의 상태를 출력하기 위한 스레드 클래스
class StatePrintThread extends Thread {
	
	private Thread targeThread;	//상태를 출력할 대상 스레드
	
	public StatePrintThread(Thread targeThread) {
		this.targeThread = targeThread;
	}
	
	@Override
	public void run() {
		while (true) {
			//스레드의 상태를 반환해주는 Enum의 State 
			Thread.State state = targeThread.getState();
			System.out.println("타겟 스레드의 상태값 : "+state);
			
			//New 상태인지 검사
			if (state == Thread.State.NEW) {
				targeThread.start(); //타겟스레드 구동
			}
			
			//타겟스레드가 종료 상태인지 검사
			if (state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class TargetThread extends Thread{
	
	@Override
	public void run() {
		for (long i = 1; i < 10000000000L; i++) {}
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for (long i = 1; i < 10000000000L; i++) {
				
			
		}
	}
}