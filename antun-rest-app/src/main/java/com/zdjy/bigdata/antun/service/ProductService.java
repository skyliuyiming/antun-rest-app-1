package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Product;
import com.zdjy.bigdata.antun.web.model.ProductAdd;
import com.zdjy.bigdata.antun.web.model.ProductUpdate;

public interface ProductService {

	Product findByCode(String productCode);

	List<Product> findAll();

	int updateStatus(Long id, Integer status);

	int saveProduct(ProductAdd productAdd);

	int deleteProduct(Long id);

	int updateProduct(Long id, ProductUpdate productUpdate);

	Product getProduct(Long id);

	List<Product> findByStatus(Integer status);

}
