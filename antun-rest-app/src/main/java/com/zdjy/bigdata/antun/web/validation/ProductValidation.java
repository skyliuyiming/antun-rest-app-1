package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.web.model.ProductAdd;
import com.zdjy.bigdata.antun.web.model.ProductUpdate;

/**
 * 渠道验证类
 * @author david
 * @create 2017年11月15日 下午1:32:35
 */
@Component
public class ProductValidation extends BaseValidation{
	@Autowired
	private ProductService productService;
	/**
	 * 修改状态（上下线）
	 * @param status
	 * @return
	 */
	public String updateStatusValidation(Integer status) {
		if(status==null)
			return empty("状态");
		if(status!=1&& status!=0)
			return "状态异常";
		return null;
	}

	/**
	 * 保存验证
	 * @param productAdd
	 * @return
	 */
	public String saveProductValidation(ProductAdd productAdd) {
		if(StringUtils.isBlank(productAdd.getName()))
			return empty("渠道名");
		if(StringUtils.isBlank(productAdd.getCode()))
			return empty("渠道码");
		Product findByCode = productService.findByCode(productAdd.getCode());
		if(findByCode!=null)
			return exist("渠道码");
		return null;
	}

	/**
	 * 修改验证
	 * @param productUpdate
	 * @return
	 */
	public String updateProductValidation(ProductUpdate productUpdate) {
		if(StringUtils.isBlank(productUpdate.getName()))
			return empty("渠道名");
		return null;
	}
	
}
