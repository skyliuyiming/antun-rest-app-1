package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.web.model.ChannelAdd;
import com.zdjy.bigdata.antun.web.model.ChannelUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.validation.ChannelValidation;

/**
 * 渠道管理
 * @author david
 * @create 2017年11月15日 上午11:31:24
 */
@RestController
@RequestMapping("/channels")
public class ChannelController extends BaseResponse{
	@Autowired
	private ChannelValidation channelValidation;
	@Autowired
	private ChannelService channelService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findAll() {
		List<Channel> channels=channelService.findAll();
		return successModel("查询全部成功").data(channels);
	}
	@RequestMapping(value="/{id}/updateStatus",method=RequestMethod.PUT)
	public BaseResponse updateStatus(@PathVariable Long id,Integer status) {
		String msg=channelValidation.updateStatusValidation(status);
		if(msg!=null)
			return errorModel(msg);
		int i=channelService.updateStatus(id,status);
		return successModel("修改状态成功，数量："+i);
	}
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveChannel(ChannelAdd channelAdd) {
		String msg=channelValidation.saveChannelValidation(channelAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=channelService.saveChannel(channelAdd);
		return successModel("新增成功，数量："+i);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deleteChannel(@PathVariable Long id) {
		int i=channelService.deleteChannel(id);
		return successModel("删除成功，数量："+i);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getChannel(@PathVariable Long id) {
		Channel channel=channelService.getChannel(id);
		return successModel("id查询成功").data(channel);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updateChannel(@PathVariable Long id,ChannelUpdate channelUpdate) {
		String msg=channelValidation.updateChannelValidation(channelUpdate);
		if(msg!=null)
			return errorModel(msg);
		int i=channelService.updateChannel(id,channelUpdate);
		return successModel("修改成功，数量："+i);
	}
	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/status/{status}",method=RequestMethod.GET)
	public BaseResponse findByStatus(@PathVariable Integer status) {
		List<Channel> channels=channelService.findByStatus(status);
		return successModel("状态查询成功").data(channels);
	}
}
