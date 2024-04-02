package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.dao.vo.BoardVO;

public interface IBoardDao {
	
	/**
	 * BoardVO에 담겨진 데이터를 DB에 insert하기 위한 메서드
	 * @param bv DB에 등록할 데이터를 담은 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0을 반환
	 */
	public int insertContent(BoardVO bv);
	
	
	/**
	 * bv BoardVO에 담겨진 데이터를 DB에 update하기 위한 메서드
	 * @param bv DB에 등록할 데이터를 담은 BoardVO객체
	 * @return DB작업에 성공하면 1이 반환되고, 실패하면 0을 반환
	 */
	public int updateContent(BoardVO bv);
	
	
	/**
	 * 게시글을 delete하기 위한 메서드
	 * @param boardNo 게시글을 삭제할때 필요한 게시글번호
	 * @return DB작업에 성공하면 1이 반환되고, 실패하면 0을 반환
	 */
	public int deleteContent(String boardNo);
	
	
	/*
	 * DB테이블에 존재하는 모든 회원정보를 가져오기 위한 메서드 = 전체조회
	 * 게시글정보를 담은 List
	 */
	public List<BoardVO> selectAllBoard();
	
	
	//서치하면 여러 검색별과가 나올 수 있으므로 List로 반환타입 지정
	//DB테이블에 존재하는 회원정보중에서 검색조건에 해당하는 회원정보를 가져오기 위한 메서드 
	//bv파라미터는 데이터를 담은 BoardVO객체
	public List<BoardVO> searchBoard(BoardVO bv);
	
	
	public List<BoardVO> selectByWriter(BoardVO bv);
	
	
}
