package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.model.StoreInfoDto;

public interface StoreInfoService {

	List<StoreInfoDto> searchStore(String storeAddr) throws SQLException;
	
}
