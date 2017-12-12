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
	 * ��ת����������
	 * @return
	 */
	@RequestMapping("/addbill.html")
	public String viewToAddBill(){
		
		return "billadd";
	}
	
	/**
	 * ��ת����Ӧ������
	 * @return
	 */
	@RequestMapping("/addprovider.html")
	public String viewToAddProvider(@ModelAttribute Provider provider){
		
		//return "provideradd";
		return "provider/provideradd";
	}
	
	
	/**
	 * ��ת���û�����
	 * @return
	 */
	@RequestMapping("/adduser.html")
	public String viewToAddUser(){
		
		return "useradd";
	}
	
	/**
	 * ��ת���û�����
	 * @return
	 */
	@RequestMapping("/addrole.html")
	public String viewToAddRole(){
		
		return "roleadd";
	}
	
	/**
	 * ��ת�������޸�
	 * @return
	 */
	@RequestMapping("/pwdmodify.html")
	public String viewTopwdmodify(){
		
		return "pwdmodify";
	}
	
	
}
