package com.nuaa.supermarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.supermarket.mapper.SmbmsBillMapper;
import com.nuaa.supermarket.mapper.SmbmsProviderMapper;
import com.nuaa.supermarket.pojo.QueryPro;
import com.nuaa.supermarket.pojo.SmbmsBillExample;
import com.nuaa.supermarket.pojo.SmbmsProvider;
import com.nuaa.supermarket.pojo.SmbmsProviderExample;
import com.nuaa.supermarket.service.ProviderService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:29:25
* 类说明
*/
@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private SmbmsProviderMapper providerMapper;
	@Autowired
	private SmbmsBillMapper billMapper;
	
	@Override
	public Page<SmbmsProvider> getAllProviderList(QueryPro pro) {
		
		SmbmsProviderExample smbmsProviderExample = new SmbmsProviderExample();	
	
		//设置查询的Limit分页限制
		smbmsProviderExample.setStart((pro.getPage()-1)*pro.getRows());
		smbmsProviderExample.setRows(pro.getRows());
		
		
		
		// 判断是有查询条件，添加查询条件
		SmbmsProviderExample.Criteria criteria = smbmsProviderExample.createCriteria();
		if (pro.getQueryProName() != null && pro.getQueryProName() != "") {
			criteria.andPronameLike("%"+pro.getQueryProName()+"%");
		}
		if (pro.getQueryProCode() != null && pro.getQueryProCode() != "") {
			criteria.andProcodeLike("%"+pro.getQueryProCode()+"%");
		}	
			
		// 初始化page数据
		Page<SmbmsProvider> page = new Page<SmbmsProvider>(pro.getPage(),providerMapper.countByExample(smbmsProviderExample),pro.getRows(),providerMapper.selectByExample(smbmsProviderExample));
		return page;
	}

	@Override
	public List<SmbmsProvider> getAllproviderNoLimit() {	
		return providerMapper.selectByExampleNoLimit();
	}

	@Override
	public SmbmsProvider selectById(long id) {	
		return providerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateProById(SmbmsProvider provider) {
		return providerMapper.updateByPrimaryKeySelective(provider);
	}

	@Override
	public int insertProvider(SmbmsProvider provider) {	
		//返回影响行数
		return providerMapper.insertSelective(provider);
	}

	@Override
	public String deleteProvider(long id) {	
		int count = -2;//-2为删除失败
		int haveBillcount = 0;
		try {
			//查看该供应商下是否有订单
			SmbmsBillExample billExample = new SmbmsBillExample();
			SmbmsBillExample.Criteria criteria = billExample.createCriteria();
			criteria.andProvideridEqualTo((int) id);
			haveBillcount = billMapper.countByExample(billExample);
			
			if (haveBillcount > 0) {
				return String.valueOf(haveBillcount);
			}
			//执行删除
			count = providerMapper.deleteByPrimaryKey(id);
			if (count == 0) {
				return "notexist";
			}
		} catch (Exception e) {
			System.out.println("删除失败！");
			return "false";
		}
		//返回删除影响的行数
		return "true";
	}

}
