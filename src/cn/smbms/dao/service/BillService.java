package cn.smbms.dao.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.util.MybatisUtil;
import cn.smbms.util.Page;

public class BillService {
	private static BillService service=null;
	public synchronized static BillService getBillService(){
		if(null==service){
			service=new BillService();
		}
		return service;
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
		SqlSession sqlSession=null;
		sqlSession=MybatisUtil.currentSession();
		BillMapper mapper=sqlSession.getMapper(BillMapper.class);
//		Page page=new Page();
//		page.setIndexPage(1);
//		page.setPageSize(0);
		list=mapper.getBillList(proName,proId,isPay,page.getPageSize(),page.getLimit());
		sqlSession.close();
		return list;
	}
	public Bill getBillById(int id){
		Bill bill=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		BillMapper mapper=sqlSession.getMapper(BillMapper.class);
		bill=mapper.getBillById(id);
		sqlSession.close();
		return bill;
	}
	public int updateBillById(Bill bill){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		BillMapper mapper=sqlSession.getMapper(BillMapper.class);
		row=mapper.updateBillById(bill);
		sqlSession.close();
		return row;
	}
	public int deleteBillById(int id){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		BillMapper mapper=sqlSession.getMapper(BillMapper.class);
		row=mapper.deleteBillById(id);
		sqlSession.close();
		return row;
	}
	public int addBill(Bill bill){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		BillMapper mapper=sqlSession.getMapper(BillMapper.class);
		row=mapper.addBill(bill);
		sqlSession.close();
		return row;
	}
}
