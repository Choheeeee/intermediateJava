package kr.or.ddit.hotel.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.hotel.dao.vo.HotelVO;
import kr.or.ddit.util.MyBatisUtil;

public class HotelDaoImpl implements IHotelDao {

	private static IHotelDao instance;
	
	private HotelDaoImpl() {}
	
	public static IHotelDao getInstance() {
		
		if (instance == null) {
			instance = new HotelDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public boolean isAlreadyInUse(int roomNo) {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		boolean isInUse = false;
		
		try {
			HotelVO hv = session.selectOne("hotel", roomNo);
		if (hv != null) {
			isInUse = true;
		}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return isInUse;
	}

	@Override
	public int insertRoom(HotelVO hv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;
		
		try {
			cnt = session.insert("hotel", hv);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteRoom(HotelVO hv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;
		
		try {
			cnt = session.delete("hotel",hv);
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateRoom(HotelVO hv) {
		
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;
		
		try {
			cnt = session.update("hotel", hv);
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public Map<Integer, HotelVO> selectAllRoom() {
		
		Map<Integer, HotelVO> hotelMap = new HashMap<>();
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			hotelMap = session.selectMap("hotel", "roomNo");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return hotelMap;
	}

	@Override
	public HotelVO selectOne(HotelVO hv) {
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		HotelVO hotel = null;
		try {
			hotel = session.selectOne("hotel", hv);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return hotel;
		
	
	};

}
