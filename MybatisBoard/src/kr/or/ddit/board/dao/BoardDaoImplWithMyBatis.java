package kr.or.ddit.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.dao.vo.BoardVO;
import kr.or.ddit.util.MyBatisUtil;

public class BoardDaoImplWithMyBatis implements IBoardDao {
	
	private static IBoardDao boardDao;
	
	private BoardDaoImplWithMyBatis() {}
	
	public static IBoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImplWithMyBatis();
		}return boardDao;
	}
	
	
	
	@Override
	public int insertContent(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			cnt = session.insert("board.insertContent", bv);
			session.commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateContent(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			cnt = session.update("board.updateContent", bv);
			session.commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteContent(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
		cnt = session.delete("board.deleteContent", bv);
		session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectAllBoard() {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		List<BoardVO> boardList =  new ArrayList<>();
		try {
			boardList = session.selectList("board.selectAllBoard");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = session.selectList("board.searchBoard", bv);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> selectByWriter(BoardVO bv) {
		 
		SqlSession session = MyBatisUtil.getInstance(true);
		
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = session.selectList("board.selectByWriter", bv);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return boardList;
	}

}
