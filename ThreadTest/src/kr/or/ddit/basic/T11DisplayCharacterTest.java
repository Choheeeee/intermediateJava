package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class T11DisplayCharacterTest {
/*
 	3개(명)의 스레드가 각각 알파벳 대문자를 출력하는데 출력을 끝낸 순서대로 결과를 출력하는 프로그램 만들기
 */
	
	static int CURR_RANK = 1; //현재 순위 정보 (모든 스레드들이 static변수에 접근하도록)
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("일지매"));
		disCharList.add(new DisplayCharacter("변학도"));
		disCharList.add(new DisplayCharacter("성춘향"));
		
		for (DisplayCharacter dc : disCharList) {
			dc.start();
		}
		
		for (DisplayCharacter dc : disCharList) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝!");
		System.out.println("------------------------------------");
		System.out.println("경기 결과");
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("순위\t:\t이름");
		
		Collections.sort(disCharList);
		for (DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t:\t" + dc.getName());
		}
		
	}
}



//알파벳 대문자 출력을 위한 스레드 클래스
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter> {
	
	private String name;
	
	private int rank;

	public DisplayCharacter(String name) {
		super(name);		//Thread의 이름을 셋팅 : Thread클래스는 자체로 name변수를 갖고있음
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력문자 : "+ch);
			
			try {
				//200~500사이의 난수 구하기 = sleep시간이 랜덤
				Thread.sleep((int)(Math.random() * 301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(name + "출력 끝!");
		
		setRank(T11DisplayCharacterTest.CURR_RANK++);	//순위 셋팅(증감연산자 사용해서 순위가 매겨지게)
	}

	@Override
	public int compareTo(DisplayCharacter dc) {
		
		return  new Integer(this.getRank()).compareTo(dc.getRank());
	}
	
}