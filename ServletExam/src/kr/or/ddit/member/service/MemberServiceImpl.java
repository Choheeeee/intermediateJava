package kr.or.ddit.member.service;
//컨트롤러가 서비스에 요청을 하면 서비스는 Dao에 요청해하고 데이터를 주고 받음
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dao.MemberDaoImplWithMyBatis;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao memDao;
	
	private static IMemberService memService;
	
	private MemberServiceImpl() {	//MemberServiceImpl 객체가 생성되는 시점에 memDao도 같이 생성시키려고
		memDao = MemberDaoImplWithMyBatis.getInstance();
	}
	
	public static IMemberService getInstance() {
		if (memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
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
	public List<MemberVO> searchMemberVO(MemberVO mv) {
		List<MemberVO> memList = memDao.searchMember(mv);
		return memList;
	}

}
