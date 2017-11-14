package com.zdjy.bigdata.antun.mapper;

import com.zdjy.bigdata.antun.domain.UserSending;
import com.zdjy.bigdata.antun.domain.UserSendingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSendingMapper {
    long countByExample(UserSendingExample example);

    int deleteByExample(UserSendingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserSending record);

    int insertSelective(UserSending record);

    List<UserSending> selectByExample(UserSendingExample example);

    UserSending selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserSending record, @Param("example") UserSendingExample example);

    int updateByExample(@Param("record") UserSending record, @Param("example") UserSendingExample example);

    int updateByPrimaryKeySelective(UserSending record);

    int updateByPrimaryKey(UserSending record);
}