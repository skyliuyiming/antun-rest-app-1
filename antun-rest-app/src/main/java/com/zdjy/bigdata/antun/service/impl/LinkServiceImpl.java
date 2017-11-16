package com.zdjy.bigdata.antun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.domain.LinkExample;
import com.zdjy.bigdata.antun.domain.LinkExample.Criteria;
import com.zdjy.bigdata.antun.mapper.LinkMapper;
import com.zdjy.bigdata.antun.service.LinkService;
import com.zdjy.bigdata.antun.util.CodeGenerateUtils;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.LinkAdd;
import com.zdjy.bigdata.antun.web.model.LinkUpdate;
/**
 * 链接业务类
 * @author david
 * @create 2017年11月16日 上午11:12:55
 */
@Service
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkMapper linkMapper;
	//查询全部链接和页面相关信息的sql语句
	private static final String SQL_SELECT_ALL_WITH_PAGE="select l.id,l.channel_name,l.code,l.gmt_create,l.status,p.description,p.product_name,p.platform from link l left join page p on l.page_code=p.code order by l.id desc";
	/**
	 * 查询全部
	 * @return
	 */
	@Override
	public List<Link> findAll() {
		LinkExample linkExample = new LinkExample();
		linkExample.setOrderByClause("id desc");
		return linkMapper.selectByExample(linkExample);
	}

	/**
	 * 查询全部包括页面信息
	 * @return
	 */
	@Override
	public Object findAllWithPage() {
		List<Map<String, String>> selectBySQL = linkMapper.selectBySQL(SQL_SELECT_ALL_WITH_PAGE);
		return selectBySQL;
	}

	/**
	 * 保存链接
	 * @param linkAdd
	 * @return
	 */
	@Override
	public int saveLink(LinkAdd linkAdd) {
		Link transfer = TransferUtil.transfer(linkAdd, Link.class);
		String randomCode = CodeGenerateUtils.getRandomCode();
		transfer.setCode(randomCode);
		return linkMapper.insertSelective(transfer);
	}

	/**
	 * 渠道和页面查询
	 * @param channelCode
	 * @param pageCode
	 * @return
	 */
	@Override
	public Link findByChannelCodeAndPageCode(String channelCode, String pageCode) {
		LinkExample linkExample = new LinkExample();
		Criteria createCriteria = linkExample.createCriteria();
		createCriteria.andChannelCodeEqualTo(channelCode);
		createCriteria.andPageCodeEqualTo(pageCode);
		linkExample.setLimit(1);
		List<Link> selectByExample = linkMapper.selectByExample(linkExample);
		if(selectByExample.isEmpty())
			return null;
		return selectByExample.get(0);
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public int deleteLink(Long id) {
		return linkMapper.deleteByPrimaryKey(id);
	}

	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Link getLink(Long id) {
		return linkMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateStatus(Long id, Integer status) {
		Link link = new Link();
		link.setId(id);
		link.setStatus(status);
		return linkMapper.updateByPrimaryKeySelective(link);
	}

	/**
	 * 修改
	 * @param id
	 * @param linkUpdate
	 * @return
	 */
	@Override
	public int updateLink(Long id, LinkUpdate linkUpdate) {
		Link transfer = TransferUtil.transfer(linkUpdate, Link.class);
		transfer.setId(id);
		return linkMapper.updateByPrimaryKeySelective(transfer);
	}
	
}
