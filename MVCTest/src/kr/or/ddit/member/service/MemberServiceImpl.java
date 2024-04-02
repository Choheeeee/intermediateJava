package kr.or.ddit.member.service;
//뷰가 서비스(=컨트롤러)에 요청을 하면 서비스는 Dao(=모델)에 db를 요청하고, Dao는 db작업 결과를 service에 반환하고, service는 또 view에 반환함
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dao.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao memDao;
	
	public MemberServiceImpl() {	//MemberServiceImpl 객체가 생성되는 시점에 memDao도 같이 생성시키려고
		memDao = new MemberDaoImpl();
	}

	
	@Override
	public int registerMember(MemberVO mv) {

		//회원정보 DB등록
		//회원등록 이력 정보 남기기
		//등록된 회원 이메일로 메일발송 처리
		int cnt = memDao.insertMember(mv);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		return memDao.checkMember(memId);
	}

	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		return cnt;
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		return cnt;
	}

	@Override
	public List<MemberVO> displayAllMember() {
		List<MemberVO> memList = memDao.selectAllMember();
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMember(mv);
		return memList;
	}

}
