package kr.or.ddit.basic;

import java.awt.Toolkit;

public class BeepPrintExample02 {

	public static void main(String[] args) {


		
		Thread th1 = new Thread(new BeepTask());
		th1.start();
		
		for (int i = 0; i < 5; i++) {
			
			System.out.println("beep");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}
		
		
//		Thread bp = new BeepPrint();
//		bp.start();
		
//		BeepPrint bp2 = new BeepPrint();
//		bp2.start();
	}

}



class BeepTask implements Runnable {

	@Override
	public void run() {

		Toolkit tk = Toolkit.getDefaultToolkit();
		
		for (int i = 0; i < 5; i++) {
		
			tk.beep();
			
			try {Thread.sleep(1000);} catch (Exception e) {}
		}
	}
	
}


class BeepPrint extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 0; i < 5; i++) {
			
			System.out.println("beep");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}
	}
}

