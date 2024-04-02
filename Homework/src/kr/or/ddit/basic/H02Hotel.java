package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class H02Hotel {

	Scanner sc = new Scanner(System.in);
	Map<String, Hotel> hotelMap = new HashMap<String, Hotel>();

	public static void main(String[] args) {
		new H02Hotel().hotelStart();
	}

	public void hotelMenu() {
		System.out.println("==============================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.투숙객 조회 4.업무종료");
		System.out.println("==============================================");
		System.out.print("메뉴 선택 >> ");
	}

	public void hotelStart() {

		while (true) {

			hotelMenu();

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				create();
				break;

			case 2:
				delete();
				break;

			case 3:
				read();
				break;

			case 4:

				System.out.println("프로그램을 종료합니다! 감사합니다:)");
				return;

			default:
				System.out.println("번호를 잘못 입력했습니다!! 다시 입력해주세요!!");
			}
		}

	}

	public void create() {
		System.out.println();
		System.out.println("몇 호실에 체크인 하시겠습니까?");
		System.out.print("호실 입력 >> ");
		String roomNo = sc.next();
		sc.nextLine();

		Hotel ht = hotelMap.get(roomNo);

		if (ht != null) {
			System.out.println(roomNo + "호는 체크인이 불가능합니다! 이미 사용중입니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String cuName = sc.nextLine();

		ht = new Hotel(roomNo, cuName);
		hotelMap.put(roomNo, ht);

		System.out.println(cuName + "님이 " + roomNo + "호에 체크인 되었습니다!");
	}

	public void read() {
		System.out.println();
		System.out.println("조회할 객실번호를 입력해주세요!");
		System.out.print("호실 입력 >> ");
		String roomNo = sc.next();
		sc.nextLine();
		
		Hotel ht = hotelMap.get(roomNo);
		
		if (ht==null) {
			System.out.println(roomNo+"객실에 체크인 내역이 없습니다.");
		}else {
			System.out.println("********"+roomNo+"호의 체크인 내역 입니다:)"+"********");
			System.out.println("투숙객 명 : "+ht.getName());
			System.out.println("이용 객실 : "+ht.getRoomNo()+"호");
			
		}
		
		
		
	}

	public void delete() {
		sc.nextLine();
		System.out.println();
		System.out.println("몇 호실을 체크아웃 하시겠습니까?");
		System.out.print("호실 입력 >> ");
		String roomNo = sc.nextLine();
		
		
		Hotel ht = hotelMap.get(roomNo);
		
		if (hotelMap.remove(roomNo) == null) {
			System.out.println(roomNo+"호실은 체크인 된 객실이 아닙니다!");
		}else {
			System.out.println(roomNo+"호실은 체크아웃 되었습니다!");
		}
		System.out.println("체크아웃 완료 !");
	}

}

class Hotel { // VO클래스(객체의 정보만 담고있는 클래스)

	private String roomNo;
	private String cuName;

	public Hotel(String roomNo, String cuName) {
		super();
		this.roomNo = roomNo;
		this.cuName = cuName;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNum(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return cuName;
	}

	public void setName(String cuName) {
		this.cuName = cuName;
	}

	@Override
	public String toString() {
		return "호텔투숙객 정보 [호실 = " + roomNo + ", 고객명 = " + cuName + "]";
	}

}
