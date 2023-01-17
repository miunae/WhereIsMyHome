package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.StoreInfoDto;
import com.ssafy.vue.model.mapper.StoreMapper;
@Service
public class StoreInfoServiceImpl implements StoreInfoService {

	
	// 생성자
	private StoreMapper storeInfoDao;
	
	@Autowired
	public StoreInfoServiceImpl(StoreMapper storeInfoDao) {
		super();
		this.storeInfoDao = storeInfoDao;
	}
	
	@Override
	public List<StoreInfoDto> searchStore(String storeAddr) throws SQLException {
		return storeInfoDao.searchStore(storeAddr);
	}
}
