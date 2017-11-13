package com.zdjy.bigdata.antun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.mapper.ProductMapper;
import com.zdjy.bigdata.antun.service.ProductService;
/**
 * 产品业务类
 * @author david
 * @create 2017年11月13日 下午2:49:09
 */
@Service

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
}
