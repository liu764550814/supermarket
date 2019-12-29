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
import com.nuaa.supermarket.pojo.BillView;
import com.nuaa.supermarket.pojo.QueryVo;
import com.nuaa.supermarket.pojo.SmbmsBill;
import com.nuaa.supermarket.pojo.SmbmsProvider;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.service.BillService;
import com.nuaa.supermarket.service.ProviderService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午8:42:46
* bill控制器
*/
@Controller
@RequestMapping("bill")
public class BillController {
	
	@Autowired
	private BillService billService;
	@Autowired
	private ProviderService providerService;
	
	/**
	 * 
	    * @Title: billlist
	    * @Description: 跳转到billlist.jsp页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("billlist")
	public String billlist(Model model,QueryVo vo,@RequestParam(value = "pageIndex",required = false)String pageIndex){
		//没查询条件的时候，名字为空时为null
		if (vo.getQueryProductName()== "") {
			vo.setQueryProductName(null);
		}
		System.out.println("vo.name = "+vo.getQueryProductName()+" ,  vo.ispayment = "+vo.getQueryIsPayment()+",  vo.getQueryProviderId = "+vo.getQueryProviderId());	
		//得到供应商的列表
		List<SmbmsProvider> providerlist = providerService.getAllproviderNoLimit();
		
		//判断有没有手动输入跳转页数，或上下页
		if(pageIndex != null){
			System.out.println("inputPage = "+pageIndex);
			vo.setPage(Integer.valueOf(pageIndex));
			System.out.println("-----------------vo.page = "+vo.getPage());
		}	
		//查询列表订单信息
		Page<SmbmsBill> page = billService.getAllBill(vo);

		 //计算一共有多少页数
		System.out.println("-----------------------page.total1 = "+page.getTotal());		
		
		//计算总页
		int totalpage = 0;
		if (page.getTotal()%10 == 0) {
			totalpage=page.getTotal()/10;
		}else{
			totalpage=page.getTotal()/10 + 1;
		}
		System.out.println("-----------------------page.total1 = "+page.getTotal());
		//設置回显信息
		model.addAttribute("QueryProviderId", vo.getQueryProviderId());
		model.addAttribute("queryProductName", vo.getQueryProductName());
		model.addAttribute("queryIsPayment", vo.getQueryIsPayment());
		model.addAttribute("totalCount", page.getTotal());
		model.addAttribute("totalPageCount", totalpage);
		model.addAttribute("currentPageNo", page.getPage());
		model.addAttribute("bill", page.getList());
		model.addAttribute("providerlist", providerlist);
		return "billlist";
	}
	
	
	//查看订单
	@RequestMapping("view")
	public String view(Model model,int billid){
		System.out.println("view 已经运行 viewid = "+billid);
		
		BillView billView = billService.selectBill(billid);
		if (billView !=null ) {
			model.addAttribute("bill", billView);
		}else{
			model.addAttribute("msg", "查询失败！");
		}
		
		return "billview";
	}
	
	//修改订单
	@RequestMapping("updatebill")
	public String updatebill(Model model,int billid){
		System.out.println("updatebill   billid = "+billid);
		//得到bill信息
		SmbmsBill bill = billService.selectBillAll(billid);
		if(bill == null){
			model.addAttribute("msg", "加载错误！");
			return "billmodify";
		}else{
			//设置回显
			model.addAttribute("bill", bill);
		}
		//得到provider信息
		List<SmbmsProvider> providerlist = providerService.getAllproviderNoLimit();
		if (providerlist.size() == 0) {
			model.addAttribute("msg", "加载错误！");
			return "billmodify";
		}else{
			model.addAttribute("providerList", providerlist);
		}
		
		return "billmodify";
	}
	
	@RequestMapping("saveupdatebill")
	public String saveupdatebill(Model model,SmbmsBill bill,String ispayment,HttpSession session){
		System.out.println("saveupdatebill已经运行    ispayment = "+ispayment);
		System.out.println(bill);
		//得到当前登录用户的信息
		SmbmsUser sessionUser = (SmbmsUser) session.getAttribute("userSession");
		//完善信息
		bill.setIspayment(Integer.valueOf(ispayment));
		bill.setModifyby(sessionUser.getId());
		bill.setModifydate(new Date());
		//执行修改操作
		int count = billService.updateBill(bill);
		//设置回显
		if (count > 0) {
			model.addAttribute("msg", "修改成功！");
		}else{
			model.addAttribute("msg", "修改失败！");
		}
		
		return "billmodify";
	}
	//跳转到添加订单页面
	@RequestMapping("billadd")
	public String billadd(){
		return "billadd";
	}
	
	
	//保存添加的订单
	@RequestMapping("savebill")
	public String savebill(Model model,SmbmsBill bill,HttpSession session){
		System.out.println("savebill已经运行      bill= "+bill);
		//完善信息
		SmbmsUser sessionUser = (SmbmsUser) session.getAttribute("userSession");
		bill.setCreatedby(sessionUser.getId());
		bill.setCreationdate(new Date());
		//执行添加操作
		int count = billService.insertBill(bill);
		if (count > 0) {
			model.addAttribute("msg", "添加成功！");
		}else{
			model.addAttribute("msg", "添加失败！");
		}
		return "billadd";
	}
	
	//删除订单
	@RequestMapping("deletebillbyid")
	@ResponseBody
	public String deletebillbyid(int billid){
		HashMap<String, Object> result = new HashMap<String,Object>();
		System.out.println("deletebillbyid  已经运行   billid = "+billid);
		
		//执行删除操作，并得到影响的行数
		int count = billService.deleteBill(billid);
		//判断删除的结果
		if (count == 0) {
			result.put("delResult", "notexist");
		}else if(count < 0){
			result.put("delResult", "false");
		}else {
			result.put("delResult", "true");
		}
		
		
		return JSONArray.toJSONString(result);
	}
}



