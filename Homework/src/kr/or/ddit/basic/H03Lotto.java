package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class H03Lotto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("=========================================");
		System.out.println("Lotto 프로그램");
		System.out.println("=========================================");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("=========================================");
		System.out.print("메뉴선택 : ");
		int choice = sc.nextInt();
		
		
		
		if (choice == 1) {
			System.out.println("Lotto 구입시작 !");
			System.out.print("금액 입력 : ");
			int pay = sc.nextInt();
			System.out.println("행운의 로또번호는 아래와 같습니다!!");
			
			for (int i = 1; i <= pay/1000; i++) {
				Set<Integer> lotto = new HashSet<Integer>();
				
				while (lotto.size() < 6) {
					int num = (int) (Math.random()*45+1);
					lotto.add(num);
				}
				System.out.print("로또번호"+i+": ");
				
				Iterator it = lotto.iterator();	//모든 Set타입의 객체는  Iterator를 갖고있음
				while (it.hasNext()) {
					
					System.out.print(it.next()+" ");
				}
//				for (Integer lottonum : lotto) {
//					
//					System.out.print(lottonum+" ");
//				}
				System.out.println();
			}
			System.out.println("받은 금액은 "+pay+"원이고 거스름돈은 "+pay%1000+"원입니다.");
		}else if (choice==2) {
			System.out.println("감사합니다. 프로그램을 종료합니다.");
		}
		
	}
	
}
				
