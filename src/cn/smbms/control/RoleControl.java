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
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		//查询结果（初衷是为了获取最大数据量）
		List<Role> allList=roleService.getList(page);
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
		 allList=roleService.getList(page);
		
		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		mv.addObject("roleList", allList);
		mv.addObject("totalCount", totalCount);
		//当前页数
		mv.addObject("currentPageNo", page.getIndexPage());
		//总页数
		mv.addObject("totalPageCount", page.getPageCount());
		//存值
		//跳转页面
		mv.setViewName("rolelist");
		return mv;
	}
	
	
	/**
	 * 获取角色下拉框
	 *  @ResponseBody 标识该方法用于ajax 不需要跳转
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
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleterole.html/{rid}")
	public ModelAndView deleteRole(@PathVariable(value="rid")Integer id){
		int row=roleService.deleteRole(id);
		
		return getRoleList(0);
	}
	
	/**
	 * 先查询
	 * @return
	 */
	@RequestMapping(value="/updaterole.html/{rid}")
	public String updateRole(@PathVariable(value="rid")Integer id,Model model){
		Role role=roleService.getRole(id);
		model.addAttribute("role", role);
		
		return "rolemodify";
	}
	/**
	 * 执行修改
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
