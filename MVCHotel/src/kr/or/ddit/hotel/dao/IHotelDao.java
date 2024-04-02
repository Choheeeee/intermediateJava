package kr.or.ddit.hotel.dao;

import java.util.Map;

import kr.or.ddit.hotel.dao.vo.HotelVO;

public interface IHotelDao {

	
	public boolean isAlreadyInUse(int roomNo);
	
	public int insertRoom(HotelVO hv);
	
	public int deleteRoom(HotelVO hv);
	
	public int updateRoom(HotelVO hv);
	
	public Map<Integer, HotelVO> selectAllRoom();
	
	public  HotelVO selectOne(HotelVO hv);

}
