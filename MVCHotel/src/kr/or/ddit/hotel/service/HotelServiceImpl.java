package kr.or.ddit.hotel.service;

import java.util.Map;

import kr.or.ddit.hotel.dao.HotelDaoImpl;
import kr.or.ddit.hotel.dao.IHotelDao;
import kr.or.ddit.hotel.dao.vo.HotelVO;

public class HotelServiceImpl implements IHotelService{
	
	private IHotelDao hotelDao;
	private static IHotelService instance;
	
	private HotelServiceImpl() {
		
		hotelDao = HotelDaoImpl.getInstance(); 
	}
	
	public static IHotelService getInstance() {
		if (instance == null) {
			instance = new HotelServiceImpl();
		}return instance;
	}

	@Override
	public boolean isAlreadyInUse(int roomNo) {
		return hotelDao.isAlreadyInUse(roomNo);
	}

	@Override
	public int checkIn(HotelVO hv) {
		int cnt = hotelDao.insertRoom(hv);
		return cnt;
	}

	@Override
	public int checkOut(HotelVO hv) {
		int cnt = hotelDao.deleteRoom(hv);
		return cnt;
	}

	@Override
	public int changeInfo(HotelVO hv) {
		int cnt = hotelDao.updateRoom(hv);
		return cnt;
	}

	@Override
	public Map<Integer, HotelVO> displayAllRoom() {
		Map<Integer, HotelVO> hotelMap = hotelDao.selectAllRoom();
		return hotelMap;
	}

	@Override
	public HotelVO search(HotelVO hv) {
		HotelVO hotel = hotelDao.selectOne(hv);
		return hotel;
	}

}
