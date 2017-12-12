package com.smbms.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.dao.service.BillService;
import cn.smbms.dao.service.ProviderService;
import cn.smbms.dao.service.RoleService;
import cn.smbms.dao.service.UserService;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.util.Page;

public class Test {
	@org.junit.Test
	public void testGetBillList() throws Exception {
		Page page=new Page();
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		List<Bill> list=BillService.getBillService().getBillList("长",0,0,page);
		for (Bill bill : list) {
			System.out.println(bill.toString());
		}
	}
	@org.junit.Test
	public void testGetBillById() throws Exception {
		System.out.println(BillService.getBillService().getBillById(1).toString());;
	}
	@org.junit.Test
	public void testDeleteBillById() throws Exception {
		System.out.println(BillService.getBillService().deleteBillById(19));
	}
	
	/*provider测试*/
	@org.junit.Test
	public void testGetProviderList() throws Exception {
		Page page=new Page();
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		List<Provider> list=ProviderService.getProviderService().getProviderList("","",page);
		for (Provider provider : list) {
			System.out.println(provider.toString());
		}
	}
	
	@org.junit.Test
	public void testGetProviderIdAndProName() throws Exception {
		JSONArray jArray=ProviderService.getProviderService().getProviderIdAndProName();
		for (Object object : jArray) {
			System.out.println(object);
		}
	}
	@org.junit.Test
	public void testGetJsonList() throws Exception {
		JSONArray jArray=RoleService.getRoleService().getJsonList();
		for (Object object : jArray) {
			System.out.println(object);
		}
	}
	@org.junit.Test
	public void testGetProviderById() throws Exception {
		System.out.println(ProviderService.getProviderService().getProviderById(1).toString());
	}
	
	@org.junit.Test
	public void testLogin1() throws Exception {
		System.out.println(new UserService().login("admin", "1234567"));
	}
	/*@org.junit.Test
	public void testLogin2() throws Exception {
		Map<String,String> map=new HashMap<String, String>();
		map.put("admin", "1234567");
		System.out.println(new UserService().login2(map));
	}*/
	@org.junit.Test
	public void testLogin() throws Exception {
		System.out.println(new UserService().login("admin", "1234567"));
	}
	@org.junit.Test
	public void testGetUserList() throws Exception {
		Page page=new Page();
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		List<User> list=UserService.getUserService().getUserList("孙",3,page);
		for (User user : list) {
			System.out.println(user.toString());
		}
		
	}
	@org.junit.Test
	public void testUpdateUser() throws Exception {
		User u=new User();
		u.setId(15);
		u.setUserPassword("1234567");
		System.out.println(UserService.getUserService().updateUser(u));
		
	}
	@org.junit.Test
	public void testGetUserList1() throws Exception {
		int [] rids={1};
		for (User user : UserService.getUserService().getUserList1(rids)) {
			System.out.println(user.toString());
		}
	}
	@org.junit.Test
	public void testGetUserList2() throws Exception {
		List rids=new ArrayList();
		rids.add(1);
		rids.add(3);
		for (User user : UserService.getUserService().getUserList2(rids)) {
			System.out.println(user.toString());
		}
	}
	@org.junit.Test
	public void testGetUserList3() throws Exception {
		List list=new ArrayList();
		list.add(1);
		list.add(3);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userName", "张");
		map.put("idList", list);
		
		for (User user : UserService.getUserService().getUserList3(map)) {
			System.out.println(user.toString());
		}
	}
}
