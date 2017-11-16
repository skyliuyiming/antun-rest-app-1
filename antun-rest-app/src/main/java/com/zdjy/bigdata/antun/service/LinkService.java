package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.web.model.LinkAdd;
import com.zdjy.bigdata.antun.web.model.LinkUpdate;

public interface LinkService {

	List<Link> findAll();

	Object findAllWithPage();

	int saveLink(LinkAdd linkAdd);

	Link findByChannelCodeAndPageCode(String channelCode, String pageCode);

	int deleteLink(Long id);

	Link getLink(Long id);

	int updateStatus(Long id, Integer status);

	int updateLink(Long id, LinkUpdate linkUpdate);

	Link findByCode(String code);

	Object findByCodeWithPage(String code);

}
