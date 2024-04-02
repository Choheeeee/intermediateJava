package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.dao.vo.MemberVO;

/*
 	실제 DB에 연결해서 SQL문을 수행하여 결과를 받아 Service에 전달하는 DAO(Database Access Object) 인터페이스
 */
public interface IMemberDao {
	
	/*
	 	- MemberVO에 담겨진 데이터를 DB에 insert하기 위한 메서드
	 	- mv DB에 등록할 데이터를 담은 MemberVO객체
	 	- DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0을 반환한다.
	 */
	public int insertMember(MemberVO mv);
	
	
	
	
	/*
	 	- DB에 해당 회원이 존재하는지 체크하기 위한 메서드
	 	- memId 존재여부 체크할 회원 ID
	 	- 해당 회원이 존재하면 true, 없으면 false 반환
	 */
	public boolean checkMember(String memId);
	
	
	
	
	/*
 	MemberVO에 담겨진 데이터를 DB에 update하기 위한 메서드
 	mv DB에 등록할 데이터를 담은 MemberVO객체
 	DB작업이 성공하면 1이 반환되고, 실패하면 0을 반환한다.
 */
	public int updateMember(MemberVO mv);
	
	
	
	/*
	 	회원정보를 삭제하기 위한 메서드
	 	memId 회원정보를 삭제할 회원 ID
	 	삭제 성공시 1, 실패시 0 반환
	 */
	public int deleteMember(String memId);
	
	
	
	
	
	/*
	 	DB테이블에 존재하는 모든 회원정보를 가져오기 위한 메서드 - 정적쿼리객체 이용
	 	회원정보를 담은 List객체
	 */
	public List<MemberVO> selectAllMember();
	
	
	
	
	//서치하면 여러명이 검색 결과로 나올수 있으므로 List로 반환타입 지정
	//DB테이블에 존재하는 회원정보중에서 검색조건에 해당하는 회원정보를 가져오기 위한 메서드(mv파라미터는 검색조건을 담은 MemberVO객체)
	public List<MemberVO> searchMember(MemberVO mv);
	
}

