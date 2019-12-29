package com.nuaa.supermarket.mapper;

import com.nuaa.supermarket.pojo.SmbmsRole;
import com.nuaa.supermarket.pojo.SmbmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmbmsRoleMapper {
    int countByExample(SmbmsRoleExample example);

    int deleteByExample(SmbmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmbmsRole record);

    int insertSelective(SmbmsRole record);

    List<SmbmsRole> selectByExample(SmbmsRoleExample example);

    List<SmbmsRole> getAllRoleNoCondition();
    
    SmbmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmbmsRole record, @Param("example") SmbmsRoleExample example);

    int updateByExample(@Param("record") SmbmsRole record, @Param("example") SmbmsRoleExample example);

    int updateByPrimaryKeySelective(SmbmsRole record);

    int updateByPrimaryKey(SmbmsRole record);
}