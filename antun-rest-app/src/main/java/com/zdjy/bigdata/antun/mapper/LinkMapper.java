package com.zdjy.bigdata.antun.mapper;

import com.zdjy.bigdata.antun.domain.Link;
import com.zdjy.bigdata.antun.domain.LinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LinkMapper extends CommonMapper{
    long countByExample(LinkExample example);

    int deleteByExample(LinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Link record);

    int insertSelective(Link record);

    List<Link> selectByExample(LinkExample example);

    Link selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Link record, @Param("example") LinkExample example);

    int updateByExample(@Param("record") Link record, @Param("example") LinkExample example);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);
}