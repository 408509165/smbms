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
	 * ��ȡ��Ӧ�̱�ź���
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
		out.close();//�ر������
	}

	@RequestMapping(value="/providerlist.html")
	public ModelAndView getBillList(
			@RequestParam(value="queryProCode",required=false,defaultValue="") String proCode
			,@RequestParam(value="queryProName",required=false,defaultValue="") String proName
			,@RequestParam(value="pageIndex",required=false,defaultValue="0")Integer pageIndex){
		ModelAndView mv=new ModelAndView();
		Page page=new Page();
		page.setIndexPage(0);//��ǰҳ����
		page.setPageSize(0);//ÿҳ��ʾ����
		//��ѯ�����������Ϊ�˻�ȡ�����������
		List<Provider> allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);
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
		allList=ProviderService.getProviderService().getProviderList(proCode,proName,page);

		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		System.out.println(proName);
		mv.addObject("providerList", allList);
		mv.addObject("totalCount", totalCount);
		//��ǰҳ��
		mv.addObject("currentPageNo", page.getIndexPage());
		//��ҳ��
		mv.addObject("totalPageCount", page.getPageCount());
		//��ֵ

		mv.addObject("queryProCode", proCode);
		mv.addObject("queryProName", proName);
		//��תҳ��
		mv.setViewName("providerlist");
		return mv;
	}
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteprovider.html/{pid}")
	public String deleteProvider(@PathVariable(value="pid")Integer id){
		int row=providerService.deleteProviderById(id);

		return "redirect:/provider/providerlist.html";
	}

	/**
	 * �Ȳ�ѯ
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
	 * ִ���޸�
	 * @param provider
	 * @return
	 */
	@RequestMapping(value="/doupdateprovider.html")
	public String doUpdateProvider(Provider provider){
		int row=providerService.updateProviderById(provider);

		return "redirect:/provider/providerlist.html";
	}
	/**
	 * ����
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
			//			��������
			// 1 ��ȡԭ�ļ���
			String fileName=pic_a.getOriginalFilename();
			// 2 ȷ�������ļ���
			String path=session.getServletContext().getRealPath("statics"+File.separator+"upload");
			File file=new File(path, fileName);
			// 3 ��������еĻ����ļ����浽 Ŀ��λ��
			pic_a.transferTo(file);

			int row=providerService.addProvider(provider);
			return "redirect:/provider/providerlist.html";
			
		}
	}


}
