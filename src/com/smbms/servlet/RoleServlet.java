package com.smbms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.smbms.dao.service.ProviderService;
import cn.smbms.dao.service.RoleService;
import cn.smbms.pojo.Role;
import cn.smbms.util.Page;

public class RoleServlet extends HttpServlet {
	public RoleServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		//操作类型
		String opr=request.getParameter("opr");
		if(opr.equals("getJsonList")){
			//查询所有角色列表
			JSONArray jArray=RoleService.getRoleService().getJsonList();
			out.print(jArray);
		}
		else if(opr.equals("list")){
			//查询所有角色
			Page page=null;
			List<Role> list=RoleService.getRoleService().getList(page);
			//将数据传给jsp/rolelist.jsp
			request.setAttribute("roleList", list);
			//转发跳转到jsp页面
			request.getRequestDispatcher("../jsp/rolelist.jsp").forward(request, response);
		}else if(opr.equals("add")){
			//新增角色
			response.setContentType("text/html;charset=utf-8");
			String name=request.getParameter("roleName");
			String code=request.getParameter("roleCode");
			Role role=new Role();
			role.setRoleCode(code);
			role.setRoleName(name);
			int row=new RoleService().saveRole(role);
			if(row==1){
				response.sendRedirect("RoleServlet?opr=list");
			}

		}else if(opr.equals("delete")){
			Integer id=Integer.valueOf(request.getParameter("id"));
			int row= RoleService.getRoleService().deleteRole(id);
			if(row>0){
				request.getRequestDispatcher("RoleServlet?opr=list").forward(request, response);
			}
			//删除角色
		}else if(opr.equals("update")){
			//修改角色
			Integer id=Integer.valueOf(request.getParameter("id"));
			String roleName= request.getParameter("roleName");
			int row=new RoleService().updateRoleName(roleName, id);
			if(row>0){
				response.sendRedirect("RoleServlet?opr=list");
			}
		}
		out.flush();
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void init() throws ServletException {
	}

}
