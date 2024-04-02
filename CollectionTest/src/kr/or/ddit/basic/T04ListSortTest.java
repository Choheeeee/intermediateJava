package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.MenuDragMouseListener;

public abstract class T04ListSortTest {
	
	public static void main(String[] args) {
	
		List<Member> memlList = new ArrayList<Member>();
		memlList.add(new Member(1, "홍길동", "010-1111-1111"));
		memlList.add(new Member(5, "변학도", "010-1111-2222"));
		memlList.add(new Member(9, "성춘향", "010-1111-3333"));
		memlList.add(new Member(3, "이순신", "010-1111-4444"));
		memlList.add(new Member(6, "강감찬", "010-1111-5555"));
		memlList.add(new Member(2, "일지매", "010-1111-6666"));
		
		System.out.println("정렬 전 : ");
		for (Member mem : memlList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		Collections.sort(memlList);
		
		System.out.println("이름으로 오름차순 정렬 후 : ");
		for (Member mem : memlList) {
			System.out.println(mem);
		}
		
		System.out.println("-----------------------------------------");
		
		Collections.shuffle(memlList);	//섞기
		System.out.println("섞은 후 : "+memlList);
		Collections.sort(memlList,new SortNumDesc());
		System.out.println("정렬 후 : "+memlList);
	}
}
		


//Member의 번호(num)의 내림차순으로 정렬하기 위한 외부정렬자 클래스
class SortNumDesc implements Comparator<Member>{


	@Override
	public int compare(Member mem1, Member mem2) {
		
		if (mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if (mem1.getNum()==mem2.getNum()) {
			return 0;
			
		}else {
			
			return 1;
		}
	}
	
}

//회원 이름을 기준으로 오름차순 정렬이 되도록 클래스 생성하기
class Member implements Comparable<Member>{	//정렬방식을 정해주기 위해 Comparable구현함

	private int num;
	private String name;
	private String tel;
	
	
	
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}



	@Override
	public String toString() {
		
		return "\n Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}



	@Override
	public int compareTo(Member mem) {	//비교대상(매개변수)과 자기자신this를 비교하므로, 매개변수가 1개(매개변수보다 자신이 더 크면 양수, 자신이 더 작으면 음수, 같으면 0)
		
		return this.getName().compareTo(mem.getName());	//String객체면 반드시 compareTo(오름차순)메소드를 갖고있음 (String클래스에서 이미 오버라이딩 해놨기 때문)
	}
	
}