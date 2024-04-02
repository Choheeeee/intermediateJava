package kr.or.ddit.basic;
//일반스레드
//데몬스레드 : 일반스레드를 보조해주는 스레드 (일반스레드가 없으면 데몬스레드는 무의미하므로 데몬스레드도 종료됨)
//메인스레드가 작업을 끝내고 먼저 종료되는 예제
//wait() : 누가 깨워줘야만 일어나고, 무한 대기상태	=> notify()를 통해 깨워줌
//sleep() : 설정해준 시간마다 일어남
public class T09ThreadDaemonTest {

	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		//데몬스레드로 설정하기 - 꼭 start() 호출하기 전에 설정해야 한다.
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			
			for (int i = 1; i < 20 ; i++) {
				System.out.println("작업- "+i);
				
				Thread.sleep(1000);
				
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("메인스레드 종료!");
	}

}


class AutoSaveThread extends Thread {
	
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				sleep(3000);
				save();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}