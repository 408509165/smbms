package com.smbms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smbms.dao.service.BillService;
import cn.smbms.pojo.Bill;
import cn.smbms.util.Page;
/**
 * 
 * @author SunShine
 *	访问路径：servlet/BillServlet
 */
public class BillServlet extends HttpServlet {


	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		//操作类型
		String opr=request.getParameter("opr");
		if(opr.equals("list")){
			String proName= request.getParameter("queryProductName");//商品名称
			int proId;//供应商名称
			int isPay;//是否付款
			String a=request.getParameter("queryProviderId");
			String b=request.getParameter("queryIsPayment");
			if(a==null)
				proId=0;
			else
				proId=Integer.valueOf(a);
			if(b==null)
				isPay=0;
			else
				isPay=Integer.valueOf(b);
			
			//查询所有订单 获取总数据量
			Page page=new Page();
			page.setIndexPage(0);
			page.setPageSize(0);
			List<Bill> billList=BillService.getBillService().getBillList(proName,proId,isPay,page);
			int totalCount=billList.size();
			//分页查询
			String c=request.getParameter("pageIndex");
			int indexPage;//当前显示页码数
			if(c==null)
				indexPage=1;
			else
				indexPage=Integer.valueOf(c);
			
			
			page=new Page();
			page.setCount(totalCount);//总页数
			page.setPageSize(6);//每页显示数量
			if(indexPage>page.getPageCount())
				indexPage=page.getPageCount();
			page.setIndexPage(indexPage);//当前页码数
			List<Bill> list=BillService.getBillService().getBillList(proName,proId,isPay,page);
			request.setAttribute("billList", list);
			request.setAttribute("totalCount", totalCount);
			//当前页数
			request.setAttribute("currentPageNo", page.getIndexPage());
			//总页数
			request.setAttribute("totalPageCount", page.getPageCount());
			//存值
			request.setAttribute("queryProductName", proName);
			request.setAttribute("proId", proId);
			request.setAttribute("queryIsPayment", isPay);
			//跳转
			request.getRequestDispatcher("../jsp/billlist.jsp").forward(request, response);
		}else if(opr.equals("add")){
			//新增订单
			String userId=(request.getSession().getAttribute("userId")).toString();
			int id=Integer.valueOf(userId); //当前登录用户的id
			String billCode=request.getParameter("billCode");
			String productName=request.getParameter("productName");
			String productUnit=request.getParameter("productUnit");
			double productCount=Double.parseDouble(request.getParameter("productCount"));
			double totalPrice=Double.parseDouble(request.getParameter("totalPrice"));
			int isPayment=Integer.valueOf(request.getParameter("isPayment"));
			int providerId=Integer.valueOf(request.getParameter("providerId"));
			Bill bill=new Bill();
			bill.setBillCode(billCode);
			bill.setProductName(productName);
			bill.setProductCount(productCount);
			bill.setProductUnit(productUnit);
			bill.setTotalPrice(totalPrice);
			bill.setCreateBy(id);
			bill.setIsPayment(isPayment);
			bill.setProviderId(providerId);
			System.out.println(bill.toString());
			int row= BillService.getBillService().addBill(bill);
			if(row>0){
				response.sendRedirect("BillServlet?opr=list");
			}
		}else if(opr.equals("delete")){
			//删除订单
			int id=Integer.valueOf((String) request.getParameter("id")); 
			int row=BillService.getBillService().deleteBillById(id);
			if(row>0){
				response.sendRedirect("BillServlet?opr=list");
			}
		}else if(opr.equals("update")){
			//修改订单
			int id=Integer.valueOf(request.getParameter("id"));
			Bill bill=new Bill();
			bill.setBillCode(request.getParameter("billCode"));
			bill.setProductName(request.getParameter("productName"));
			bill.setProductUnit(request.getParameter("productUnit"));
			double Count=Double.parseDouble(request.getParameter("productCount"));
			bill.setProductCount(Count);
			double totalPrice=Double.parseDouble(request.getParameter("totalPrice"));
			bill.setTotalPrice(totalPrice);
			int pid=Integer.valueOf(request.getParameter("pid"));
			bill.setProviderId(pid);
			bill.setId(id);
			int row=BillService.getBillService().updateBillById(bill);
			if(row>0){
				response.sendRedirect("BillServlet?opr=list");
			}
			
		}else if(opr.equals("getBillById")){
			//查询单个订单
			int id=Integer.valueOf(request.getParameter("id"));
			Bill bill= BillService.getBillService().getBillById(id);
			request.setAttribute("bill", bill);
			request.getRequestDispatcher("../jsp/billmodify.jsp").forward(request, response);
		}
		
	}

}
