package com.zdjy.bigdata.antun.web.response;

/**
 * 分页map
 * @author david
 * @create 2017年11月13日 上午10:49:58
 */
public class PageMap{
	private Object list;
	private Long count;
	public PageMap(Object list, Long count) {
		this.list = list;
		this.count = count;
	}
	public Object getList() {
		return list;
	}
	public void setList(Object list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
