package com.nuaa.supermarket.mapper;

import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.pojo.SmbmsUserExample;
import com.nuaa.supermarket.pojo.UserAndRole;
import com.nuaa.supermarket.pojo.UserView;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmbmsUserMapper {
    int countByExample(SmbmsUserExample example);

    int deleteByExample(SmbmsUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmbmsUser record);

    int insertSelective(SmbmsUser record);

    List<SmbmsUser> selectByExample(SmbmsUserExample example);

    SmbmsUser selectByPrimaryKey(Long id);
    
    UserView selectOneById(long id);
    
    List<UserAndRole> getUserAndRole(SmbmsUserExample example);

    int updateByExampleSelective(@Param("record") SmbmsUser record, @Param("example") SmbmsUserExample example);

    int updateByExample(@Param("record") SmbmsUser record, @Param("example") SmbmsUserExample example);

    int updateByPrimaryKeySelective(SmbmsUser record);

    int updateByPrimaryKey(SmbmsUser record);
}