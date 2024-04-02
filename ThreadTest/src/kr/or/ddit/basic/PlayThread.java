package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class PlayThread {
	
	public static String inputCheck = "";

	public static void main(String[] args) {

		
	}

}


class Input extends Thread {
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		PlayThread.inputCheck = str;	//정적변수이므로, class이름으로 접근 후 닷연산자
										//입력받자마자 입력이 완료되었음을 알려줌	
		System.out.println("입력한 값은 "+ str + "입니다.");
		
	}
}

class CountDown2 extends Thread {
	@Override
	public void run() {
	
	for (int i = 5; i > 0; i--) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	System.out.println("5초가 경과되어 사용자 패!");
		
	}
	
}


class Rnd extends Thread {
	@Override
	public void run() {
	}
	
	int num = (int) (Math.random() *3);
}