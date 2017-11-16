package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.service.LinkService;
import com.zdjy.bigdata.antun.web.model.LinkAdd;
import com.zdjy.bigdata.antun.web.model.LinkUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.validation.LinkValidation;

/**
 * 连接接口类
 * @author david
 * @create 2017年11月16日 上午9:09:49
 */
@RestController
@RequestMapping("/links")
public class LinkController extends BaseResponse{
	@Autowired
	private LinkService linkService;
	@Autowired
	private LinkValidation linkValidation;
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findAll() {
		List<Link> links=linkService.findAll();
		return successModel("查询全部成功").data(links);
	}
	/**
	 * 查询全部包括页面相关信息
	 * @return
	 */
	@RequestMapping(value="/withPage",method=RequestMethod.GET)
	public BaseResponse findAllWithLink() {
		Object object=linkService.findAllWithPage();
		return successModel("查询全部成功").data(object);
	}
	/**
	 * 保存
	 * @param linkAdd
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveLink(LinkAdd linkAdd) {
		String msg=linkValidation.saveLinkValidation(linkAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=linkService.saveLink(linkAdd);
		return successModel("新增成功，数量："+i);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deleteLink(@PathVariable Long id) {
		int i=linkService.deleteLink(id);
		return successModel("删除成功，数量："+i);
	}
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getLink(@PathVariable Long id) {
		Link link=linkService.getLink(id);
		return successModel("id查询成功").data(link);
	}
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/{id}/updateStatus",method=RequestMethod.PUT)
	public BaseResponse updateStatus(@PathVariable Long id,Integer status) {
		String msg=linkValidation.updateStatusValidation(status);
		if(msg!=null)
			return errorModel(msg);
		int i=linkService.updateStatus(id,status);
		return successModel("修改状态成功，数量："+i);
	}
	/**
	 * 修改
	 * @param id
	 * @param linkUpdate
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updateLink(@PathVariable Long id,LinkUpdate linkUpdate) {
		String msg=linkValidation.updateLinkValidation(id,linkUpdate);
		if(msg!=null)
			return errorModel(msg);
		int i=linkService.updateLink(id,linkUpdate);
		return successModel("修改成功，数量："+i);
	}
	/**
	 * 编码查询
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/code",method=RequestMethod.GET)
	public BaseResponse findByCode(String code) {
		String msg=linkValidation.codeValidation(code);
		if(msg!=null)
			return errorModel(msg);
		Link link=linkService.findByCode(code);
		return successModel("编码查询成功").data(link);
	}
	/**
	 * 编码查询
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/code/withPage",method=RequestMethod.GET)
	public BaseResponse findByCodeWithPage(String code) {
		String msg=linkValidation.codeValidation(code);
		if(msg!=null)
			return errorModel(msg);
		Object object=linkService.findByCodeWithPage(code);
		return successModel("编码查询成功").data(object);
	}
}
