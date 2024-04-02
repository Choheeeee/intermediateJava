package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MyBatisUtil;

public class MemberDaoImplWithMyBatis implements IMemberDao {
	
	private static IMemberDao memDao;
	
	private MemberDaoImplWithMyBatis() {}
	
	public static IMemberDao  getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImplWithMyBatis();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			cnt = session.insert("member.insertMember", mv);
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			int cnt = session.selectOne("member.checkMember",memId);
			
			if (cnt > 0) {
				isExist = true;
			}	//else는 필요 없음 : isExist 디폴트가 false이므로(존재할때만 true로 바꿔주면 됨)
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		try {
			//update()메서드의 반환값도 성공한 레코드 수이다.
			cnt = session.update("member.updateMember", mv);
			if (cnt > 0) {
				System.out.println("update 작업" +cnt+ "개 성공!");
				session.commit();	//auto커밋을 false로 꺼놨으므로 반드시 명시적으로 commit()해주기!!
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.delete("member.deleteMember", "d001");
			if (cnt > 0) {
				session.commit();
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		
		List<MemberVO> memList = new ArrayList<>();
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			memList = session.selectList("member.selectAllMember");
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		
		try {
			memList = session.selectList("member.searchMember", mv);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return memList;
	}

}
