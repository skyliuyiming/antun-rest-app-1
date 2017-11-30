package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.AreaExample;
import com.zdjy.bigdata.antun.domain.AreaExample.Criteria;
import com.zdjy.bigdata.antun.mapper.AreaMapper;
import com.zdjy.bigdata.antun.service.AreaService;
/**
 * 地区业务类
 * @author david
 * @create 2017年11月13日 下午4:46:41
 */
@Service
@CacheConfig(cacheNames="area")
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;

	/**
	 * 父级id查询
	 * @param parentId
	 * @return
	 */
	@Override
	@Cacheable(unless="#result.size()==0")
	public List<Area> findByParentId(Long parentId) {
		AreaExample areaExample=new AreaExample();
		Criteria createCriteria = areaExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<Area> selectByExample = areaMapper.selectByExample(areaExample);
		return selectByExample;
	}

	/**
	 * 根据id查询
	 * @param province
	 * @return
	 */
	@Override
	@Cacheable(unless="#result==null")
	public Area findArea(Long id) {
		Area area=areaMapper.selectByPrimaryKey(id);
		return area;
	}
}
