package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.domain.ProductExample;
import com.zdjy.bigdata.antun.domain.ProductExample.Criteria;
import com.zdjy.bigdata.antun.mapper.ProductMapper;
import com.zdjy.bigdata.antun.service.ProductService;
import com.zdjy.bigdata.antun.util.EsapiUtil;
/**
 * 产品业务类
 * @author david
 * @create 2017年11月13日 下午2:49:09
 */
@Service

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	/**
	 * 产品码查询
	 * @param productCode
	 * @return
	 */
	@Override
	public Product findByCode(String productCode) {
		ProductExample productExample = new ProductExample();
		Criteria createCriteria = productExample.createCriteria();
		createCriteria.andCodeEqualTo(EsapiUtil.sql(productCode));
		productExample.setLimit(1);
		List<Product> selectByExample = productMapper.selectByExample(productExample);
		if(!selectByExample.isEmpty())
			return selectByExample.get(0);
		return null;
	}
}
