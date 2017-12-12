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
		//��������
		String opr=request.getParameter("opr");
		if(opr.equals("getJsonList")){
			//��ѯ���н�ɫ�б�
			JSONArray jArray=RoleService.getRoleService().getJsonList();
			out.print(jArray);
		}
		else if(opr.equals("list")){
			//��ѯ���н�ɫ
			Page page=null;
			List<Role> list=RoleService.getRoleService().getList(page);
			//�����ݴ���jsp/rolelist.jsp
			request.setAttribute("roleList", list);
			//ת����ת��jspҳ��
			request.getRequestDispatcher("../jsp/rolelist.jsp").forward(request, response);
		}else if(opr.equals("add")){
			//������ɫ
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
			//ɾ����ɫ
		}else if(opr.equals("update")){
			//�޸Ľ�ɫ
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
