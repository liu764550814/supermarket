package com.nuaa.supermarket.mapper;

import com.nuaa.supermarket.pojo.SmbmsProvider;
import com.nuaa.supermarket.pojo.SmbmsProviderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmbmsProviderMapper {
    int countByExample(SmbmsProviderExample example);

    int deleteByExample(SmbmsProviderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmbmsProvider record);

    int insertSelective(SmbmsProvider record);

    List<SmbmsProvider> selectByExample(SmbmsProviderExample example);

    List<SmbmsProvider> selectByExampleNoLimit();//直接查询
    
    SmbmsProvider selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmbmsProvider record, @Param("example") SmbmsProviderExample example);

    int updateByExample(@Param("record") SmbmsProvider record, @Param("example") SmbmsProviderExample example);

    int updateByPrimaryKeySelective(SmbmsProvider record);

    int updateByPrimaryKey(SmbmsProvider record);
}