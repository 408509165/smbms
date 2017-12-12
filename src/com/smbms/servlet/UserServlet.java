package com.smbms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smbms.dao.service.BillService;
import cn.smbms.dao.service.UserService;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.User;
import cn.smbms.util.Page;


public class UserServlet extends HttpServlet{
	//user 数据库操作实例对象
	UserService service=new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String opr= request.getParameter("opr");
		PrintWriter out =response.getWriter();
		if(opr.equals("login")){
			String userCode=request.getParameter("userCode");
			String userPassword=request.getParameter("userPassword");
			User user= service.login(userCode, userPassword);
			//System.out.println(user);
			if(null!=user){
				//成功
				String username=user.getUserName();
				int userId=user.getId();
				request.getSession().setAttribute("userName", username);//保存登录用户的name
				request.getSession().setAttribute("userId", userId);//保存登录用户的id
				request.getRequestDispatcher("../jsp/frame.jsp").forward(request, response);
			}else{
				out.print("<script> confirm('错误的用户名或密码') </script>");
			}
		}else if(opr.equals("list")){
			String username=request.getParameter("queryname");//用户名
			String r=request.getParameter("queryUserRole");//角色
			int queryuid=Integer.valueOf((r==null?0:r).toString());
			

			//查询所有 获取总数据量
			Page page=new Page();
			page.setIndexPage(0);
			page.setPageSize(0);
			List<User> billList=UserService.getUserService().getUserList(username,queryuid,page);
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
			
			
			List<User> list=UserService.getUserService().getUserList(username,queryuid,page);
			request.setAttribute("queryuid", queryuid);
			//最大页码数
			request.setAttribute("totalCount", totalCount);
			//当前页数
			request.setAttribute("currentPageNo", page.getIndexPage());
			//总页数
			request.setAttribute("totalPageCount", page.getPageCount());
			
			request.setAttribute("userList", list);
			request.setAttribute("queryUserName", username);
			request.getRequestDispatcher("../jsp/userlist.jsp").forward(request, response);
			
		}else if(opr.equals("add")){
			String userCode= request.getParameter("userCode");
			String userName= request.getParameter("userName");
			String userPassword= request.getParameter("userPassword");
			int gender= Integer.valueOf(request.getParameter("gender"));
			String birthday= request.getParameter("birthday");
			String phone= request.getParameter("phone");
			String address= request.getParameter("address");
			int userRole= Integer.valueOf(request.getParameter("userRole"));
			User user=new User();
			user.setAddress(address);
			user.setUserCode(userCode);
			user.setUserName(userName);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			try {
				user.setBirthday(format.parse(birthday));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			user.setUserPassword(userPassword);
			user.setUserRole(userRole);
			user.setGender(gender);
			user.setPhone(phone);
			user.setUserPassword(userPassword);
			String userId=(request.getSession().getAttribute("userId")).toString();
			int createBy=Integer.valueOf(userId); //当前登录用户的id
			user.setCreateBy(createBy);
			int row=UserService.getUserService().addUser(user);
			if(row>0){
				response.sendRedirect("UserServlet?opr=list");
			}
		}else if(opr.equals("update")){
			
		}else if(opr.equals("getUserById")){
			int id=Integer.valueOf(request.getParameter("id"));
			User user=UserService.getUserService().getUserById(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("../jsp/usermodify.jsp").forward(request, response);
		}else if(opr.equals("delete")){
			int id=Integer.valueOf(request.getParameter("id"));
			int row=UserService.getUserService().deleteUser(id);
			if(row>0){
				response.sendRedirect("UserServlet?opr=list");
			}
		}else if(opr.equals("getPwd")){
			//获取当前登录用户的密码
			
		}else if(opr.equals("updatePwd")){
			//修改当前登录用户的密码
		}
		
	}
}
