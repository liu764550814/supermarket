package com.nuaa.supermarket.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.supermarket.mapper.SmbmsUserMapper;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.pojo.SmbmsUserExample;
import com.nuaa.supermarket.service.LoginService;

/**
* @author 刘超明
* @version 创建时间：2019年10月30日 下午3:12:44
* 类说明
*/
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private SmbmsUserMapper smbmsUserMapper;

	
	
	
	@Override
	public SmbmsUser getUserByQuery(String userCode,String userPassword) {
		System.out.println("1111111111");
		SmbmsUserExample smbmsUserExample = new SmbmsUserExample();
		SmbmsUserExample.Criteria criteria = smbmsUserExample.createCriteria();
		System.out.println("22222222222");
		criteria.andUsercodeEqualTo(userCode);
		criteria.andUserpasswordEqualTo(userPassword);
		System.out.println("usercode = "+userCode+",  userpassword = "+userPassword);
		
		//分页设置，但是查询登录用不到，不过后面的列表查询用的是相同的方法，所以补上
		smbmsUserExample.setStart(0);
		smbmsUserExample.setRows(10);
		
		try {
			//密码和用户都正确
			List<SmbmsUser> list = smbmsUserMapper.selectByExample(smbmsUserExample);

			return list.get(0);
			
		} catch (Exception e) {
			
			System.out.println("service error");
			e.printStackTrace();
		}
		//用户或密码错误
		return null;


	}
	
}
