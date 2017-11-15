package com.zdjy.bigdata.antun.mapper;

import com.zdjy.bigdata.antun.domain.Page;
import com.zdjy.bigdata.antun.domain.PageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageMapper {
    long countByExample(PageExample example);

    int deleteByExample(PageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Page record);

    int insertSelective(Page record);

    List<Page> selectByExample(PageExample example);

    Page selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Page record, @Param("example") PageExample example);

    int updateByExample(@Param("record") Page record, @Param("example") PageExample example);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKey(Page record);
}