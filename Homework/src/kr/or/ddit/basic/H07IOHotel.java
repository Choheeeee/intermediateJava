package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class H07IOHotel {
	
	Scanner sc = new Scanner(System.in);
	
	Map<String, Hotel2> hotelMap = new HashMap<String, Hotel2>();
	
	
	//H07Hotel클래스의 생성자로, 이 클래스가 생성될때 파일에 저장된 객체를 불러오기 위해 생성자에서 IO작업을 함. (생성자메서드가 아닌, 일반메서드로 정의해도 됨)
	public H07IOHotel() {	
		
		ObjectInputStream ois = null;
		
		try {
			File f = new File("d:/D_Other/hotelObj.bin");
			
			if(f.exists()) {
				ois = new ObjectInputStream(new FileInputStream("d:/D_Other/hotelObj.bin"));
				
				hotelMap = (Map<String, Hotel2>) ois.readObject();
				
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(ois!=null) 
					ois.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		new H07IOHotel().hotelStart();
		
	}

	
	public void hotelMenu( ) {
		System.out.println("==============================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.투숙객 조회 4.업무종료");
		System.out.println("==============================================");
		System.out.print("메뉴 선택 >> ");
	}
	
	public void hotelStart() throws Exception {
		
		while (true) {

			hotelMenu();

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				checkIn();
				break;

			case 2:
				checkOut();
				break;

			case 3:
				selectOne();
				break;

			case 4:

				System.out.println("프로그램을 종료합니다! 감사합니다:)");
				objOutput();
				return;

			default:
				System.out.println("번호를 잘못 입력했습니다!! 다시 입력해주세요!!");
			}
		}

	}
	
	public void objOutput() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/hotelObj.bin"));
			oos.writeObject(hotelMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.flush();
				oos.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void selectOne() {

		System.out.println();
		System.out.print("조회할 객실번호를 입력해주세요 >> ");
		String roomNo = sc.next();
		sc.nextLine();
		
		Hotel2 ht = hotelMap.get(roomNo);
		
		if (ht == null) {
			System.out.println(roomNo+"객실에 체크인 내역이 없습니다.");
		}else {
			System.out.println("********"+roomNo+"의 체크인 내역 입니다:)"+"********");
			System.out.println("투숙객 명 : "+ht.getName());
			System.out.println("이용 객실 : "+ht.getRoomNo());
		}
		
		
	}

	public void checkIn() {
		System.out.println();
		System.out.println("몇 호실에 체크인 하시겠습니까?");
		System.out.print("호실 입력 >> ");
		String roomNo = sc.next();
		sc.nextLine();	//버퍼처리용 줄 바꾸기

		
		
		Hotel2 ht = hotelMap.get(roomNo);	//방번호를 담고있는 변수 roomNo를 키값으로 넣어서, value값을 얻으려함

		if (ht != null) {
			System.out.println(roomNo + "호는 체크인이 불가능합니다! 이미 사용중입니다.");
			return;
		}

		System.out.print("체크인 할 고객의 이름을 입력해주세요  >> ");
		String cuName = sc.nextLine();

		ht = new Hotel2(roomNo, cuName);
		hotelMap.put(roomNo, ht);

		System.out.println(cuName + "님이 " + roomNo + "호에 체크인 되었습니다!");
		
		
	}
	
	
	public void checkOut() {
	
		System.out.println();
		System.out.print("퇴실할 객실 번호를 입력해주세요. >> ");
		String roomNo = sc.next();
		sc.nextLine();	//버퍼 처리용
		
		Hotel2 ht = hotelMap.get(roomNo);
		
		if (ht==null) {
			System.out.println(roomNo+"객실에 체크인 내역이 없습니다.");
		}else {
			System.out.println(roomNo+"호실이 체크아웃 되었습니다!");
			}
		}
	}



class Hotel2 implements Serializable {
	
	private String roomNo;
	private String name;
	
	public Hotel2(String roomNo, String name) {
		super();
		this.name = name;
		this.roomNo = roomNo;
	}

	
	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public String getRoomNo() {return roomNo;}

	public void setRoomNo(String roomNo) {this.roomNo = roomNo;}
	
}