package com.zdjy.bigdata.antun.web.model;

/**
 * 页面新增类
 * @author david
 * @create 2017年11月15日 下午4:38:33
 */
public class PageAdd {
	private String description;
	private String fileName;
	private String productCode;
	private Integer platform;
	
	
	//辅助字段
	private String productName;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
