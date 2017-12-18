package cn.smbms.dao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.util.Page;
@Service
public class BillService {
	
	private BillMapper mapper=null;
	
	public BillMapper getMapper() {
		return mapper;
	}
	@Resource(name="billMapper")
	public void setMapper(BillMapper mapper) {
		this.mapper = mapper;
	}
	/**
	 * 查询条件获取列表，条件可以为空
	 * @param proName 产品名
	 * @param proId	产品编号
	 * @param isPay	是否付款
	 * @param page	页码
	 * @return
	 */
	public List<Bill> getBillList(String proName,int proId,int isPay,Page page){
		List<Bill> list=null;
//		Page page=new Page();
//		page.setIndexPage(1);
//		page.setPageSize(0);
		list=mapper.getBillList(proName,proId,isPay,page.getPageSize(),page.getLimit());
		return list;
	}
	public Bill getBillById(int id){
		Bill bill=null;
		bill=mapper.getBillById(id);
		return bill;
	}
	public int updateBillById(Bill bill){
		int row=0;
		row=mapper.updateBillById(bill);
		return row;
	}
	public int deleteBillById(int id){
		int row=0;
		row=mapper.deleteBillById(id);
		return row;
	}
	public int addBill(Bill bill){
		int row=0;
		row=mapper.addBill(bill);
		return row;
	}
}
