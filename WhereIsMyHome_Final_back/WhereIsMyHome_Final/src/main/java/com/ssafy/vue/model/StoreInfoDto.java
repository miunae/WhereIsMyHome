package com.ssafy.vue.model;

public class StoreInfoDto {
	private String storeName;
	private String storeSectorName;
	private String storeAddr; // 지번
	private String storeNAddr; // 도로명주소
	private String storeLng; // 경도
	private String storeLat; // 위도
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreSectorName() {
		return storeSectorName;
	}
	public void setStoreSectorName(String storeSectorName) {
		this.storeSectorName = storeSectorName;
	}
	public String getStoreAddr() {
		return storeAddr;
	}
	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	public String getStoreNAddr() {
		return storeNAddr;
	}
	public void setStoreNAddr(String storeNAddr) {
		this.storeNAddr = storeNAddr;
	}
	public String getStoreLng() {
		return storeLng;
	}
	public void setStoreLng(String storeLng) {
		this.storeLng = storeLng;
	}
	public String getStoreLat() {
		return storeLat;
	}
	public void setStoreLat(String storeLat) {
		this.storeLat = storeLat;
	}
	@Override
	public String toString() {
		return "StoreInfoDto [storeName=" + storeName + ", storeSectorName=" + storeSectorName + ", storeAddr="
				+ storeAddr + ", storeNAddr=" + storeNAddr + ", storeLng=" + storeLng + ", storeLat=" + storeLat + "]";
	}

	
	
}
