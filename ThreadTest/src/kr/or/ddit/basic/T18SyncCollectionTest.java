package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18SyncCollectionTest {
/*
 	Vector, HashTable 등의 예전부터 사용하던  Collection클래스들은 내부에 동기화 처리가 되어있다.
 	하지만, 최근에 새로 구성된 클래스들은 동기화 처리가 되어 있지 않다.
 	그래서 동기화가 필요한 경우엔 직접 동기화처리를 한 후 사용해야 한다.
 	
 */
	//동기화처리를 하지 않은 경우
//	private static List<Integer> list1 = new ArrayList<Integer>();
	
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
	
	//동기화처리를 한 경우
	public static void main(String[] args) {
		
		Runnable r =  new Runnable() {	//방법3으로 스레드를 생성함 (T02 예제 참고)
			
			@Override
			public void run() {

				for (int i = 1; i <= 10000; i++) {
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		
		Thread[] ths = new Thread[] {new Thread(r), new Thread(r),
									new Thread(r), new Thread(r), new Thread(r)};
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("list1의 개수 : "+list1.size());
		System.out.println("list2의 개수 : "+list2.size());
	}
}
