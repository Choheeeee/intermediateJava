package kr.or.ddit.basic;

import java.util.LinkedList;
//LinkedList가 List인터페이스를 구현했기 때문에, LinkedList도 List의 모든 속성을 사용할 수 있고, 거기에 LinkedList만의 추가된 push, pop, offer, poll 메소드까지 사용할 수 있다.
//LinkedList의 장점 : ArrayList는 중간에 있는 데이터를 삭제하거나 변경하면, 내부에서 나머지 뒤에있던 데이터들이 다 앞으로 이동하게 됨. 
//					자료가 많은데 데이터 변경과 삭제가 많을 수록, 속도에 영향을 미치므로 비효율적
//					반면에, LinkedList는 연결된 노드(링크)를 끊어주고 추가된곳에 노드를 바로 참조시키면 되므로 데이터 수정과 삭제가 잦을땐, LinkedList자료구조가 효율적임
public class T02StackQueueTest {

	public static void main(String[] args) {

		/*
		 		Stack => 후입선출(LIFO)의 자료 구조(push, pop, peek)
		 		Queue => 선입선출(FIFO)의 자료 구조(offer, poll, peek)
		 */
		
		LinkedList<String> stack = new LinkedList<String>(); //이 예제는 LinkedList만이 갖고있는 메소드(push, pop, offer, poll)를 쓰기 위해, 
															//타입이 부모인터페이스인 List가 아닌 LinkedList타입으로 선언해야함.
		
		/*
		 	LinkedList를 Stack방식처럼 동작하게 하는 명령
		 	1) 자료 입력 : push(저장할 값)
		 	2) 자료 출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다.
		 */
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		System.out.println("현재 stack값들 : "+stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 자료 : "+ data);
		System.out.println("꺼내온 자료 : "+ stack.pop());
		System.out.println("현재 stack값들 : "+stack);
		System.out.println("======================================");
		
		
		stack.push("성춘향");
		System.out.println("현재 stack값들 : "+stack);
		System.out.println("꺼내온 자료 : "+ stack.pop());
		System.out.println("======================================");
		System.out.println();
		
		LinkedList<String> queue = new LinkedList<String>();	
		/*
		 		LinkedList를 Queue방식처럼 동작하게 하는 명령
		 		1) 자료 입력 : offer(저장할 값)
		 		2) 자료 출력 : Poll() => 자료를 Queue에서 꺼내온 후 꺼내온 자료를 Queue에서 삭제한다.
		 */
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		System.out.println("현재 queue의 값 : "+queue);
		
		
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료 : "+ temp);
		System.out.println("꺼내온 자료 : "+ queue.poll());
		System.out.println("현재 queue의 값 : "+ queue);
		System.out.println("=================================");
		
		if (queue.offer("성춘향")) {
			System.out.println("신규 등록 자료 : 성춘향");
			
		}
		
		System.out.println("현재 queue의 값 : "+ queue);
		System.out.println("꺼내온 자료 : "+ queue.poll());
	}

}
