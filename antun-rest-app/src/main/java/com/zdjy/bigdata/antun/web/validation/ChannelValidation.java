package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.web.model.ChannelAdd;
import com.zdjy.bigdata.antun.web.model.ChannelUpdate;

/**
 * 渠道验证类
 * @author david
 * @create 2017年11月15日 下午1:32:35
 */
@Component
public class ChannelValidation extends BaseValidation{
	@Autowired
	private ChannelService channelService;


	/**
	 * 保存验证
	 * @param channelAdd
	 * @return
	 */
	public String saveChannelValidation(ChannelAdd channelAdd) {
		if(StringUtils.isBlank(channelAdd.getName()))
			return empty("渠道名");
		if(StringUtils.isBlank(channelAdd.getCode()))
			return empty("渠道码");
		Channel findByCode = channelService.findByCode(channelAdd.getCode());
		if(findByCode!=null)
			return exist("渠道码");
		return null;
	}

	/**
	 * 修改验证
	 * @param channelUpdate
	 * @return
	 */
	public String updateChannelValidation(ChannelUpdate channelUpdate) {
		if(StringUtils.isBlank(channelUpdate.getName()))
			return empty("渠道名");
		return null;
	}
	
}
