package cn.smbms.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.smbms.pojo.Provider;

@Controller
@RequestMapping("/action")
public class PageActionControl {
	
	/**
	 * 跳转至订单新增
	 * @return
	 */
	@RequestMapping("/addbill.html")
	public String viewToAddBill(){
		
		return "billadd";
	}
	
	/**
	 * 跳转至供应商新增
	 * @return
	 */
	@RequestMapping("/addprovider.html")
	public String viewToAddProvider(@ModelAttribute Provider provider){
		
		//return "provideradd";
		return "provider/provideradd";
	}
	
	
	/**
	 * 跳转至用户新增
	 * @return
	 */
	@RequestMapping("/adduser.html")
	public String viewToAddUser(){
		
		return "useradd";
	}
	
	/**
	 * 跳转至用户新增
	 * @return
	 */
	@RequestMapping("/addrole.html")
	public String viewToAddRole(){
		
		return "roleadd";
	}
	
	/**
	 * 跳转至密码修改
	 * @return
	 */
	@RequestMapping("/pwdmodify.html")
	public String viewTopwdmodify(){
		
		return "pwdmodify";
	}
	
	
}
