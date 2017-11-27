package com.zdjy.bigdata.antun.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author david
 * @create 2017年11月27日 下午5:25:17
 */
public class DateUtil {
	private static final SimpleDateFormat SIMPLEDATEFORMAT=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 获取昨天的时间
	 * @return
	 */
	public static String getToday() {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		return SIMPLEDATEFORMAT.format(instance.getTime());
	}
	/**
	 * 获取昨天的时间
	 * @return
	 */
	public static String getLastDay() {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.DATE,-1);
		return SIMPLEDATEFORMAT.format(instance.getTime());
	}
	/**
	 * 获取最近一周的时间段
	 * @return
	 */
	public static String getLastWeek(){
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.DATE,-7);
		return SIMPLEDATEFORMAT.format(instance.getTime());
	}
	/**
	 * 获取最近一周的时间段
	 * @return
	 */
	public static String getLastMonth(){
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.DATE,-30);
		return SIMPLEDATEFORMAT.format(instance.getTime());
	}
}
