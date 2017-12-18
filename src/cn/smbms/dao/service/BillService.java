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
	 * ��ѯ������ȡ�б���������Ϊ��
	 * @param proName ��Ʒ��
	 * @param proId	��Ʒ���
	 * @param isPay	�Ƿ񸶿�
	 * @param page	ҳ��
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
