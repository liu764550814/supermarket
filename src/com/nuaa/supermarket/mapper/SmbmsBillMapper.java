package com.nuaa.supermarket.mapper;

import com.nuaa.supermarket.pojo.BillView;
import com.nuaa.supermarket.pojo.SmbmsBill;
import com.nuaa.supermarket.pojo.SmbmsBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmbmsBillMapper {
    int countByExample(SmbmsBillExample example);

    int deleteByExample(SmbmsBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmbmsBill record);

    int insertSelective(SmbmsBill record);

    List<SmbmsBill> selectByExample(SmbmsBillExample example);

    SmbmsBill selectByPrimaryKey(Long id);

    BillView selectBillView(int id);
    
    int updateByExampleSelective(@Param("record") SmbmsBill record, @Param("example") SmbmsBillExample example);

    int updateByExample(@Param("record") SmbmsBill record, @Param("example") SmbmsBillExample example);

    int updateByPrimaryKeySelective(SmbmsBill record);

    int updateByPrimaryKey(SmbmsBill record);
    
}