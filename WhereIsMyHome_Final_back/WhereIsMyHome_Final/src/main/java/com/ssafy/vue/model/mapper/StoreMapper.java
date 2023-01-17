package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.StoreInfoDto;

@Mapper
public interface StoreMapper {

	List<StoreInfoDto> searchStore(String storeAddr) throws SQLException;

}
