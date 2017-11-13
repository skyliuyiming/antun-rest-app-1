package com.zdjy.bigdata.antun.web.response;
/**
 * 基本返回值类
 * @author david
 * @create 2017年10月28日 下午5:39:00
 */
public class BaseResponse {
	private Integer code;
	private String message;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public BaseResponse code(Integer code) {
		this.code = code;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BaseResponse message(String message) {
		this.message = message;
		return this;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public BaseResponse data(Object data) {
		this.data = data;
		return this;
	}
	public BaseResponse successModel() {
		return new BaseResponse().code(200);
	}
	public BaseResponse successModel(String message) {
		return new BaseResponse().code(200).message(message);
	}
	public BaseResponse errorModel() {
		return new BaseResponse().code(400);
	}
	public BaseResponse errorModel(String message) {
		return new BaseResponse().code(400).message(message);
	}
}
