package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


import kr.or.ddit.util.JDBCUtil3;

public class BoardMain {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	public void boardMenu() {
		System.out.println("--------------------------------------");
		System.out.println("  === 메 뉴 선 택 ===");
		System.out.println("  1. 전체게시글 조회");
		System.out.println("  2. 게시글 검색");
		System.out.println("  3. 게시글 작성");
		System.out.println("  4. 게시글 수정");
		System.out.println("  5. 게시글 삭제");
		System.out.println("  6. 프로그램 종료");
		System.out.println("--------------------------------------");
		System.out.print("메뉴 선택 >> ");
		
	}
	
	public void start() {
		
		int choice;
		
		do {
			boardMenu();
			
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				displayAllcontents();
				break;
			case 2:
				searchContent();
				break;
			case 3:
				insertContent();
				break;
			case 4:
				updateContent();
				break;
			case 5:
				deleteContent();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다!");
				return;

			default:
				System.out.println("잘못 입력했습니다. 메뉴의 번호를 다시 입력해주세요!");
			}
		} while (choice != 6);
	}
	
	
	
	private void deleteContent() {

		sc.nextLine();
		
		System.out.println("--------------------------------------");
		System.out.print("삭제할 게시글의 작성자를 입력해주세요 >> ");
		String writer = sc.next();
		
		selectByWriter(writer);
		
		System.out.print("삭제할 게시글 번호를 입력해주세요 >> ");
		int no = sc.nextInt();
		sc.nextLine();
		System.out.println("--------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " delete from jdbc_board "
						+ "where board_writer = ? and  board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, writer);
			pstmt.setInt(2, no);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(writer + "님의 "+no+"번 게시글 삭제 성공!");
			}else {
				System.out.println(writer +"님의 "+no+"번 게시글 삭제 실패!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void updateContent() {

		sc.nextLine();
		
		System.out.println("--------------------------------------");
		System.out.print("수정할 게시글의 작성자를 입력해주세요 >> ");
		String writer = sc.next();
		
		selectByWriter(writer);
		
		sc.nextLine();
		
		System.out.print("몇 번 게시글번호를 수정하시겠습니까 ? >> ");
		 String no = sc.nextLine();
		 
		System.out.println("--------------------------------------");
		
		
		System.out.print("제목 변경 >> ");
		String title = sc.nextLine();
		
		System.out.print("내용 변경 >> ");
		String content = sc.nextLine();
		
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " update jdbc_board " + " set board_title = ?  " + " , board_content = ? "
							+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, no);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(no+"번 게시글 수정 완료!");
			}else {
				System.out.println(no+"번 게시글 수정 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void insertContent() {
		
		sc.nextLine();
		
		System.out.println("--------------------------------------");
		System.out.print("작성할 게시글 제목을 입력해주세요.");
		String title = sc.next();
		
		System.out.print("작성자를 입력해주세요.");
		String writer = sc.next();
		
		System.out.print("작성할 내용을 입력해주세요.");
		String content = sc.next();
		System.out.println("--------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) " 
							+ " values (board_seq.nextval, ? , ? , sysdate, ? ) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(writer +"님의 게시글 등록 성공 !");
			}else {
				System.out.println(writer + "님의 게시글 등록 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	private void searchContent() {
		sc.nextLine();
		System.out.println("--------------------------------------");
		System.out.println("검색할 게시글 정보를 입력해주세요. (skip 가능)");
		System.out.println("게시글 번호 >> ");
		String no = sc.nextLine().trim();
		
		System.out.println("게시글 제목 >> ");
		String title = sc.nextLine().trim();
		
		
		
		System.out.println("게시글 작성자 >> ");
		String writer = sc.nextLine().trim();
		
//		System.out.println("게시글 작성일 >> ");
//		String date = sc.nextLine().trim();
		
		System.out.println("--------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board where 1=1 ";
			
			if (no != null && !no.equals("")) {
				sql += " and board_no = ? ";
			}
			if (title != null && !title.equals("")) {
				sql += " and board_title = ? ";
			}
			if (writer != null && !writer.equals("")) {
				sql += " and board_writer = ? ";
			}
//			if (date != null && !date.equals("")) {
//				sql += " and board_date = ? ";
//			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			if (no != null && !no.equals("")) {
				pstmt.setString(index++, no);	//바뀌는 물음표위치 인덱스를 표현하기 위해 index에 후위 증감연산자
			}
			if (title != null && !title.equals("")) {
				pstmt.setString(index++, title);
			}
			if (writer != null && !writer.equals("")) {	
				pstmt.setString(index++, writer);
			}
//			if (date != null && !date.equals("")) {
//				pstmt.setString(index++, date);
//			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				no = rs.getString(1);
				title = rs.getString(2);
				writer = rs.getString(3);
//				date = rs.getString(4);
				
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자");
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" "+no+"\t\t"+title+"\t\t"+writer+"\t\t");
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private void selectByWriter(String writer) {
		
		int no;
		String title;
		String date;
		String content;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board " + " where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			System.out.println("--------------------------------------");
			System.out.println(writer+"님의 모든 게시글 목록입니다.");
			while (rs.next()) {
				no = rs.getInt("board_no");
				title = rs.getString("board_title");
				writer = rs.getString("board_writer");
				date = rs.getString("board_date");
				content = rs.getString("board_content");
				
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자\t\t작성일\t\t게시글내용");
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" "+no+"\t\t"+title+"\t\t"+writer+"\t\t"+date+"\t\t"+content);
				System.out.println("----------------------------------------------------------------------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}
	private void displayAllcontents() {
		
		int no;
		String title;
		String writer;
		String date;
		String content;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				no = rs.getInt(1);
				title = rs.getString(2);
				writer = rs.getString(3);
				date = rs.getString(4);
				content = rs.getString(5);
				System.out.println();
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자\t\t작성일\t\t게시글내용");
				System.out.println("----------------------------------------------------------------------------------------------------");
				System.out.println(" "+no+"\t\t"+title+"\t\t"+writer+"\t\t"+date+"\t\t"+content);
				System.out.println("----------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		
	}

	public static void main(String[] args) {
		new BoardMain().start();
	}

}
