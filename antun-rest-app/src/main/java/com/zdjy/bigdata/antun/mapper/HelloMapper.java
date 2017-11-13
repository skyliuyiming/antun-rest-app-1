package com.zdjy.bigdata.antun.mapper;

import com.zdjy.bigdata.antun.domain.Hello;
import com.zdjy.bigdata.antun.domain.HelloExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelloMapper {
    long countByExample(HelloExample example);

    int deleteByExample(HelloExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Hello record);

    int insertSelective(Hello record);

    List<Hello> selectByExample(HelloExample example);

    Hello selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Hello record, @Param("example") HelloExample example);

    int updateByExample(@Param("record") Hello record, @Param("example") HelloExample example);

    int updateByPrimaryKeySelective(Hello record);

    int updateByPrimaryKey(Hello record);
}