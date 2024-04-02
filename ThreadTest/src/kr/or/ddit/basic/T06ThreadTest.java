package kr.or.ddit.basic;

import javax.swing.JOptionPane;
//결론 : 메인, th1, th2 총3개의 쓰레드가 모두 종료돼야, 한 프로그램이 종료되는것
public class T06ThreadTest {

	//입력여부를 확인하기 위한 변수 선언
	public static boolean INPUT_CHECK = false;
	
	public static void main(String[] args) {

		Thread th1 = new DataInput();
		th1.start();
		
		Thread th2 = new CountDown();
		th2.start();
		
	}

}

/*
 	사용자의 입력과 카운트다운 처리를 위한 스레드
 */
class DataInput extends Thread {
	
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		T06ThreadTest.INPUT_CHECK = true;	//정적변수이므로, class이름으로 접근 후 닷연산자
											//입력받자마자 입력이 완료되었음을 알려줌	
		System.out.println("입력한 값은 "+ str + "입니다.");
		
	}
}


class CountDown extends Thread {
	
	@Override
	public void run() {

		for (int i = 10; i >= 1; i--) {
			
			if (T06ThreadTest.INPUT_CHECK) {	//사용자입력이 true=입력이 됐으면, run()을 종료시켜라 => CountDown스레드 종료를 의미
				return;							//return문이 메서드 종료의 의미로 쓰임
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과됐는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);	//DataInput쓰레드는 안 끝나기때문에, 프로그램을 강제 종료시키는 명령
	}
}