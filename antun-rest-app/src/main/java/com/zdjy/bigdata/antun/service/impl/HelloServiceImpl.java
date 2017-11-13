package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdjy.bigdata.antun.domain.Hello;
import com.zdjy.bigdata.antun.domain.HelloExample;
import com.zdjy.bigdata.antun.mapper.HelloMapper;
import com.zdjy.bigdata.antun.service.HelloService;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.HelloAdd;
import com.zdjy.bigdata.antun.web.model.HelloUpdate;
import com.zdjy.bigdata.antun.web.response.PageMap;
/**
 * hello业务类
 * @author david
 * @create 2017年11月13日 上午10:21:16
 */
@Service
public class HelloServiceImpl implements HelloService {
	@Autowired
	private HelloMapper helloMapper;

	/**
	 * 查询全部
	 * @return
	 */
	@Override
	public List<Hello> findAll() {
//		return helloMapper.selectByExample(null);
		HelloExample helloExample = new HelloExample();
		helloExample.setOrderByClause("id desc");
		return helloMapper.selectByExample(helloExample);
	}

	/**
	 * 保存
	 * @param helloAdd
	 * @return
	 */
	@Override
	@Transactional
	public int saveHello(HelloAdd helloAdd) {
		Hello transfer = transfer(helloAdd);
		return helloMapper.insertSelective(transfer);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public int deleteHello(Long id) {
		return helloMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改
	 * @param id
	 * @param helloUpdate
	 * @return
	 */
	@Override
	@Transactional
	public int updateHello(Long id, HelloUpdate helloUpdate) {
		Hello transfer = transfer(helloUpdate);
		transfer.setId(id);
		return helloMapper.updateByPrimaryKeySelective(transfer);
	}

	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Hello getHello(Long id) {
		return helloMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @return
	 */
	@Override
	public PageMap getByPage(Integer offset, Integer limit) {
		if(offset==null||offset<0)
			offset=0;
		if(limit==null||limit<=0||offset>10)
			limit=10;
		HelloExample helloExample = new HelloExample();
		helloExample.setOffset(offset);
		helloExample.setLimit(limit);
		helloExample.setOrderByClause("id desc");
		List<Hello> selectByExample = helloMapper.selectByExample(helloExample);
		long countByExample = helloMapper.countByExample(helloExample);
		return new PageMap(selectByExample, countByExample);
	}
	
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param newsAdd
	 * @return
	 */
	public Hello transfer(HelloAdd helloAdd) {
		Hello hello = new Hello();
		TransferUtil.transfer(hello, helloAdd);
		return hello;
	}
	/**
	 * 修改类与领域类的转换
	 * 
	 * @param helloUpdate
	 * @return
	 */
	public Hello transfer(HelloUpdate helloUpdate) {
		Hello hello = new Hello();
		TransferUtil.transfer(hello, helloUpdate);
		return hello;
	}
}
