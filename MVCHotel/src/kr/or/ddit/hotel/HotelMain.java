package kr.or.ddit.hotel;

import java.util.Scanner;

import kr.or.ddit.hotel.dao.vo.HotelVO;
import kr.or.ddit.hotel.service.HotelServiceImpl;
import kr.or.ddit.hotel.service.IHotelService;

public class HotelMain {
//체크인, 체크아웃, 투숙객 전체조회, 투숙객 검색, 투숙객 정보변경(호실, 고객이름 변경), 해당 호실이 체크인돼있는지 체크하기
	private IHotelService hotelService;
	private Scanner sc;
	
	public HotelMain() {
		
		hotelService = HotelServiceImpl.getInstance();
		sc = new Scanner(System.in);
	}
	
	public void hotelMenu() {
		
		System.out.println("==============================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.투숙객정보 변경 4.전체조회 5.검색 6.프로그램 종료");
		System.out.println("==============================================");
		System.out.print("메뉴선택>> ");
	}
	
	public void start() {
		
		int choice = 0;
		do {
			hotelMenu();
			
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				changeInfo();
				break;
			case 4:
				displayAll();
				break;
			case 5:
				serch();
				break;

			default:
				System.out.println("메뉴의 숫자를 입력해주세요!");
				return;
			}
		} while (choice != 5);
	}
	
	
	
	
	
	
	
	private void serch() {
		// TODO Auto-generated method stub
		
	}

	private void displayAll() {
		// TODO Auto-generated method stub
		
	}

	private void changeInfo() {
		// TODO Auto-generated method stub
		
	}

	private void checkOut() {
		// TODO Auto-generated method stub
		
	}

	private void checkIn() {
		
		boolean isInUse;
		int roomNo;
		do {
			System.out.print("체크인 할 객실 번호를 입력해주세요 >> ");
			roomNo = sc.nextInt();
			
			isInUse = hotelService.isAlreadyInUse(roomNo);
			
			if (isInUse) {
				System.out.println("이미 사용중인 객실입니다! 번호를 다시 입력해주세요");
			}
		} while (isInUse);
		
		String customerName = sc.nextLine();
		HotelVO hv = new HotelVO();
		hv.setRoomNo(roomNo);
		hv.setCustomerName(customerName);
		
		int cnt = hotelService.checkIn(hv);
		
		if (cnt > 0) {
			System.out.println(hv.getRoomNo()+"호에 "+customerName+"님 체크인 완료!");
		}else {
			System.out.println(hv.getRoomNo()+"호에 "+customerName+"님 체크인 실패!");
		}
	}

	public static void main(String[] args) {
		new HotelMain().start();;
	}

}
