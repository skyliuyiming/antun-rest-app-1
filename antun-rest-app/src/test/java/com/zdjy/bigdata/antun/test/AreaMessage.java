package com.zdjy.bigdata.antun.test;

public class AreaMessage {
	private Integer areaId;
	private String areaName;
	private Integer level;
	private Integer parentAreaId;
	private String fullPinYin;
	private String shortPinYin;
	
	
	public Integer getAreaId() {
		return areaId;
	}


	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public Integer getParentAreaId() {
		return parentAreaId;
	}


	public void setParentAreaId(Integer parentAreaId) {
		this.parentAreaId = parentAreaId;
	}


	public String getFullPinYin() {
		return fullPinYin;
	}


	public void setFullPinYin(String fullPinYin) {
		this.fullPinYin = fullPinYin;
	}


	public String getShortPinYin() {
		return shortPinYin;
	}


	public void setShortPinYin(String shortPinYin) {
		this.shortPinYin = shortPinYin;
	}


	@Override
	public String toString() {
		return  areaId + "," + areaName + "," + level + ","
				+ parentAreaId + "," + fullPinYin + "," + shortPinYin ;
	}
	
	
}
