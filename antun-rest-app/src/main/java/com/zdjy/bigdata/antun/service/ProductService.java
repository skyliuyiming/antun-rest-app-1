package com.zdjy.bigdata.antun.service;

import com.zdjy.bigdata.antun.domain.Product;

public interface ProductService {

	Product findByCode(String productCode);

}
