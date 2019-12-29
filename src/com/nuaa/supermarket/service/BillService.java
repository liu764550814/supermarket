package com.nuaa.supermarket.service;
/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:03:54
* 类说明
*/

import com.nuaa.supermarket.pojo.BillView;
import com.nuaa.supermarket.pojo.QueryVo;
import com.nuaa.supermarket.pojo.SmbmsBill;
import com.nuaa.supermarket.utils.Page;

public interface BillService {
	/**
	 * @param vo 
	 * 
	    * @Title: getAllBill
	    * @Description: 查询所有订单
	    * @param @return    参数
	    * @return List<SmbmsBill>    返回类型
	    * @throws
	 */
	Page<SmbmsBill> getAllBill(QueryVo vo);
	//查看订单信息部分信息
	BillView selectBill(int id);
	//查看订单全部信息
	SmbmsBill selectBillAll(long id);
	//修改订单
	int updateBill(SmbmsBill bill);
	//添加订单
	int insertBill(SmbmsBill bill);
	//删除订单
	int deleteBill(int id);
}
