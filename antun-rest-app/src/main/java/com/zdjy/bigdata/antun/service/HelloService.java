package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Hello;
import com.zdjy.bigdata.antun.web.model.HelloAdd;
import com.zdjy.bigdata.antun.web.model.HelloUpdate;
import com.zdjy.bigdata.antun.web.response.PageMap;

public interface HelloService {

	List<Hello> findAll();

	int saveHello(HelloAdd helloAdd);

	int deleteHello(Long id);

	int updateHello(Long id, HelloUpdate helloUpdate);

	Hello getHello(Long id);

	PageMap getByPage(Integer offset, Integer limit);

}
