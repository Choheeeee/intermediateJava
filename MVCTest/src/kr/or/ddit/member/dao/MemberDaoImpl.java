package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;


//각 메서드마다 각각 Connection하는 이유 & 전역으로 변수만 선언하고 객체생성은 각 메서드에서 따로따로 하는 이유 : 해당 메서드가 실행될때 각 메서드별로 연결시켰다가, 다시 close하는 패턴으로 해야 오라클 회선이 물려있지 않음.
public class MemberDaoImpl implements IMemberDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();	//오라클 접속을 위한 connection객체 가져오기
			
			//새로운 회원 정보를 데이터베이스에 삽입하는 쿼리를 정의
			String sql = " INSERT INTO mymember (mem_id, mem_name, mem_tel, mem_addr) "
							+ " VALUES (?, ?, ?, ?)  ";
			
			//PreparedStatement 객체를 생성하고 SQL 쿼리를 이 객체에 로드함
			pstmt = conn.prepareStatement(sql);
			
			//setString(int parameterIndex, String x)
			//parameterIndex : SQL 문에서 몇 번째 파라미터(물음표)에 해당하는지를 나타냄. 첫 번째 파라미터는 1부터 시작
			//x : 해당인덱스 물음표에 설정하려는 문자열
			pstmt.setString(1, mv.getMemId());	//첫번째 물음표에 memID변수에 저장된 문자열을 넣는다.
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			//여기까지 이렇게 설정된 PreparedStatement 객체를 사용하여 SQL 쿼리를 실행하면, 
			//물음표로 파라미터가 설정된 위치에 실제 데이터가 대체되어 데이터베이스에 데이터가 삽입됨.
			
			
		
			//PreparedStatement 객체의 executeUpdate()를 호출하여 SQL 쿼리를 실행함
			//int cnt : 실행된 SQL 쿼리에 의해 영향을 받은 행의 수가 이 변수에 저장됨. 
			//즉, 데이터베이스에 추가, 수정 또는 삭제된 행의 수가 정수로 반환돼 변수에 저장됨.
			cnt = pstmt.executeUpdate();	//CRUD 작업중 반환값이 정수인것은 executeUpdate()
												//select만 반환값이 ResultSet이고, executeQuerty()메서드
			
			
			//위의 SQL문을 실행하면 입력받은 새 회원 정보를 데이터베이스에 추가하고, 
			//cnt 변수에 추가된 회원의 수가 저장됩니다. 이를 통해 작업의 성공 여부를 확인할 수 있음. 
			//만약 cnt가 1보다 크면 (영향을 받은 행이 하나 이상) 즉, 작업이 성공한 것으로 간주
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {	//자원반납을 해주지 않으면, 자원을 계속 점유중이므로 오라클서버에서 문제가 발생 할 수 있음	
			
			/*
			 	- null이 아니란 의미는 해당 리소스가 초기화되었거나 할당되었음을 의미함.
			 	- 즉, 이 변수들이 null이 아니라는 것은 해당 자원이 생성되었고 사용 중이라는 것을 의미
			 	- (변수가 null인 경우에는 해당 자원이 아직 초기화되지 않았거나 사용되지 않았음을 나타냄. 따라서 자원을 닫는 작업은 필요하지 않음)
			 	- 만약 자원을 사용한 후에도 null이라면, 이것은 자원이 정상적으로 초기화되지 않았거나 
			 		예외가 발생하여 자원을 할당하지 못한 경우를 나타낼 수 있음.
			 		
			 	if (rs != null) try {rs.close();} catch (SQLException e2) {}
			 	if (stmt != null) try {stmt.close();} catch (SQLException e2) {}
			 	if (pstmt != null) try {pstmt.close();} catch (SQLException e2) {}
			 	if (conn != null) try {conn.close();} catch (SQLException e2) {}
			 */
			
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql =" select count(*) as cnt from mymember " +" where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();	//select는 executeQuery()이용
			
			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("CNT");
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

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " update mymember "
					+" set mem_name = ? "
					+ " , mem_tel = ? "
					+ " , mem_addr = ? " 
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();	//쿼리문 실행후 반환해주는 성공된 정수값
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
		
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		List<MemberVO> memList = new ArrayList<>();
		try {
			conn = JDBCUtil3.getConnection();	//Connection객체를 생성하려면 드라이버 로딩을 선행해야함
			
			stmt = conn.createStatement();	//Statement객체 생성 하는 부분 (Statement객체를 얻으려면 Connection객체가 있어야 한다. 위에서 꼭 선행 돼야함)
			
			rs = stmt.executeQuery("select * from mymember");
			
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv = new MemberVO();
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {

		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "select * from mymember where 1=1 ";	//다이나믹 쿼리
			if (mv.getMemId() != null && !mv.getMemId().equals("")) {	//memId에 뭔가를 입력했다는 의미
				sql += "and mem_id = ? ";
			}
			if (mv.getMemName() != null && !mv.getMemName().equals("")) {	//memName에 뭔가를 입력했다는 의미
				sql += "and mem_name = ? ";
			}
			if (mv.getMemTel() != null && !mv.getMemTel().equals("")) {	//memTel에 뭔가를 입력했다는 의미
				sql += "and mem_tel = ? ";
			}
			if (mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {	//memAddr에 뭔가를 입력했다는 의미
				sql += "and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			//물음표 위치 인덱스
			int index = 1;
			if (mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());	//바뀌는 물음표위치 인덱스를 표현하기 위해 index에 후위 증감연산자
			}
			if (mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if (mv.getMemTel() != null && !mv.getMemTel().equals("")) {	
				pstmt.setString(index++, mv.getMemTel());
			}
			if (mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(memId);
				mv2.setMemName(memName);
				mv2.setMemTel(memTel);
				mv2.setMemAddr(memAddr);
				mv2.setRegDt(rs.getTimestamp("reg_dt").toLocalDateTime().toLocalDate());
				
				memList.add(mv2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}


}
