package cn.smbms.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.smbms.dao.service.ProviderService;
import cn.smbms.dao.service.RoleService;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Role;
import cn.smbms.util.Page;

@Controller
@RequestMapping("/role")
public class RoleControl {
	private RoleService roleService;
	
	
	public RoleService getRoleService() {
		return roleService;
	}

	@Resource(name="roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	@RequestMapping(value="/rolelist.html")
	public ModelAndView getRoleList(
			@RequestParam(value="pageIndex",required=false,defaultValue="0")Integer pageIndex){
		ModelAndView mv=new ModelAndView();
		Page page=new Page();
		page.setIndexPage(0);//��ǰҳ����
		page.setPageSize(0);//ÿҳ��ʾ����
		//��ѯ�����������Ϊ�˻�ȡ�����������
		List<Role> allList=roleService.getList(page);
		//��ȡ���������
		int totalCount=allList.size();
		//��ҳ��ѯ
		int pageOfNow = pageIndex == null||pageIndex==0 ? 1 : pageIndex;//��ǰҳ��
		//������ǰҳ�Ķ���
		page=new Page();
		page.setCount(totalCount);//��ҳ��
		page.setPageSize(6);//ÿҳ��ʾ����
		//��鵱ǰҳ���Ƿ񳬹����ҳ����
		pageOfNow= pageOfNow>page.getPageSize() ? page.getPageSize():pageOfNow;
		//���ϲ���ȷ��pageOfNow��һ��ʼ�պϷ���ҳ���������������޸�page����ǰҳ������ΪpageOfNow
		page.setIndexPage(pageOfNow);
		//�����µ�page�����ѯ����
		 allList=roleService.getList(page);
		
		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		mv.addObject("roleList", allList);
		mv.addObject("totalCount", totalCount);
		//��ǰҳ��
		mv.addObject("currentPageNo", page.getIndexPage());
		//��ҳ��
		mv.addObject("totalPageCount", page.getPageCount());
		//��ֵ
		//��תҳ��
		mv.setViewName("rolelist");
		return mv;
	}
	
	
	/**
	 * ��ȡ��ɫ������
	 *  @ResponseBody ��ʶ�÷�������ajax ����Ҫ��ת
	 *  ,produces={"text/html;charset=utf-8","application/json;charset=utf-8"}
	 * @return 
	 */
	@RequestMapping(value="/view",method=RequestMethod.GET,produces={"text/html;charset=utf-8","application/json;charset=utf-8"})
	@ResponseBody
	public Object getProNameAndProId(){
		Page page=new Page();
		List<Role> list=roleService.getList(page);
		
		return JSONObject.toJSONString(list);
	}
	
	
	
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleterole.html/{rid}")
	public ModelAndView deleteRole(@PathVariable(value="rid")Integer id){
		int row=roleService.deleteRole(id);
		
		return getRoleList(0);
	}
	
	/**
	 * �Ȳ�ѯ
	 * @return
	 */
	@RequestMapping(value="/updaterole.html/{rid}")
	public String updateRole(@PathVariable(value="rid")Integer id,Model model){
		Role role=roleService.getRole(id);
		model.addAttribute("role", role);
		
		return "rolemodify";
	}
	/**
	 * ִ���޸�
	 * @param provider
	 * @return
	 */
	@RequestMapping(value="/doupdaterole.html")
	public String doUpdateRole(Role role){
		int row=roleService.updateRoleName(role.getRoleName(), role.getId());
		
		return "redirect:/role/rolelist.html";
	}
	
	@RequestMapping("/addrole.html")
	public String addRole(Role role){
		int row=roleService.saveRole(role);
		return "redirect:/role/rolelist.html";
	}
}
