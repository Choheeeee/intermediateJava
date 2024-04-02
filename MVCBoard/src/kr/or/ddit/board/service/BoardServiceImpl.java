package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{

	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public int writeContent(BoardVO bv) {
		int cnt = boardDao.insertContent(bv); 
		return cnt;
	}

	@Override
	public int editContent(BoardVO bv) {
		int cnt = boardDao.updateContent(bv);
		return cnt;
	}

	@Override
	public int deleteContetn(String boardNo) {
		int cnt = boardDao.deleteContent(boardNo);
		return cnt;
	}

	@Override
	public List<BoardVO> displayAllContents() {
		List<BoardVO> boardList = boardDao.selectAllBoard();
		return boardList;
	}


	@Override
	public List<BoardVO> selectByWriter(BoardVO bv) {
		List<BoardVO> boardList = boardDao.selectByWriter(bv);
		return boardList;
	}

	@Override
	public List<BoardVO> searchContents(BoardVO bv) {
		List<BoardVO> boardList = boardDao.searchBoard(bv);
		return boardList;
	}

	
}
