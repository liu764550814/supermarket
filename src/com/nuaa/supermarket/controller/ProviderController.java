package com.nuaa.supermarket.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.nuaa.supermarket.pojo.QueryPro;
import com.nuaa.supermarket.pojo.SmbmsProvider;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.service.ProviderService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 下午5:08:14
* 供应商功能controller类
*/
@Controller
@RequestMapping("provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;
	
	/**
	 * 
	    * @Title: providerlist
	    * @Description: 查询记录
	    * @param @param model
	    * @param @param pro
	    * @param @param pageIndex
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("providerlist")					
	public String providerlist(Model model,QueryPro pro,@RequestParam(value = "pageIndex",required = false)Integer pageIndex){
		System.out.println("providerlist 运行");	
		System.out.println("pro.code = "+pro.getQueryProCode()+",  pro.name = "+pro.getQueryProName()+",  pageIndex = "+pageIndex);
		
		//将手动输入的页面赋值给pro变量
		if(pageIndex != null){
			pro.setPage(pageIndex);
			System.out.println("pro.page = "+pro.getPage());
		}
		
		//接收查询数据
		Page<SmbmsProvider> page = providerService.getAllProviderList(pro);
		
		//计算总页数
		int totalpage = 0;
		if (page.getTotal()%pro.getRows() == 0) {
			totalpage = page.getTotal()/pro.getRows();
		}else{
			totalpage = page.getTotal()/pro.getRows()+1;
		}
		
		//显示信息的回显
		model.addAttribute("providerList", page.getList());
		model.addAttribute("totalCount", page.getTotal());
		model.addAttribute("currentPageNo", page.getPage());
		model.addAttribute("totalPageCount", totalpage);
		model.addAttribute("queryProCode", pro.getQueryProCode());
		model.addAttribute("queryProName", pro.getQueryProName());
		return "providerlist";
	}
	
	
	
	
	@RequestMapping("/deleteprovider/{id}")
	public String deleteprovider() {
		System.out.println("deleteprovider 已经运行。。。。。。。。");
		return "providerlist";

	}
	
	//跳转到供应商查看
	@RequestMapping("proview")
	public String proview(Model model,int proid){
		
		System.out.println("proview已经运行、。。。proid = "+proid);
		
		SmbmsProvider provider = providerService.selectById(proid);
		if (provider == null) {
			model.addAttribute("msg", "查看失败！");
			return "redirect:providerlist.action";
		}
		model.addAttribute("provider", provider);
		return "providerview";
	}
	
	//修改供应商
	@RequestMapping("updateprovider")
	public String updateprovider(Model model,int proid){
		System.out.println("updateprovider已经运行。。  updateprovider = "+proid);
		
	 	SmbmsProvider provider = providerService.selectById(proid);
		if (provider == null) {
			model.addAttribute("msg", "查看失败！");
			return "redirect:providerlist.action";
		}
		model.addAttribute("provider", provider);
		
		return "providermodify";
	}
	
	
	@RequestMapping("saveupdateprovider")
	public String saveupdateprovider(Model model,SmbmsProvider provider,String proid,HttpSession session){
		System.out.println("provider = "+provider+", proid = "+proid);
		
		SmbmsUser sessionUser = (SmbmsUser) session.getAttribute("userSession");
		
		//设置修改信息
		provider.setModifyby(sessionUser.getId());
		provider.setModifydate(new Date());
		//设置provider的id值
		provider.setId((long)Integer.valueOf(proid));
		//执行修改操作
		int count = providerService.updateProById(provider);
		//判断是否执行成功
		if (count > 0) {
			model.addAttribute("msg", "修改成功！");
		}else{
			model.addAttribute("msg", "修改失败！");
		}
		
		return "providermodify";
	}
	
	
	//跳转到供应商添加页面
	@RequestMapping("provideradd")
	public String provideradd(){
		return "provideradd";
	}
	
	//添加供应商页面
	@RequestMapping("saveprovideradd")
	public String saveprovideradd(Model model,SmbmsProvider provider,HttpSession session){
		
		//得到当前用户登录信息
		SmbmsUser sessionUser = (SmbmsUser) session.getAttribute("userSession");
		//完善添加的信息
		provider.setCreatedby(sessionUser.getId());
		provider.setCreationdate(new Date());
		//执行添加操作
		int count = providerService.insertProvider(provider);
		//判断是否添加成功
		if (count > 0) {
			model.addAttribute("msg", "添加成功！");
		}else{
			model.addAttribute("msg", "添加失败！");
		}
		
		System.out.println("provider已经运行  saveprovideradd = "+provider);	
		return "provideradd";
	}
	
	//删除供应商
	@RequestMapping("deleteprovider")
	@ResponseBody
	public String deleteprovider(int proid){
		System.out.println("deleteprovider 已经运行。。。。proid = "+proid);
		
		HashMap< String, Object> result = new HashMap<String,Object>();
		//执行删除操作
		String msg = providerService.deleteProvider(proid);
		//设置返回结果
		result.put("delResult", msg);
		return JSONArray.toJSONString(result);
	}
	
	@RequestMapping("getProNamelist")
	@ResponseBody
	public String getProNamelist(){
		HashMap<String,Object> result = new HashMap<String,Object>();
		List<SmbmsProvider> list = providerService.getAllproviderNoLimit();
		if (list.size() != 0) {
			result.put("providerlist", list);
		}
		return JSONArray.toJSONString(result);
	}
	
}
