package cn.smbms.control;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.smbms.dao.service.BillService;
import cn.smbms.dao.service.ProviderService;
import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.util.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping(value="/provider")
public class ProviderControl {
	private ProviderService providerService=new ProviderService();

	/**
	 * 获取供应商编号和名
	 * @return 
	 */
	@RequestMapping(value="/getProNameAndProId.html",method=RequestMethod.POST)
	public void getProNameAndProId(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONArray jArray=ProviderService.getProviderService().getProviderIdAndProName();
		//System.out.println(jArray.getObject(1, Provider.class));
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(JSONArray.toJSONString(jArray));
		out.flush();
		out.close();//关闭输出流
	}

	@RequestMapping(value="/providerlist.html")
	public ModelAndView getBillList(
			@RequestParam(value="queryProCode",required=false,defaultValue="") String proCode
			,@RequestParam(value="queryProName",required=false,defaultValue="") String proName
			,@RequestParam(value="pageIndex",required=false,defaultValue="0")Integer pageIndex){
		ModelAndView mv=new ModelAndView();
		Page page=new Page();
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		//查询结果（初衷是为了获取最大数据量）
		List<Provider> allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);
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
		allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);

		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		System.out.println(proName);
		mv.addObject("providerList", allList);
		mv.addObject("totalCount", totalCount);
		//当前页数
		mv.addObject("currentPageNo", page.getIndexPage());
		//总页数
		mv.addObject("totalPageCount", page.getPageCount());
		//存值

		mv.addObject("queryProCode", proCode);
		mv.addObject("queryProName", proName);
		//跳转页面
		mv.setViewName("providerlist");
		return mv;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteprovider.html/{pid}")
	public String deleteProvider(@PathVariable(value="pid")Integer id){
		int row=providerService.deleteProviderById(id);

		return "redirect:/provider/providerlist.html";
	}

	/**
	 * 先查询
	 * @return
	 */
	@RequestMapping(value={"/updateprovider.html/{pid}/{do}","/providerview/{pid}/{do}"})
	public String updateProvider(@PathVariable(value="pid")Integer id,Model model,@PathVariable(value="do")Integer d){
		Provider provider=providerService.getProviderById(id);
		model.addAttribute("provider", provider);
		if(d==3304)
			return "providermodify";
		else
			return "providerview";
	}
	/**
	 * 执行修改
	 * @param provider
	 * @return
	 */
	@RequestMapping(value="/doupdateprovider.html")
	public String doUpdateProvider(Provider provider){
		int row=providerService.updateProviderById(provider);

		return "redirect:/provider/providerlist.html";
	}
	/**
	 * 增加
	 * @param provider
	 * @param result
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/addprovider.html")
	public String addProvider(@Valid Provider provider,BindingResult result
			,@RequestParam("a_companyLicPicPath")MultipartFile pic_a
			,HttpSession session) throws IllegalStateException, IOException{
		if(result.hasErrors()){
			return "provider/provideradd";
		}else{
			//			附件处理
			// 1 获取原文件名
			String fileName=pic_a.getOriginalFilename();
			// 2 确定保存文件夹
			String path=session.getServletContext().getRealPath("statics"+File.separator+"upload");
			File file=new File(path, fileName);
			// 3 将浏览器中的缓存文件保存到 目标位置
			pic_a.transferTo(file);

			int row=providerService.addProvider(provider);
			return "redirect:/provider/providerlist.html";
			
		}
	}


}
