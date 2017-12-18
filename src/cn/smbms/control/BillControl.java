package cn.smbms.control;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import com.smbms.anntation.ReadAnnotationFromClass;

import cn.smbms.dao.service.BillService;
import cn.smbms.pojo.Bill;
import cn.smbms.util.Page;

@Controller
@RequestMapping(value="/bill")
public class BillControl {
	private BillService billService;


	public BillService getBillService() {
		return billService;
	}
	@Resource(name="billService")
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	@RequestMapping(value="/billlist.html")
	public ModelAndView getBillList(@RequestParam(value="queryProductName",required=false,defaultValue="") String proName
			,@RequestParam(value="queryProviderId",required=false,defaultValue="0") Integer proId
			,@RequestParam(value="queryIsPayment",required=false,defaultValue="0")Integer isPay
			,@RequestParam(value="pageIndex",required=false,defaultValue="0")Integer pageIndex){
		ModelAndView mv=new ModelAndView();
		Page page=new Page();
		page.setIndexPage(0);//��ǰҳ����
		page.setPageSize(0);//ÿҳ��ʾ����
		//��ѯ�����������Ϊ�˻�ȡ�����������
		List<Bill> billList= billService.getBillList(proName, proId, isPay, page);
		//��ȡ���������
		int totalCount=billList.size();
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
		billList=billService.getBillList(proName,proId,isPay,page);

		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		System.out.println(proName);
		mv.addObject("billList", billList);
		mv.addObject("totalCount", totalCount);
		//��ǰҳ��
		mv.addObject("currentPageNo", page.getIndexPage());
		//��ҳ��
		mv.addObject("totalPageCount", page.getPageCount());
		//��ֵ

		mv.addObject("queryProductName", proName);
		mv.addObject("proId", proId);
		mv.addObject("queryIsPayment", isPay);
		//��תҳ��
		mv.setViewName("billlist");
		return mv;
	}

	/**
	 * ɾ������
	 * @return
	 */
	@RequestMapping(value="/deletebill.html/{bid}")
	public String deleteBill(@PathVariable(value="bid")Integer id){
		int row=billService.deleteBillById(id);

		return "redirect:/bill/billlist.html";
	}


	/**
	 * �޸Ĳ���
	 * @param bill
	 * @return
	 */
	@RequestMapping(value="/submitbill.html")
	public String doUpdateBill(Bill bill){
		int row=billService.updateBillById(bill);

		//return getBillList("", 0, 0, 0);
		return "redirect:/bill/billlist.html";
	}
	
	@RequestMapping(value="/addbill.html")
	public String addBill(Bill bill){
		int row=billService.addBill(bill);
		
		//return getBillList("", 0, 0, 0);
		return "redirect:/bill/billlist.html";
	}
	
	/**
	 * �Ȳ�ѯ�ö�����Ϣ������ת����Ҫ������ҳ��
	 * @param model ���ݶ���
	 * @param id ����id
	 * @return
	 */
	@RequestMapping(value={"/updatebill.html/{bid}/{do}","/viewbill.html/{bid}/{do}"})
	public String updateBill(Model model,@PathVariable(value="bid")Integer id,@PathVariable(value="do") Integer d){
		Bill bill=billService.getBillById(id);

		//�洢bill����
		model.addAttribute("bill", bill);
		//�ж�Ҫ��ת��ҳ������һ��
		//String methodName="updateBill";
		//String value = ReadAnnotationFromClass.getValue(BillControl.class,methodName);
		
		//view to 
		if(d==3304)
			return "billmodify";
		else
			return "billview";
	}

	
	@Test
	public void testName() throws Exception {
		ReadAnnotationFromClass.getValue(BillControl.class,"updateBill",RequestMapping.class);
	}
	
}
