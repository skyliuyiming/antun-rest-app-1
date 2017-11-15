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
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.ProductAdd;
import com.zdjy.bigdata.antun.web.model.ProductUpdate;
/**
 * 产品业务类
 * @author david
 * @create 2017年11月13日 下午2:48:27
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

	/**
	 * 查询全部
	 * @return
	 */
	@Override
	public List<Product> findAll() {
		ProductExample productExample = new ProductExample();
		productExample.setOrderByClause("status desc,id desc");
		return productMapper.selectByExample(productExample);
	}

	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateStatus(Long id, Integer status) {
		Product product = new Product();
		product.setId(id);
		product.setStatus(status);
		return productMapper.updateByPrimaryKeySelective(product);
	}

	/**
	 * 保存产品
	 * @param productAdd
	 * @return
	 */
	@Override
	public int saveProduct(ProductAdd productAdd) {
		Product transfer = transfer(productAdd);
		return productMapper.insertSelective(transfer);
	}
	
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param productAdd
	 * @return
	 */
	public Product transfer(ProductAdd productAdd) {
		Product product = new Product();
		TransferUtil.transfer(product, productAdd);
		return product;
	}
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param productUpdate
	 * @return
	 */
	public Product transfer(ProductUpdate productUpdate) {
		Product product = new Product();
		TransferUtil.transfer(product, productUpdate);
		return product;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public int deleteProduct(Long id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改
	 * @param id
	 * @param productUpdate
	 * @return
	 */
	@Override
	public int updateProduct(Long id, ProductUpdate productUpdate) {
		Product transfer = transfer(productUpdate);
		transfer.setId(id);
		return productMapper.updateByPrimaryKeySelective(transfer);
	}

	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Product getProduct(Long id) {
		return productMapper.selectByPrimaryKey(id);
	}

	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@Override
	public List<Product> findByStatus(Integer status) {
		ProductExample productExample = new ProductExample();
		Criteria createCriteria = productExample.createCriteria();
		createCriteria.andStatusEqualTo(status);
		productExample.setOrderByClause("id desc");
		return productMapper.selectByExample(productExample);
	}
}
