package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.domain.PageExample;
import com.zdjy.bigdata.antun.domain.PageExample.Criteria;
import com.zdjy.bigdata.antun.mapper.PageMapper;
import com.zdjy.bigdata.antun.service.PageService;
import com.zdjy.bigdata.antun.util.CodeGenerateUtils;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.PageAdd;
import com.zdjy.bigdata.antun.web.model.PageUpdate;
/**
 * 页面业务类
 * @author david
 * @create 2017年11月15日 下午4:34:54
 */
@Service
public class PageServiceImpl implements PageService {
	@Autowired
	private PageMapper pageMapper;

	/**
	 * 查询全部
	 * @return
	 */
	@Override
	public List<Page> findAll() {
		PageExample pageExample = new PageExample();
		pageExample.setOrderByClause("id desc");
		return pageMapper.selectByExample(pageExample);
	}

	/**
	 * 保存
	 * @param pageAdd
	 * @return
	 */
	@Override
	public int savePage(PageAdd pageAdd) {
		Page transfer = TransferUtil.transfer(pageAdd,Page.class);
		transfer.setCode(CodeGenerateUtils.getRandomCode());
		return pageMapper.insertSelective(transfer);
	}
	
	/**
	 * 编码查询
	 * @param code
	 * @return
	 */
	@Override
	public Page findByCode(String code) {
		PageExample pageExample = new PageExample();
		Criteria createCriteria = pageExample.createCriteria();
		createCriteria.andCodeEqualTo(code);
		pageExample.setLimit(1);
		List<Page> selectByExample = pageMapper.selectByExample(pageExample);
		if(selectByExample.isEmpty())
			return null;
		return selectByExample.get(0);
	}

	/**
	 * 文件名查询
	 * @param fileName
	 * @return
	 */
	@Override
	public Page findByFileName(String fileName) {
		PageExample pageExample = new PageExample();
		Criteria createCriteria = pageExample.createCriteria();
		createCriteria.andFileNameEqualTo(fileName);
		pageExample.setLimit(1);
		List<Page> selectByExample = pageMapper.selectByExample(pageExample);
		if(selectByExample.isEmpty())
			return null;
		return selectByExample.get(0);
	}

	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@Override
	public List<Page> findByStatus(Integer status) {
		PageExample pageExample = new PageExample();
		Criteria createCriteria = pageExample.createCriteria();
		createCriteria.andStatusEqualTo(status);
		pageExample.setOrderByClause("id desc");
		return pageMapper.selectByExample(pageExample);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public int deletePage(Long id) {
		return pageMapper.deleteByPrimaryKey(id);
	}

	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Page getPage(Long id) {
		return pageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateStatus(Long id, Integer status) {
		Page page = new Page();
		page.setId(id);
		page.setStatus(status);
		return pageMapper.updateByPrimaryKeySelective(page);
	}

	/**
	 * 修改页面
	 * @param id
	 * @param pageUpdate
	 * @return
	 */
	@Override
	public int updatePage(Long id, PageUpdate pageUpdate) {
		Page transfer = TransferUtil.transfer(pageUpdate,Page.class);
		transfer.setId(id);
		return pageMapper.updateByPrimaryKeySelective(transfer);
	}
}
