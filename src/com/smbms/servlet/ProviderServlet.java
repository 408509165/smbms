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
			//��ȡ������Ӧ����Ϣ
			int id=Integer.valueOf(request.getParameter("id"));
			Provider provider= ProviderService.getProviderService().getProviderById(id);
			request.setAttribute("provider", provider);
			request.getRequestDispatcher("../jsp/providermodify.jsp").forward(request, response);
		}
		else if(opr.equals("getJsonList")){
			/*1:�׳�list���ϣ������Ϊprovider*/  
//			List<Provider> list=ProviderService.getProviderService().getProviderList();
//			out.print(JSON.toJSONString(list));	/*�����ķ�ʽ�׳�*/
			/*2:ֱ���׳�jsonarray����*/
			JSONArray jArray=ProviderService.getProviderService().getProviderIdAndProName();
			out.print(JSON.toJSONString(jArray));
			
		}else if(opr.equals("list")){
			String proCode = request.getParameter("queryProCode");//��Ӧ�̱���
			String proName = request.getParameter("queryProName");//��Ӧ������
			//��ѯ���㵱ǰ���������й�Ӧ��  ��ȡ��������
			Page page=new Page();
			page.setIndexPage(0);
			page.setPageSize(0);
			List<Provider> allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);
			int totalCount=allList.size();//��������
			//����ִ�з�ҳ��ѯ
			String c=request.getParameter("pageIndex");//��ȡ��ǰҪ��ʾ��ҳ����
			int indexPage;//��ǰ��ʾҳ����
			if(c==null)
				indexPage=1;
			else
				indexPage=Integer.valueOf(c);
			page=new Page();//���´���page����
			page.setCount(totalCount);//��ҳ��
			page.setPageSize(6);//ÿҳ��ʾ����
			if(indexPage>page.getPageCount())
				indexPage=page.getPageCount();
			page.setIndexPage(indexPage);//��ǰҳ����
			
			List<Provider> list=ProviderService.getProviderService().getProviderList(proCode,proName,page);
			//��������
			request.setAttribute("totalCount", totalCount);
			//��ǰҳ��
			request.setAttribute("currentPageNo", page.getIndexPage());
			//��ҳ��
			request.setAttribute("totalPageCount", page.getPageCount());
			
			request.setAttribute("providerList", list);
			request.setAttribute("queryProCode", proCode);
			request.setAttribute("queryProName", proName);
			request.getRequestDispatcher("../jsp/providerlist.jsp").forward(request, response);
		}else if(opr.equals("add")){
			//����
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
			//��������Ϊ��ǰ��¼�û�
			int userId=Integer.valueOf(request.getSession().getAttribute("userId").toString());
			p.setCreateBy(userId);
			int row=ProviderService.getProviderService().addProvider(p);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}else if(opr.equals("delete")){
			//ɾ��
			//��ȡҪɾ���Ĺ�Ӧ�̱��
			int id=Integer.valueOf(request.getParameter("id"));
			int row=ProviderService.getProviderService().deleteProviderById(id);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}else if(opr.equals("update")){
			//�޸�
			//��ȡҪ�޸ĵĹ�Ӧ�̱��
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
			//�޸���Ϊ��ǰ��¼�û�
			int userId=Integer.valueOf(request.getSession().getAttribute("userId").toString());
			p.setModifyBy(userId);
			int row=ProviderService.getProviderService().updateProviderById(p);
			if(row>0){
				response.sendRedirect("ProviderServlet?opr=list");
			}
		}
		
		out.flush(); //ˢ�������
		out.close(); //�ر������
		
	}

}
