package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardDaoImpl implements IBoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {}
	
	public static IBoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImpl();
		}return boardDao;
	}
	
	
	
	@Override
	public int insertContent(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) " 
					+ " values (board_seq.nextval, ? , ? , sysdate, ? ) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int updateContent(BoardVO bv) {
		
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " update jdbc_board " + " set board_title = ?  ," + "  board_content = ? "
							+ " where board_no = ? ";
			if (bv.getBoardTitle() == null || bv.getBoardTitle().equals("")) {
				sql = sql.replace(" board_title = ? ,", "");
			}
			if (bv.getBoardContent() == null || bv.getBoardContent().equals("")) {
				sql = sql.replace(" board_content = ? ", "");
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			if (bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if (bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				pstmt.setString(index++, bv.getBoardContent());
			}
			pstmt.setString(3, bv.getBoardNo());
			
			cnt = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteContent(BoardVO bv) {
		
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " delete from jdbc_board "
						+ "where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardNo());
			
			cnt = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		
		List<BoardVO> boardList =  new ArrayList<>();
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String boardNo = rs.getString(1);
				String boardTitle = rs.getString(2);
				String boardWriter = rs.getString(3);
				String boardDate = rs.getString(4);
				String boardContent = rs.getString(5);
				
				BoardVO bv = new BoardVO();
				bv.setBoardNo(boardNo);
				bv.setBoardTitle(boardTitle);
				bv.setBoardWriter(boardWriter);
				bv.setBoardDate(boardDate);
				bv.setBoardContent(boardContent);
				
				boardList.add(bv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board where 1=1 ";
			
			if (bv.getBoardNo() != null && !bv.getBoardNo().equals("")) {
				sql += " and board_no = ? ";
			}
			if (bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				sql += " and board_title = ? ";
			}
			if (bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				sql += " and board_writer = ? ";
			}
//			if (date != null && !date.equals("")) {
//				sql += " and board_date = ? ";
//			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			if (bv.getBoardNo() != null && !bv.getBoardNo().equals("")) {
				pstmt.setString(index++, bv.getBoardNo());	//바뀌는 물음표위치 인덱스를 표현하기 위해 index에 후위 증감연산자
			}
			if (bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if (bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {	
				pstmt.setString(index++, bv.getBoardWriter());
			}
			
//			if (date != null && !date.equals("")) {
//				pstmt.setString(index++, date);
//			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String boardNo = rs.getString(1);
				String boardTitle = rs.getString(2);
				String boardWriter = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardDate = rs.getString(5);
				
				BoardVO bv2 = new BoardVO();
				bv2.setBoardNo(boardNo);
				bv2.setBoardTitle(boardTitle);
				bv2.setBoardWriter(boardWriter);
				bv2.setBoardContent(boardContent);
				bv2.setBoardDate(boardDate);
				
				boardList.add(bv2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> selectByWriter(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = " select * from jdbc_board " + " where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardWriter());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
	
				BoardVO bv2 = new BoardVO();
				bv2.setBoardNo(boardNo);
				bv2.setBoardTitle(boardTitle);
				bv2.setBoardWriter(boardWriter);
				bv2.setBoardDate(boardDate);
				bv2.setBoardContent(boardContent);
				
				boardList.add(bv2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}

		return boardList;
	}

}
