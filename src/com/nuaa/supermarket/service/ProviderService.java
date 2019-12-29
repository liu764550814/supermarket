package com.nuaa.supermarket.service;

import java.util.List;

import com.nuaa.supermarket.pojo.QueryPro;
import com.nuaa.supermarket.pojo.SmbmsProvider;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:28:59
* 供应商service类
*/
public interface ProviderService {
	/**
	 * 
	    * @Title: getAllProviderList
	    * @Description: 查询所有的供应商列表
	    * @param @return    参数
	    * @return List<SmbmsProvider>    返回类型
	    * @throws
	 */
	Page<SmbmsProvider> getAllProviderList(QueryPro pro);
	//无查询条件得到全部供应商列表
	List<SmbmsProvider> getAllproviderNoLimit();
	//根据id查看供应商的详细信息
	SmbmsProvider selectById(long id);
	//修改
	int updateProById(SmbmsProvider provider);
	//添加
	int insertProvider(SmbmsProvider provider);
	//删除
	String deleteProvider(long id);
}
