package kr.or.ddit.board;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.dao.vo.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

public class BoardMain {

	private IBoardService boardService;
	private Scanner sc;
	
	public BoardMain() {
		
		boardService = new BoardServiceImpl();
		sc = new Scanner(System.in);
	}
	
	
	public void displayMenu() {
		
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
			displayMenu();
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				selectAllBoard();
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
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요!");
			}
			
		} while (choice != 6);
		
	}
	
	
	private void deleteContent() {

		myContents();
		
		System.out.print("삭제할 게시글 번호를 입력해주세요 >> ");
		String boardNo = sc.nextLine();
		System.out.println("--------------------------------------");
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		
		int cnt = boardService.deleteContetn(boardNo);
		if (cnt > 0) {
			System.out.println(bv.getBoardNo()+"번 게시글 삭제 성공!");
		}else {
			System.out.println(bv.getBoardNo()+"번 게시글 삭제 실패!!");
		}
		
	}


	private void updateContent() {
		
		myContents();

		System.out.print("수정할 게시글번호를 입력해주세요 >> ");
		String boardNo = sc.nextLine();
		 
		System.out.println("--------------------------------------");
		
		
		System.out.print("제목 변경 >> ");
		String boardTitle = sc.nextLine();
		
		System.out.print("내용 변경 >> ");
		String boardContent = sc.nextLine();
		
		BoardVO bv2 = new BoardVO();
		bv2.setBoardNo(boardNo);
		bv2.setBoardTitle(boardTitle);
		bv2.setBoardContent(boardContent);
		
		int cnt = boardService.editContent(bv2);
		if (cnt > 0) {
			System.out.println(boardNo+"번 게시글 수정 완료!");
		}else {
			System.out.println(boardNo+"번 게시글 수정 실패!");
		}
	}


	private void insertContent() {

		sc.nextLine();
		
		System.out.println("--------------------------------------");
		System.out.print("작성할 게시글 제목을 입력해주세요.");
		String boardTitle = sc.next();
		
		System.out.print("작성자를 입력해주세요.");
		String boardWriter = sc.next();
		
		System.out.print("작성할 내용을 입력해주세요.");
		String boardContent = sc.next();
		System.out.println("--------------------------------------");
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardService.writeContent(bv);
		
		if (cnt > 0) {
			System.out.println(boardWriter +"님의 게시글 등록 성공 !");
		}else {
			System.out.println(boardWriter + "님의 게시글 등록 실패!");
		}
	}


	private void searchContent() {

		sc.nextLine();
		System.out.println("--------------------------------------");
		System.out.println("검색할 게시글 정보를 입력해주세요. (skip 가능)");
		System.out.println("게시글 번호 >> ");
		String boardNo = sc.nextLine().trim();
		
		System.out.println("게시글 제목 >> ");
		String boardTitle = sc.nextLine().trim();
		
		
		
		System.out.println("게시글 작성자 >> ");
		String boardWriter = sc.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		List<BoardVO> boardList = boardService.searchContents(bv);
		
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자"+"\t\t게시글 내용"+"\t\t작성일");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		for (BoardVO bv2 : boardList) {
			
			System.out.println(" "+bv2.getBoardNo()+"\t\t"+bv2.getBoardTitle()
							+"\t\t"+bv2.getBoardWriter()+"\t\t"+bv2.getBoardContent()+"\t\t"+bv2.getBoardDate());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
	}


	private void selectAllBoard() {

		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자\t\t작성일\t\t게시글내용");
		System.out.println("----------------------------------------------------------------------------------------------------");
		List<BoardVO> boardList = boardService.displayAllContents();
		
		for (BoardVO bv : boardList) {
			System.out.println(" "+bv.getBoardNo()+"\t\t"+bv.getBoardTitle()
								+"\t\t"+bv.getBoardWriter()+"\t\t"+bv.getBoardContent()+"\t\t"+bv.getBoardDate());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
	}

	private String myContents() {
		sc.nextLine();
		
		System.out.println("--------------------------------------");
		System.out.print("수정할 게시글의 작성자를 입력해주세요 >> ");
		String boardWriter = sc.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardWriter(boardWriter);
		List<BoardVO> boardList = boardService.selectByWriter(bv);
		
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println(" 게시글번호\t\t게시글제목\t\t게시글작성자"+"\t\t게시글 내용"+"\t\t작성일");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		for (BoardVO bv2 : boardList) {
			
			System.out.println(" "+bv2.getBoardNo()+"\t\t"+bv2.getBoardTitle()
							+"\t\t"+bv2.getBoardWriter()+"\t\t"+bv2.getBoardContent()+"\t\t"+bv2.getBoardDate());
		}
		System.out.println("----------------------------------------------------------------------------------------------------");
		return bv.getBoardWriter();
	}

	public static void main(String[] args) {
		
		new BoardMain().start();
		
	}
	
	

}
