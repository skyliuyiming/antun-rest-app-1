package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.service.PageService;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.web.model.PageAdd;
import com.zdjy.bigdata.antun.web.model.PageUpdate;

/**
 * 页面验证类
 * @author david
 * @create 2017年11月15日 下午4:40:58
 */
@Component
public class PageValidation extends BaseValidation{
	@Autowired
	private PageService pageService;
	@Autowired
	private ProductService productService;
	public String savePageValidation(PageAdd pageAdd) {
		if(StringUtils.isBlank(pageAdd.getFileName()))
			return empty("文件名");
		if(StringUtils.isBlank(pageAdd.getProductCode()))
			return empty("产品码");
		Page page=pageService.findByFileName(pageAdd.getFileName());
		if(page!=null)
			return exist("文件名");
		Product findByCode = productService.findByCode(pageAdd.getProductCode());
		if(findByCode==null)
			return "产品码不存在";
		if(findByCode.getStatus()!=1)
			return "该产品已下线";
		pageAdd.setProductName(findByCode.getName());
		return null;
	}
	/**
	 * 修改验证
	 * @param pageUpdate
	 * @return
	 */
	public String updatePageValidation(Long id,PageUpdate pageUpdate) {
		if(StringUtils.isBlank(pageUpdate.getFileName()))
			return empty("文件名");
		if(StringUtils.isBlank(pageUpdate.getProductCode()))
			return empty("产品码");
		Page page=pageService.findByFileName(pageUpdate.getFileName());
		if(page!=null&&page.getId()!=id)
			return exist("文件名");
		Product findByCode = productService.findByCode(pageUpdate.getProductCode());
		if(findByCode==null)
			return "产品码不存在";
		if(findByCode.getStatus()!=1)
			return "该产品已下线";
		pageUpdate.setProductName(findByCode.getName());
		return null;
	}

}
