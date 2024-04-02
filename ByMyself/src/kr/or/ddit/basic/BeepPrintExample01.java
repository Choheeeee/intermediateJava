package kr.or.ddit.basic;

import java.awt.Toolkit;

public class BeepPrintExample01 {
	
	public static void main(String[] args) {
		Toolkit tk = Toolkit.getDefaultToolkit(); //기본 Toolkit 객체 얻기
		
		for (int i = 0; i < 5; i++) {	//5회 반복
			
			tk.beep();	//beep음 발생
			
			try { Thread.sleep(1000); } catch (InterruptedException e) {}	//1초간 일시정지
		}
		
		
		
		
		for (int i = 0; i < 5; i++) {
			
			System.out.println("beep");	//beep 문구 출력
			
			try { Thread.sleep(1000); } catch (Exception e) {}	//1초간 일시정지
		}
	}

}
