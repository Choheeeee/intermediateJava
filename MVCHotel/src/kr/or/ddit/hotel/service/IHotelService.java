package kr.or.ddit.hotel.service;

import java.util.Map;

import kr.or.ddit.hotel.dao.vo.HotelVO;

public interface IHotelService {
	
	public boolean isAlreadyInUse(int roomNo);
	
	public int checkIn(HotelVO hv);
	
	public int checkOut(HotelVO hv);
	
	public int changeInfo(HotelVO hv);
	
	public Map<Integer, HotelVO> displayAllRoom();
	
	public HotelVO search(HotelVO hv);
}
