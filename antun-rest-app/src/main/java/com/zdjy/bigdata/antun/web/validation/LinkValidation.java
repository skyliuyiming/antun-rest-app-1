package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.service.LinkService;
import com.zdjy.bigdata.antun.service.PageService;
import com.zdjy.bigdata.antun.web.model.LinkAdd;
import com.zdjy.bigdata.antun.web.model.LinkUpdate;

/**
 * 链接验证类
 * @author david
 * @create 2017年11月16日 上午9:46:26
 */
@Component
public class LinkValidation extends BaseValidation{
	@Autowired
	private ChannelService channelService;
	@Autowired
	private PageService pageService;
	@Autowired
	private LinkService linkService;
	/**
	 * 新增验证
	 * @param linkAdd
	 * @return
	 */
	public String saveLinkValidation(LinkAdd linkAdd) {
		if(StringUtils.isBlank(linkAdd.getChannelCode()))
			return empty("渠道");
		if(StringUtils.isBlank(linkAdd.getPageCode()))
			return empty("页面");
		Channel findByCode = channelService.findByCode(linkAdd.getChannelCode());
		if(findByCode==null)
			return notExist("渠道");
		if(findByCode.getStatus()!=1)
			return "该渠道已下线";
		
		Page findByCode2 = pageService.findByCode(linkAdd.getPageCode());
		if(findByCode2==null)
			return notExist("页面");
		if(findByCode2.getStatus()!=1)
			return "该页面已下线";
		Link link=linkService.findByChannelCodeAndPageCode(linkAdd.getChannelCode(),linkAdd.getPageCode());
		if(link!=null)
			return exist("同一个渠道和页面");
		linkAdd.setChannelName(findByCode.getName());
		return null;
	}
	
	/**
	 * 修改验证
	 * @param id
	 * @param linkUpdate
	 * @return
	 */
	public String updateLinkValidation(Long id, LinkUpdate linkUpdate) {
		if(StringUtils.isBlank(linkUpdate.getChannelCode()))
			return empty("渠道");
		if(StringUtils.isBlank(linkUpdate.getPageCode()))
			return empty("页面");
		Channel findByCode = channelService.findByCode(linkUpdate.getChannelCode());
		if(findByCode==null)
			return notExist("渠道");
		if(findByCode.getStatus()!=1)
			return "该渠道已下线";
		
		Page findByCode2 = pageService.findByCode(linkUpdate.getPageCode());
		if(findByCode2==null)
			return notExist("页面");
		if(findByCode2.getStatus()!=1)
			return "该页面已下线";
		Link link=linkService.findByChannelCodeAndPageCode(linkUpdate.getChannelCode(),linkUpdate.getPageCode());
		if(link!=null&&link.getId().longValue()!=id.longValue())
			return exist("同一个渠道和页面");
		linkUpdate.setChannelName(findByCode.getName());
		return null;
	}
	
}
