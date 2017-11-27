package com.zdjy.bigdata.antun.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Statistic;
import com.zdjy.bigdata.antun.domain.StatisticExample;
import com.zdjy.bigdata.antun.domain.StatisticExample.Criteria;
import com.zdjy.bigdata.antun.mapper.StatisticMapper;
import com.zdjy.bigdata.antun.service.StatisticService;
import com.zdjy.bigdata.antun.util.DateUtil;

/**
 * 统计业务类
 * 
 * @author david
 * @create 2017年11月27日 下午4:58:01
 */
@Service
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	private StatisticMapper statisticMapper;
	private String sql = "select d.dimension_key as dimension_key, "
			+ "case when a.dimension_value is null then 0 else a.dimension_value end as last_day, "
			+ "case when b.dimension_value is null then 0 else b.dimension_value END as last_week, "
			+ "case when c.dimension_value is null then 0 else c.dimension_value end as last_month, "
			+ "d.dimension_value as all_day " + "from "
			+ "(select dimension_key,SUM(dimension_value) as dimension_value from statistic WHERE dimension='%s' GROUP BY dimension_key) d "
			+ "LEFT JOIN "
			+ "(select dimension_key,SUM(dimension_value) as dimension_value from statistic WHERE dimension='%s' and day>='%s' GROUP BY dimension_key) c "
			+ "on d.dimension_key=c.dimension_key " + "LEFT JOIN "
			+ "(select dimension_key,SUM(dimension_value) as dimension_value from statistic WHERE dimension='%s' and day>='%s' GROUP BY dimension_key) b "
			+ "ON C.dimension_key=B.dimension_key " + " LEFT join "
			+ "(select dimension_key,SUM(dimension_value) as dimension_value from statistic WHERE dimension='%s' and day>='%s' GROUP BY dimension_key) a "
			+ "on C.dimension_key=A.dimension_key";

	@Override
	public List<Map<String, Object>> findStatistic(String dimension) {
		String lastWeek = DateUtil.getLastWeek();
		String lastMonth = DateUtil.getLastMonth();
		String lastDay = DateUtil.getLastDay();
		List<Map<String, Object>> findBySQL = statisticMapper.findBySQL(
				String.format(sql, dimension, dimension, lastMonth, dimension, lastWeek, dimension, lastDay));
		return findBySQL;
	}

	/**
	 * 统计插入
	 * 
	 * @return
	 */
	@Override
	public int statictic() {
		// 获取最新的统计信息日期--我们的统计从这个时间点开始
		String maxDate = getMaxDate();
		String lastDay = DateUtil.getLastDay();
		int sum = 0;
		if (StringUtils.isBlank(maxDate)) {
			// 如果数据库没有数据，从头到尾执行查询
			sum += statictic_age(null, lastDay);
			sum += statictic_status(null, lastDay);
			sum += statictic_product(null, lastDay);
			sum += statictic_channel(null, lastDay);
			
		} else {
			// 如果数据库有数据，从最后没有查询处开始执行查询
			sum += statictic_age(maxDate, lastDay);
			sum += statictic_age(maxDate, lastDay);
			sum += statictic_product(maxDate, lastDay);
			sum += statictic_channel(maxDate, lastDay);
		}
		return sum;
	}

	private String max_date_sql = "select max(day) as max_day from statistic";

	/**
	 * 获取数据表中的最大日期
	 * 
	 * @return
	 */
	private String getMaxDate() {
		List<Map<String, String>> selectBySQL = statisticMapper.selectBySQL(max_date_sql);
		if (selectBySQL == null || selectBySQL.get(0) == null)
			return null;
		return selectBySQL.get(0).get("max_day");
	}

	/**
	 * 年龄统计
	 * @param fromDay
	 * @param lastDay
	 * @return
	 */
	private int statictic_age(String fromDay, String lastDay) {
		String age_sql = "insert into statistic (day,dimension,dimension_key,dimension_value) "
				+ "SELECT DATE_FORMAT(u.gmt_create, '%Y-%m-%d') AS DAY,'age' AS dimension, "
				+ "CASE WHEN YEAR (from_days(datediff(now(),str_to_date(u.birth, '%Y-%m-%d')))) < 10 THEN '<10' "
				+ "WHEN YEAR (from_days(datediff(now(),str_to_date(u.birth, '%Y-%m-%d')))) < 20 THEN	'10-20' "
				+ "WHEN YEAR (from_days(datediff(now(),str_to_date(u.birth, '%Y-%m-%d')))) < 30 THEN	'20-30' "
				+ "WHEN YEAR (from_days(datediff(now(),str_to_date(u.birth, '%Y-%m-%d')))) < 40 THEN	'30-40' "
				+ "WHEN YEAR (from_days(datediff(now(),str_to_date(u.birth, '%Y-%m-%d')))) < 50 THEN	'40-50' "
				+ "ELSE '>50' END AS dimension_key, count(1) AS dimension_value " + "FROM USER u WHERE	1 = 1 "
				+ "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') < '" + lastDay + "' ";
		if (StringUtils.isNoneBlank(fromDay))
			age_sql += "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') > '" + fromDay + "' ";
		age_sql += "GROUP BY DAY,dimension,dimension_key;";
		int insertBySQL = statisticMapper.insertBySQL(age_sql);
		return insertBySQL;
	}

	/**
	 * 状态统计
	 * @param fromDay
	 * @param lastDay
	 * @return
	 */
	private int statictic_status(String fromDay, String lastDay) {
		String age_sql = "insert into statistic (day,dimension,dimension_key,dimension_value) "
				+ "SELECT DATE_FORMAT(u.gmt_create, '%Y-%m-%d') AS DAY,'status' AS dimension, "
				+ "CASE u.status WHEN 0 THEN '未发送' " + "WHEN 1 THEN '外发成功，数据有效' " + "WHEN 2 THEN '外发成功，数据无效' "
				+ "WHEN 3 THEN '外发失败' " + "ELSE '状态异常' END AS dimension_key, count(1) AS dimension_value "
				+ "FROM USER u WHERE	1 = 1 " + "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') < '" + lastDay + "' ";
		if (StringUtils.isNoneBlank(fromDay))
			age_sql += "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') > '" + fromDay + "' ";
		age_sql += "GROUP BY DAY,dimension,dimension_key;";
		int insertBySQL = statisticMapper.insertBySQL(age_sql);
		return insertBySQL;
	}
	/**
	 * 产品统计
	 * @param fromDay
	 * @param lastDay
	 * @return
	 */
	private int statictic_product(String fromDay, String lastDay) {
		String age_sql = "insert into statistic (day,dimension,dimension_key,dimension_value) "+
				"SELECT DATE_FORMAT(u.gmt_create, '%Y-%m-%d') AS DAY,'product' AS dimension, "+
				"u.product_name AS dimension_key, count(1) AS dimension_value "+
				"FROM USER u WHERE	1 = 1 "+
				"AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') < '"+lastDay+"' ";
		if (StringUtils.isNoneBlank(fromDay))
			age_sql += "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') > '" + fromDay + "' ";
		age_sql += "GROUP BY DAY,dimension,dimension_key;";
		int insertBySQL = statisticMapper.insertBySQL(age_sql);
		return insertBySQL;
	}
	/**
	 * 渠道统计
	 * @param fromDay
	 * @param lastDay
	 * @return
	 */
	private int statictic_channel(String fromDay, String lastDay) {
		String age_sql = "insert into statistic (day,dimension,dimension_key,dimension_value) "+
				"SELECT DATE_FORMAT(u.gmt_create, '%Y-%m-%d') AS DAY,'channel' AS dimension, "+
				"u.channel_name AS dimension_key, count(1) AS dimension_value "+
				"FROM USER u WHERE	1 = 1 "+
				"AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') < '"+lastDay+"' ";
		if (StringUtils.isNoneBlank(fromDay))
			age_sql += "AND DATE_FORMAT(u.gmt_create, '%Y-%m-%d') > '" + fromDay + "' ";
		age_sql += "GROUP BY DAY,dimension,dimension_key;";
		int insertBySQL = statisticMapper.insertBySQL(age_sql);
		return insertBySQL;
	}

	/**
	 * 最近一个月不同维度的所有数据
	 * @param dimension
	 * @return
	 */
	@Override
	public List<Statistic> findStatisticLastMonth(String dimension) {
		String lastMonth = DateUtil.getLastMonth();
		String lastDay = DateUtil.getLastDay();
		StatisticExample statisticExample = new StatisticExample();
		Criteria createCriteria = statisticExample.createCriteria();
		createCriteria.andDimensionEqualTo(dimension);
		createCriteria.andDayGreaterThanOrEqualTo(lastMonth);
		createCriteria.andDayLessThanOrEqualTo(lastDay);
		statisticExample.setOrderByClause("day");
		return statisticMapper.selectByExample(statisticExample);
	}

}
