package com.zdjy.bigdata.antun.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.service.PageService;
import com.zdjy.bigdata.antun.web.model.PageAdd;
import com.zdjy.bigdata.antun.web.model.PageUpdate;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.validation.PageValidation;

/**
 * 页面接口
 * @author david
 * @create 2017年11月15日 下午4:35:41
 */
@RestController
@RequestMapping("/pages")
public class PageController extends BaseResponse{
	@Autowired
	private PageService pageService;
	@Autowired
	private PageValidation pageValidation;
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findAll() {
		List<Page> pages=pageService.findAll();
		return successModel("查询全部成功").data(pages);
	}
	/**
	 * 保存
	 * @param pageAdd
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse savePage(PageAdd pageAdd) {
		String msg=pageValidation.savePageValidation(pageAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=pageService.savePage(pageAdd);
		return successModel("新增成功，数量："+i);
	}
	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/status/{status}",method=RequestMethod.GET)
	public BaseResponse findByStatus(@PathVariable Integer status) {
		List<Page> pages=pageService.findByStatus(status);
		return successModel("状态查询成功").data(pages);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public BaseResponse deletePage(@PathVariable Long id) {
		int i=pageService.deletePage(id);
		return successModel("删除成功，数量："+i);
	}
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public BaseResponse getPage(@PathVariable Long id) {
		Page page=pageService.getPage(id);
		return successModel("id查询成功").data(page);
	}
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/{id}/updateStatus",method=RequestMethod.PUT)
	public BaseResponse updateStatus(@PathVariable Long id,Integer status) {
		String msg=pageValidation.updateStatusValidation(status);
		if(msg!=null)
			return errorModel(msg);
		int i=pageService.updateStatus(id,status);
		return successModel("修改状态成功，数量："+i);
	}
	/**
	 * 修改
	 * @param id
	 * @param pageUpdate
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public BaseResponse updatePage(@PathVariable Long id,PageUpdate pageUpdate) {
		String msg=pageValidation.updatePageValidation(id,pageUpdate);
		if(msg!=null)
			return errorModel(msg);
		int i=pageService.updatePage(id,pageUpdate);
		return successModel("修改成功，数量："+i);
	}
}
