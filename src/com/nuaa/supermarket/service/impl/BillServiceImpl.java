package com.nuaa.supermarket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nuaa.supermarket.mapper.SmbmsBillMapper;
import com.nuaa.supermarket.pojo.BillView;
import com.nuaa.supermarket.pojo.QueryVo;
import com.nuaa.supermarket.pojo.SmbmsBill;
import com.nuaa.supermarket.pojo.SmbmsBillExample;
import com.nuaa.supermarket.service.BillService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:05:02
* 类说明
*/
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private SmbmsBillMapper billMapper;
	
	@Override
	public Page<SmbmsBill> getAllBill(QueryVo vo) {
	
		SmbmsBillExample smbmsBillExample = new SmbmsBillExample();
		//设置开始查询的第一条记录
		smbmsBillExample.setStart((vo.getPage()-1)*vo.getRows());
		System.out.println("smbmsBillExample--------start = "+(vo.getPage()-1)*vo.getRows());
		//设置每页多少条记录
		smbmsBillExample.setRows(vo.getRows());
		System.out.println("smbmsBillExample.rows = "+smbmsBillExample.getRows()+"smbmsBillExample.start = "+smbmsBillExample.getStart());
		//添加查询条件
		SmbmsBillExample.Criteria criteria = smbmsBillExample.createCriteria();
		
		
		//判断是否输入查询条件
		if (vo.getQueryIsPayment() != null) {
			criteria.andIspaymentEqualTo(vo.getQueryIsPayment());
			
		}
		if (vo.getQueryProductName()!=null && vo.getQueryProductName() !="") {
			criteria.andProductnameLike("%"+vo.getQueryProductName()+"%");
		}
		
		if(vo.getQueryProviderId() != null){
			criteria.andProvideridEqualTo(vo.getQueryProviderId());
		}
		
		//criteria.andProductnameEqualTo("%"+vo.getQueryProductName()+"%");
		//System.out.println("111113333311111111");
		//criteria.andIspaymentEqualTo(vo.getQueryIsPayment());
		//criteria.andIspaymentEqualTo(1);
		//System.out.println("1111111111111");
		
		//获得一共多少条记录
		int total = billMapper.countByExample(smbmsBillExample);
		
		System.out.println("==============================serviceimpl_total = "+total);
		Page<SmbmsBill> page = new Page<SmbmsBill>(vo.getPage(),total,vo.getRows(),billMapper.selectByExample(smbmsBillExample));
		//System.out.println("333333333333");
		return page;
	}

	@Override
	public BillView selectBill(int id) {
		return billMapper.selectBillView(id);
	}

	@Override
	public SmbmsBill selectBillAll(long id) {
		return billMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateBill(SmbmsBill bill) {	
		return billMapper.updateByPrimaryKeySelective(bill);
	}

	@Override
	public int insertBill(SmbmsBill bill) {
		return billMapper.insertSelective(bill);
	}

	@Override
	public int deleteBill(int id) {
		//删除影响的行数
		int count = 0;
		try {
			//执行删除操作。并且得到返回值
			return count = billMapper.deleteByPrimaryKey((long) id);
		} catch (Exception e) {
			//操作执行失败
			count = -1;
		}	 
		//返回删除影响的行数
		return count;
	}
	

}
