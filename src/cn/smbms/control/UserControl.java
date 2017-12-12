package cn.smbms.control;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.smbms.dao.service.BillService;
import cn.smbms.dao.service.RoleService;
import cn.smbms.dao.service.UserService;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.util.Page;


@Controller
@RequestMapping("/user")
@SessionAttributes({"loginUserId","loginUserName"})
public class UserControl {
	private UserService userService=UserService.getUserService();

	@RequestMapping(value="/login.html",method=RequestMethod.POST)
	public String login(Model model,String userCode,String userPassword){
		//业务处理
		User user=userService.login(userCode, userPassword);
		if(null!=user){
			//成功
			model.addAttribute("loginUserId",user.getId()); //存储用户编号
			model.addAttribute("loginUserName",user.getUserName()); //存储用户名称
			return "frame";
		}
		//失败
		return "login";
	}


	@RequestMapping(value="/login1.html",method=RequestMethod.POST)
	public String login1(@RequestParam("userCode") String ucode,@RequestParam("userPassword")  String upwd){
		//业务处理
		User user=userService.login(ucode, upwd);
		if(null!=user)
			//成功
			return "frame";
		//失败
		return "login";
	}
	@RequestMapping(value="/login2.html",method=RequestMethod.POST)
	public String login2(User u){
		//业务处理
		User user=userService.login(u.getUserCode(),u.getUserPassword());
		if(null!=user)
			//成功
			return "frame";
		//失败
		return "login";
	}









	@RequestMapping(value="/userlist.html")
	public ModelAndView getUserList(@RequestParam(value="queryname",required=false,defaultValue="") String uName
			,@RequestParam(value="queryUserRole",required=false,defaultValue="0") Integer roleId
			,@RequestParam(value="pageIndex",required=false,defaultValue="0")Integer pageIndex){
		ModelAndView mv=new ModelAndView();
		Page page=new Page();
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		//查询结果（初衷是为了获取最大数据量）
		List<User> allList= userService.getUserList(uName,roleId,page);
		//获取最大数据量
		int totalCount=allList.size();
		//分页查询
		int pageOfNow = pageIndex == null||pageIndex==0 ? 1 : pageIndex;//当前页码
		//创建当前页的对象
		page=new Page();
		page.setCount(totalCount);//总页数
		page.setPageSize(6);//每页显示数量
		//检查当前页码是否超过最大页码数
		pageOfNow= pageOfNow>page.getPageSize() ? page.getPageSize():pageOfNow;
		//以上步骤确定pageOfNow是一个始终合法的页数并且满足需求，修改page对象当前页码数改为pageOfNow
		page.setIndexPage(pageOfNow);
		//根据新的page对象查询数据
		allList=userService.getUserList(uName,roleId,page);

		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		mv.addObject("userList", allList);
		mv.addObject("totalCount", totalCount);
		//当前页数
		mv.addObject("currentPageNo", page.getIndexPage());
		//总页数
		mv.addObject("totalPageCount", page.getPageCount());
		//存值

		mv.addObject("queryUserName", uName);
		mv.addObject("queryuid", roleId);
		//跳转页面
		mv.setViewName("userlist");
		return mv;
	}


	
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteuser.html/{uid}")
	public String deleteProvider(@PathVariable(value="uid")Integer id){
		int row=userService.deleteUser(id);
		
		return "redirect:/user/userlist.html";
	}
	
	/**
	 * 先查询
	 * @return
	 */
	@RequestMapping(value={"/updateuser.html/{uid}/{do}","/userview/{uid}/{do}"})
	public String updateUser(@PathVariable(value="uid")Integer id,Model model,@PathVariable(value="do") Integer d){
		User user=userService.getUserById(id);
		
		model.addAttribute("user", user);
		if(d==3304)
			return "usermodify";
		else
			return "userview";
	}
	
	/**
	 * 执行修改
	 * @param provider
	 * @return
	 */
	@RequestMapping(value="/doupdateuser.html")
	public String doUpdateProvider(User user){
		int row=userService.updateUser(user);
		
		return "redirect:/user/userlist.html";
	}
	@RequestMapping(value="/adduser.html")
	public String addUser(User user){
		int row=userService.addUser(user);
		
		return "redirect:/user/userlist.html";
	}
	
	@RequestMapping(value="/passsure/{id}/{pwd}")
	public void su(Model model,@PathVariable(value="id") Integer id,@PathVariable(value="pwd") String pwd,HttpServletRequest request,PrintWriter out){
		String pass=userService.getPwd(id);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(pwd.trim().equals(pass))
			out.print("√");
		else
			out.print("×");
		out.flush();
		out.close();
	}
	@RequestMapping("/catsure")
	public String catsure(String newpassword,Integer loginUserId){
		int row=userService.modifyPwd(newpassword, loginUserId);
		return "frame";
	}
}
