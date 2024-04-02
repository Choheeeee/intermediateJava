package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.vo.BoardVO;

public interface IBoardService {

	/**
	 * 게시글 작성을 위한 메서드
	 * @param Dao에 넘겨줄 데이터를 담은 BoardVO객체
	 * @return 
	 */
	public int writeContent(BoardVO bv);
	
	
	public int editContent(BoardVO bv);
	
	
	public int deleteContetn(String boardNo);
	
	
	public List<BoardVO> displayAllContents();
	
	public List<BoardVO> searchContents(BoardVO bv);
	
	public List<BoardVO> selectByWriter(BoardVO bv);
}
