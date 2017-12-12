package com.smbms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.smbms.dao.service.ProviderService;
import cn.smbms.pojo.Provider;
import cn.smbms.util.Page;

public class ProviderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out =response.getWriter();
		String opr=request.getParameter("opr");
		if(opr.equals("getProviderById")){
			//获取单个供应商信息
			int id=Integer.valueOf(request.getParameter("id"));
			Provider provider= ProviderService.getProviderService().getProviderById(id);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher("../jsp/providermodify.jsp").forward(request, response);
		}
		else if(opr.equals("getJsonList")){
			/*1:抛出list集合，其对象为provider*/  
//			List<Provider> list=ProviderService.getProviderService().getProviderList();
//			out.print(JSON.toJSONString(list));	/*以流的方式抛出*/
			/*2:直接抛出jsonarray集合*/
			JSONArray jArray=ProviderService.getProviderService().getProviderIdAndProName();
			out.print(JSON.toJSONString(jArray));
			
		}else if(opr.equals("list")){
			String proCode = request.getParameter("queryProCode");//供应商编码
			String proName = request.getParameter("queryProName");//供应商名称
			//查询满足当前条件的所有供应商  获取总数据量
			Page page=new Page();
			page.setIndexPage(0);
			page.setPageSize(0);
			List<Provider> allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);
			int totalCount=allList.size();//总数据量
			//重新执行分页查询
			String c=request.getParameter("pageIndex");//获取当前要显示的页码数
			int indexPage;//当前显示页码数
			if(c==null)
				indexPage=1;
			else
				indexPage=Integer.valueOf(c);
			page=new Page();//重新创建page对象
			page.setCount(totalCount);//总页数
			page.setPageSize(6);//每页显示数量
			if(indexPage>page.getPageCount())
				indexPage=page.getPageCount();
			page.setIndexPage(indexPage);//当前页码数
			
			List<Provider> list=ProviderService.getProviderService().getProviderList(proCode,proName,page);
			//总数据量
			request.setAttribute("totalCount", totalCount);
			//当前页数
			request.setAttribute("currentPageNo", page.getIndexPage());
			//总页数
			request.setAttribute("totalPageCount", page.getPageCount());
			
			request.setAttribute("providerList", list);
			request.setAttribute("queryProCode", proCode);
			request.setAttribute("queryProName", proName);
			request.getRequestDispatcher("../jsp/providerlist.jsp").forward(request, response);
		}else if(opr.equals("add")){
			//新增
			String proCode=request.getParameter("proCode");
			String proName=request.getParameter("proName");
			String proContact=request.getParameter("proContact");
			String proPhone=request.getParameter("proPhone");
			String proAddress=request.getParameter("proAddress");
			String proFax=request.getParameter("proFax");
			String proDesc=request.getParameter("proDesc");
			Provider p=new Provider();
			p.setProCode(proCode);
			p.setProName(proName);
			p.setProContact(proContact);
			p.setProPhone(proPhone);
			p.setProAddress(proAddress);
			p.setProFax(proFax);
			p.setProDesc(proDesc);
			//创建者者为当前登录用户
			int userId=Integer.valueOf(request.getSession().getAttribute("userId").toString());
			p.setCreateBy(userId);
			int row=ProviderService.getProviderService().addProvider(p);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}else if(opr.equals("delete")){
			//删除
			//获取要删除的供应商编号
			int id=Integer.valueOf(request.getParameter("id"));
			int row=ProviderService.getProviderService().deleteProviderById(id);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}else if(opr.equals("update")){
			//修改
			//获取要修改的供应商编号
			int id=Integer.valueOf(request.getParameter("providerId"));
			String proCode=request.getParameter("proCode");
			String proName=request.getParameter("proName");
			String proContact=request.getParameter("proContact");
			String proPhone=request.getParameter("proPhone");
			String proAddress=request.getParameter("proAddress");
			String proFax=request.getParameter("proFax");
			String proDesc=request.getParameter("proDesc");
			Provider p=new Provider();
			p.setProCode(proCode);
			p.setProName(proName);
			p.setProContact(proContact);
			p.setProPhone(proPhone);
			p.setProAddress(proAddress);
			p.setProFax(proFax);
			p.setProDesc(proDesc);
			p.setId(id);
			//修改者为当前登录用户
			int userId=Integer.valueOf(request.getSession().getAttribute("userId").toString());
			p.setModifyBy(userId);
			int row=ProviderService.getProviderService().updateProviderById(p);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}
		
		out.flush(); //刷新输出流
		out.close(); //关闭输出流
		
	}

}
