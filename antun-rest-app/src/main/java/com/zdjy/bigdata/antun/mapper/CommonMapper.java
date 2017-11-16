package com.zdjy.bigdata.antun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 通用mapper
 * @author david
 * @create 2017年11月16日 上午9:29:23
 */
public interface CommonMapper{

    @Select(value = "${sql}")
    List<Map<String,String>> selectBySQL(@Param(value = "sql") String sql);

    @Select(value = "${sql}")
    List<Map<String,Object>> findBySQL(@Param(value = "sql") String sql);

    @Select(value = "${sql}")
    Long countBySQL(@Param(value = "sql") String sql);

    @Update(value = "${sql}")
    int updateBySQL(@Param(value = "sql") String sql);

    @Delete(value = "${sql}")
    int deleteBySQL(@Param(value = "sql") String sql);

    @Insert(value = "${sql}")
    int insertBySQL(@Param(value = "sql") String sql);


}
