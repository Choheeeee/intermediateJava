package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil3;

public class T01HotelInfoTest {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	public void hotelMenu() {
		System.out.println("==============================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실조회 4.프로그램 종료");
		System.out.println("==============================================");
		System.out.print("메뉴선택>> ");
	}
	
	public void hotelStart() {
		
		int choice;
		
		do {
			
			hotelMenu();
			
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				insertRoomNum();
				break;
			case 2:
				deleteRoomNum();
				break;
			case 3:
				selectOne();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다!");
				return;

			default:
				System.out.println("잘못 입력했습니다. 메뉴의 번호를 다시 입력해주세요!");
			}
		} while (choice != 4);
	}
	
	
	
	
	
	private void selectOne() {
		
		System.out.println();
		System.out.println("체크아웃 할 객실번호를 입력하세요");
		System.out.print("객실번호 >> ");
		int roomNum = sc.nextInt();
		
		String guestName = "";
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " select * from hotel_mng " + " where room_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				roomNum = rs.getInt("room_num");
				guestName = rs.getString("guest_name");
			}
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println(" 객실번호\t고객명\t");
			System.out.println("-------------------------------------------------");
			System.out.println(" "+roomNum+"\t"+guestName);
			System.out.println("-------------------------------------------------");
			System.out.println(roomNum + "호 객실정보 출력 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private void deleteRoomNum() {
		
		System.out.println();
		System.out.println("체크아웃 할 객실번호를 입력하세요");
		System.out.print("객실번호 >> ");
		int roomNum = sc.nextInt();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " delete from hotel_mng where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(roomNum + "호 체크아웃 성공!");
			}else {
				System.out.println(roomNum + "호 체크아웃 실패!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private void insertRoomNum() {
		
		boolean isExist = false;
		
		int roomNum = 0;
		
		do {
			System.out.println();
			System.out.println("체크인 할 객실 번호를 입력해주세요.");
			System.out.print("객실번호 >> ");
			roomNum = sc.nextInt();
			
			isExist = checkRoomNum(roomNum);
			
			if (isExist) {
				System.out.println(roomNum + "호는 이미 체크인 된 객실입니다. 다시 입력해주세요!");
			}
			
		} while (isExist);
		
		sc.nextLine();
		System.out.print("체크인 할 고객 이름 >> ");
		String guestName = sc.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " insert into hotel_mng (room_num, guest_name) " + " values(?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, guestName);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(roomNum + "호에 체크인 성공!");
			}else {
				System.out.println(roomNum + "호에 체크인 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	
	private boolean checkRoomNum(int roomNum) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select count(*) from hotel_mng " + " where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt( "count(*)" );
				
			}
			if (cnt > 0) {
				isExist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return isExist;
		
	}

	public static void main(String[] args) {
		new T01HotelInfoTest().hotelStart();
		
	}

}
