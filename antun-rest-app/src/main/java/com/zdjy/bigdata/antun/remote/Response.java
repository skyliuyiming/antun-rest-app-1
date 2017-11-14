package com.zdjy.bigdata.antun.remote;

import com.google.gson.Gson;

public class Response {
	private Integer code;
	private String result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
