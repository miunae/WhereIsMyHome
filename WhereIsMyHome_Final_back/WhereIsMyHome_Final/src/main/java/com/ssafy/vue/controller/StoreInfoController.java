package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.StoreInfoDto;
import com.ssafy.vue.model.service.StoreInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/store")
@Api("주변상권 컨트롤러  API V1")
public class StoreInfoController{

	private static final Logger logger = LoggerFactory.getLogger(StoreInfoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private StoreInfoService storeInfoService;
	
//	@GetMapping("/mvstore")
//	public String mvStore(@RequestParam Map<String, Object> map) {
//		return "store/store";
//	}
	
	@ApiOperation(value = "상권정보 목록", notes = "시도코드와 구군, 동 정보 기준으로 주변 상권시설 목록을 반환한다.", response = List.class)
	@GetMapping("/list/{sido}/{gugun}/{dong}")
	public ResponseEntity<?> storeList(@PathVariable("sido") String sido,
			@PathVariable("gugun") String gugun, @PathVariable("dong") String dong) throws Exception {
		logger.info("상권정보 리스트 - 호출");
		String storeAddr = sido + " " + gugun + " " + dong;
		List<StoreInfoDto> list= storeInfoService.searchStore(storeAddr);
		System.out.println("상권정보:"+list.get(0));
		System.out.println(list.get(0).toString());
		if(list.size() != 0) {
			return new ResponseEntity<List<StoreInfoDto>>(list, HttpStatus.OK);
		}
		else return new ResponseEntity<String>(FAIL , HttpStatus.NO_CONTENT); 
	}
}
