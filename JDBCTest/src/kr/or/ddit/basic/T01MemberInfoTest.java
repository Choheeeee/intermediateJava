package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.or.ddit.util.JDBCUtil3;

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
public class T01MemberInfoTest {
	
	//SQL을 위한 로그객체담을 상수변수 만들기
	private static final Logger SQL_LOGGER = LogManager.getLogger("log4jexam.sql.Query");
	
	//파라미터를 위한 로그객체 만들기
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam.sql.Parameter");
	
	//결과를 위한 로그객체 만들기
	private static final Logger RESULT_LOGGER = LogManager.getLogger(T01MemberInfoTest.class);
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
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
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	//모든 회원정보 출력을 위한 메서드
	private void displayAllMember() {
		
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("-------------------------------------------------");
		try {
			conn = JDBCUtil3.getConnection();	//Connection객체를 생성하려면 드라이버 로딩을 선행해야함
			
			stmt = conn.createStatement();	//Statement객체 생성 하는 부분 (Statement객체를 얻으려면 Connection객체가 있어야 한다. 위에서 꼭 선행 돼야함)
			
			rs = stmt.executeQuery("select * from mymember");
			
			while (rs.next()) {
				String memID = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(" "+memID+"\t"+memName+"\t"+memTel+"\t"+memAddr);
			}
			System.out.println("-------------------------------------------------");
			System.out.println("전체 회원정보 출력 완료");
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memID = scan.next();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memID);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memID+"의 회원정보 삭제 성공!");
			}else {
				System.out.println(memID+"의 회원정보 삭제 실패!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
	}

	//회원정보를 수정하기 위한 메서드
	private void updateMember() {

		boolean isExist = false;
		
		String memID = "";
		
		do {
			
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			memID = scan.next();
			
			isExist = checkMember(memID);	//루프를 탈출하게 해줌(플래그 변수)
			
			if (!isExist) {	//isExist가 true로 바뀌면
				System.out.println(memID+"은 등록되지 않은 회원입니다. 다시 입력해주세요.");
			}
			
		}while (!isExist);	//회원정보가 이미 존재하지 않으면 무한루프
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 지우기
		
		System.out.print("회원주소>> ");
		String memAddr = scan.nextLine();	//여기까지가 사용자한테 입력받을 준비 
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " update mymember "
					+" set mem_name = ? "
					+ " , mem_tel = ? "
					+ " , mem_addr = ? " 
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memID);
			
			int cnt = pstmt.executeUpdate();	//쿼리문 실행후 반환해주는 성공된 정수값
			
			if (cnt > 0 ) {	//업데이트 성공
				System.out.println(memID+"의 회원정보 수정 성공!!");
			}else {
				System.out.println(memID+"의 회원정보 수정 실패!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
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
		
		String memID = "";
		
		do {
			
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			memID = scan.next();
			
			isExist = checkMember(memID);	//루프를 탈출하게 해줌
			
			if (isExist) {	//isExist가 true로 바뀌면
				System.out.println(memID+"인 회원은 이미 존재합니다. 다시 입력해 주세요!");
			}
			
		}while (isExist);	//회원정보가 이미 존재하면 무한루프, 아닐때 멈춤
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 지우기
		
		System.out.print("회원주소>> ");
		String memAddr = scan.nextLine();	//여기까지가 사용자한테 입력받을 준비 
		
		
		//JDBC 코딩 시작
		try {
			conn = JDBCUtil3.getConnection();	//오라클 접속을 위한 connection객체 가져오기
			
			//새로운 회원 정보를 데이터베이스에 삽입하는 쿼리를 정의
			String sql = " INSERT INTO mymember (mem_id, mem_name, mem_tel, mem_addr) "
							+ " VALUES (?, ?, ?, ?)  ";
			
			//sysout을 통해 찍어도 되지만, 레벨을 다르게 해서 로그를 남길 수 있음(SQL은 info레벨)
			SQL_LOGGER.info("SQL => " + sql);
			
			//PreparedStatement 객체를 생성하고 SQL 쿼리를 이 객체에 로드함
			pstmt = conn.prepareStatement(sql);
			
			//setString(int parameterIndex, String x)
			//parameterIndex : SQL 문에서 몇 번째 파라미터(물음표)에 해당하는지를 나타냄. 첫 번째 파라미터는 1부터 시작
			//x : 해당인덱스 물음표에 설정하려는 문자열
			pstmt.setString(1, memID);	//첫번째 물음표에 memID변수에 저장된 문자열을 넣는다.
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			//여기까지 이렇게 설정된 PreparedStatement 객체를 사용하여 SQL 쿼리를 실행하면, 
			//물음표로 파라미터가 설정된 위치에 실제 데이터가 대체되어 데이터베이스에 데이터가 삽입됨.
			
			
			PARAM_LOGGER.debug("PARAMETER => "
									+ "(memId : "+memID+", memName : "+memName
									+ ", memTel : "+memTel+", memAddr : "+memAddr+")");
			
			
			//PreparedStatement 객체의 executeUpdate()를 호출하여 SQL 쿼리를 실행함
			//int cnt : 실행된 SQL 쿼리에 의해 영향을 받은 행의 수가 이 변수에 저장됨. 
			//즉, 데이터베이스에 추가, 수정 또는 삭제된 행의 수가 정수로 반환돼 변수에 저장됨.
			int cnt = pstmt.executeUpdate();	//CRUD 작업중 반환값이 정수인것은 executeUpdate()
												//select만 반환값이 ResultSet이고, executeQuerty()메서드
			
			
			RESULT_LOGGER.info("결과 값(cnt) => "+cnt);
			
			
			
			//위의 SQL문을 실행하면 입력받은 새 회원 정보를 데이터베이스에 추가하고, 
			//cnt 변수에 추가된 회원의 수가 저장됩니다. 이를 통해 작업의 성공 여부를 확인할 수 있음. 
			//만약 cnt가 1보다 크면 (영향을 받은 행이 하나 이상) 즉, 작업이 성공한 것으로 간주
			if (cnt > 0) {
				System.out.println(memID+"인 회원 추가작업 성공!!");
			}else {
				System.out.println(memID+"인 회원 추가작업 실패!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {	//자원반납을 해주지 않으면, 자원을 계속 점유중이므로 오라클서버에서 문제가 발생 할 수 있음	
			
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	
	
	/*
	 *	회원ID를 이용하여 회원이 존재하는지 체크하기 위한 메서드 
	 * 	memID 회원존재여부를 체크하기 위한 회원ID
	 *	존재하면 true, 존재하지 않으면 false
	 */
	private boolean checkMember(String memID) {
		boolean isExist = false;
		try {
			conn = JDBCUtil3.getConnection();
			String sql =" select count(*) as cnt from mymember " +" where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memID);
			
			rs = pstmt.executeQuery();	//select는 executeQuery()이용
			
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if (cnt >0) {	//존재할때
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
		T01MemberInfoTest memObj = new T01MemberInfoTest();
		memObj.start();
	}

}






