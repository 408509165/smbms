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
		page.setIndexPage(0);//当前页码数
		page.setPageSize(0);//每页显示数量
		//查询结果（初衷是为了获取最大数据量）
		List<Bill> billList= billService.getBillList(proName, proId, isPay, page);
		//获取最大数据量
		int totalCount=billList.size();
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
		billList=billService.getBillList(proName,proId,isPay,page);

		//System.out.println(proName+"-"+proId+"-"+isPay+"-"+page.toString()+"*"+pageIndex+"*"+pageOfNow);
		System.out.println(proName);
		mv.addObject("billList", billList);
		mv.addObject("totalCount", totalCount);
		//当前页数
		mv.addObject("currentPageNo", page.getIndexPage());
		//总页数
		mv.addObject("totalPageCount", page.getPageCount());
		//存值

		mv.addObject("queryProductName", proName);
		mv.addObject("proId", proId);
		mv.addObject("queryIsPayment", isPay);
		//跳转页面
		mv.setViewName("billlist");
		return mv;
	}

	/**
	 * 删除订单
	 * @return
	 */
	@RequestMapping(value="/deletebill.html/{bid}")
	public String deleteBill(@PathVariable(value="bid")Integer id){
		int row=billService.deleteBillById(id);

		return "redirect:/bill/billlist.html";
	}


	/**
	 * 修改操作
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
	 * 先查询该订单信息，再跳转到需要操作的页面
	 * @param model 数据对象
	 * @param id 订单id
	 * @return
	 */
	@RequestMapping(value={"/updatebill.html/{bid}/{do}","/viewbill.html/{bid}/{do}"})
	public String updateBill(Model model,@PathVariable(value="bid")Integer id,@PathVariable(value="do") Integer d){
		Bill bill=billService.getBillById(id);

		//存储bill对象
		model.addAttribute("bill", bill);
		//判断要跳转的页面是哪一个
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
