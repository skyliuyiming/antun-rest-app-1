package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.web.model.PageAdd;
import com.zdjy.bigdata.antun.web.model.PageUpdate;

public interface PageService {

	List<Page> findAll();

	int savePage(PageAdd pageAdd);

	Page findByCode(String code);

	Page findByFileName(String fileName);

	List<Page> findByStatus(Integer status);

	int deletePage(Long id);

	Page getPage(Long id);

	int updateStatus(Long id, Integer status);

	int updatePage(Long id, PageUpdate pageUpdate);

}
