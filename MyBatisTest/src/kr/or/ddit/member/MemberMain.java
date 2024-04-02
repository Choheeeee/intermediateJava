package kr.or.ddit.member;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    reg_dt DATE DEFAULT sysdate, -- 등록일
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class MemberMain {	//MemberMain은 View와 Controller역할
	
	private IMemberService memberService;
	private Scanner scan;
	
	public MemberMain() {
		
	memberService = MemberServiceImpl.getInstance();
	scan = new Scanner(System.in);
	
	}
	
	

	//메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 자료 검색");
		System.out.println("  6. 작업 끝");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	//프로그램 시작 메서드
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayAllMember();
					break;
				case 5 :  // 자료검색
					serchMember();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	
	
	
	
	/*
	 	- 회원정보를 검색하기 위한 메서드
		- 검색할 회원ID, 회원이름, 전화번호, 주소등을 입력하면 입력한 정보만 사용하여 검색하는 기능을 구현하시오
		- 주소는 입력한 값이 포함만 되어도 검색되도록 한다.
		- 입력을 하지 않을 컬럼은 엔터키로 다음 입력으로 넘긴다.
	 */
	private void serchMember() {
		scan.nextLine();	//입력버퍼 비우기
		
		System.out.println();
		System.out.println("검색할 회원정보를 입력하세요.");
		System.out.println("회원ID >> ");
		String memId = scan.nextLine().trim();	//아무것도 입력안하고 엔터로 스킵할 수 있기때문에, 라인단위로 받도록
		
		System.out.println("회원 이름 >> ");
		String memName = scan.nextLine().trim();
		
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.nextLine().trim();
		
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine().trim();
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		List<MemberVO> memList = memberService.searchMemberVO(mv);
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("-------------------------------------------------");
		
		for (MemberVO mv2 : memList) {
			
			System.out.println(" "+mv2.getRegDt()+"\t"+mv2.getMemId()+"\t"+mv2.getMemName()+"\t"+mv2.getMemTel()+"\t"+mv2.getMemAddr());
		}
		System.out.println("-------------------------------------------------");
		
	}



	//모든 회원정보 출력을 위한 메서드
	private void displayAllMember() {
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("-------------------------------------------------");
		List<MemberVO> memList = memberService.displayAllMember();
		
		for (MemberVO mv : memList) {
			
			System.out.println(" "+mv.getMemId()+"\t"+mv.getMemName()+"\t"+mv.getMemTel()+"\t"+mv.getMemAddr());
		}
		System.out.println("-------------------------------------------------");
	}

	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		int cnt = memberService.removeMember(memId);
		if (cnt > 0) {
			System.out.println(memId+"의 회원정보 삭제 성공!");
		}else {
			System.out.println(memId+"의 회원정보 삭제 실패!!");
		}
		
	}

	//회원정보를 수정하기 위한 메서드
	private void updateMember() {

		boolean isExist = false;
		
		String memId = "";
		
		do {
			
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			isExist = memberService.checkMember(memId);	//루프를 탈출하게 해줌(플래그 변수)
			
			if (!isExist) {	//isExist가 true로 바뀌면
				System.out.println(memId+"은 등록되지 않은 회원입니다. 다시 입력해주세요.");
			}
			
		}while (!isExist);	//회원정보가 이미 존재하지 않으면 무한루프
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 지우기
		
		System.out.print("회원주소>> ");
		String memAddr = scan.nextLine();	//여기까지가 사용자한테 입력받을 준비 
			
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		int cnt = memberService.modifyMember(mv);	//쿼리문 실행후 반환해주는 성공된 정수값
		if (cnt > 0 ) {	//업데이트 성공
			System.out.println(memId+"의 회원정보 수정 성공!!");
		}else {
			System.out.println(memId+"의 회원정보 수정 실패!!!");
		}
			
	}

	/*
	 * 	회원정보를 추가하기 위한 메서드
	 */
	private void insertMember() {

		
		boolean isExist = false;	//플래그변수 선언
		/*
		 * 플래그 변수(Flag Variable)는 프로그램에서 특정 조건이나 상황을 나타내기 위해 사용되는 변수입니다
		 * 이 변수는 일반적으로 불리언 데이터 타입 (예: boolean)을 사용하며, 
		 * 두 가지 값 중 하나만 가질 수 있는 변수입니다: true 또는 false.
		 * 플래그 변수는 프로그램의 흐름 제어 및 조건부 실행에 사용됩니다
		 */
		
		String memId = "";
		
		do {
			
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			isExist = memberService.checkMember(memId);	//루프를 탈출하게 해줌
			
			if (isExist) {	//isExist가 true로 바뀌면
				System.out.println(memId+"인 회원은 이미 존재합니다. 다시 입력해 주세요!");
			}
			
		}while (isExist);	//회원정보가 이미 존재하면 무한루프, 아닐때 멈춤
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 지우기
		
		System.out.print("회원주소>> ");
		String memAddr = scan.nextLine();	//여기까지가 사용자한테 입력받을 준비 
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
		int cnt = memberService.registerMember(mv);
		
		if (cnt > 0) {
			System.out.println(memId+"인 회원 추가작업 성공!!");
		}else {
			System.out.println(memId+"인 회원 추가작업 실패!!");
		}
		
	}
		

	
	public static void main(String[] args) {
		MemberMain memObj = new MemberMain();
		memObj.start();
	}

}






