package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고, 이 Phone클래스를 이용하여 
	  전화번호 정보를 관리하는 프로그램을 완성하시오.
	  이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
	  
	  전체의 전화번호 정보는 Map을 이용하여 관리한다.
	  (key는 '이름'으로 하고 value는 'Phone클래스의 인스턴스'로 한다.)


실행예시)
===============================================
   전화번호 관리 프로그램(파일로 저장되지 않음)
===============================================

  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 1  <-- 직접 입력
  
  새롭게 등록할 전화번호 정보를 입력하세요.
  이름 >> 홍길동  <-- 직접 입력
  전화번호 >> 010-1234-5678  <-- 직접 입력
  주소 >> 대전시 중구 대흥동 111  <-- 직접 입력
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 5  <-- 직접 입력
  
  =======================================
  번호   이름       전화번호         주소
  =======================================
   1    홍길동   010-1234-5678    대전시
   ~~~~~
   
  =======================================
  출력완료...
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 0  <-- 직접 입력
  
  프로그램을 종료합니다...
  
*/
public class T09PhoneBookTest {

	private Scanner scan;
	private Map<String, PhoneVO> phoneBookMap; // phoneBookMap은 변수명

	public T09PhoneBookTest() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<String, PhoneVO>();
	}

	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");
	}

	// 프로그램을 시작하는 메서드
	public void phoneBookStart() {
		System.out.println("===============================================");
		System.out.println("   전화번호 관리 프로그램(파일로 저장되지 않음)");
		System.out.println("===============================================");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				insert(); // 등록
				break;
			case 2:
				update(); // 수정
				break;
			case 3:
				delete(); // 삭제
				break;
			case 4:
				search(); // 검색
				break;
			case 5:
				displayAll(); // 전체 출력
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	
	//이름=(key값)을 이용하여 해당 전화번호 정보를 조회하기 위한 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();	//이름이 key값이 됨 = 검색할 key값을 아니까 get(name)하여 value를 가져옴.
		
		PhoneVO pv = phoneBookMap.get(name);
		
		if (pv==null) { //pv가 null이란 의미는 name으로 된 해당 객체를 찾을 수 없다는 의미!
			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
		}else {
			System.out.println(name+"======씨의 전화번호 정보=====");
			System.out.println("이름 : "+pv.getName());
			System.out.println("전화번호 : "+pv.getTel());
			System.out.println("주소 : "+pv.getAddr());
		}
		
		System.out.println("검색 작업 완료!");
	}

	//전화번호 정보를 삭제하기 위한 메서드
	private void delete() {

		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		//remove(key) => 삭제 성공하면 삭제된 value값을 반환하고, 실패하면 null반환함.
		if (phoneBookMap.remove(name)==null) {	//널이면 name으로 된 사람이 없음
			System.out.println(name+"씨는 등록된 사람이 아닙니다.");
		}else {
			System.out.println(name+"씨의 전화번호 정보가 삭제되었습니다.");
		}
		System.out.println("삭제 작업 완료");
	}

	// 기존 전화번호 정보를 수정하기 위한 메서드
	private void update() {

		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();

		PhoneVO pv = phoneBookMap.get(name);	//폰북멥에서 업데이트할 이름을 찾기 위해, get(name)

		if (pv == null) { // 널이면 같은 이름으로 등록된 객체가 없으므로, 업데이트 할게 없다는 의미
			System.out.println("수정할"+name + "씨의 전화번호 정보를 찾을 수 없습니다.");
			return; // void일때 메서드 종료하고, 호출한곳으로 돌아감
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼에 남아있는 엔터키 비우기

		System.out.print("주소 >> ");
		String addr = scan.nextLine();

		pv = new PhoneVO(name, tel, addr);

		phoneBookMap.put(name, pv);

		System.out.println(name + "씨 수정 완료");

	}

	// 전체 자료를 출력하기 위한 메서드
	private void displayAll() {
		Set<String> keySet = phoneBookMap.keySet();

		System.out.println("===============================================================================");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("===============================================================================");

		if (keySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 없습니다.");
		} else {

			Iterator<String> it = keySet.iterator();

			int cnt = 1;

			while (it.hasNext()) {
				String name = it.next();
				PhoneVO pv = phoneBookMap.get(name);
				System.out.println(" " + cnt + "\t" + pv.getName() + "\t" + pv.getTel() + "\t" + pv.getAddr());

				cnt++;
			}

		}
		System.out.println("===============================================================================");
		System.out.println("출력 완료!");
	}

	// 새로운 전화번호 정보를 등록하기 위한 메서드.(이미 등록된 사람은 등록되지 않는다.)
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();

		PhoneVO pv = phoneBookMap.get(name);	//Map에 담기전에 중복여부를 검사하기 위해, phoneBookMap에서 name정보를 가져옴

		if (pv != null) { // 중복여부 검사
			System.out.println(name + "씨의 전화번호 정보가 이미 존재합니다.");
			return; // void일때 메서드 종료하고, 호출한곳으로 돌아감
		}
		System.out.print("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼에 남아있는 엔터키 비우기

		System.out.print("주소 >> ");
		String addr = scan.nextLine();

		pv = new PhoneVO(name, tel, addr);

		phoneBookMap.put(name, pv);

		System.out.println(name + "씨 등록 완료");
	}

	public static void main(String[] args) {
		new T09PhoneBookTest().phoneBookStart();

	}

}

//객체의 정보만 저장하기 위한 VO클래스(Value Object = 정보값을 담기위한 클래스)
//장점 : 관리할 데이터가 많아졌을때, 코드 유지보수에 유리함. 객체의 추가된 정보만 아래에  추가해주면 됨.
class PhoneVO {

	public PhoneVO(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	private String name;
	private String tel;
	private String addr;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "PhoneVO [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}

}
