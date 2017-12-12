package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Role;

/**
 *  角色dao接口
 */
public interface BillMapper {
	/**
	 * 查询所有订单
	 * @return
	 */
	List<Bill> getBillList(@Param("proName")String proName,@Param("proId")int proId,@Param("isPay")int isPay,@Param("pageSize")int pageSize,@Param("limit")int limit);
	
	List<Bill> getBillList1(String billCode);
	/**
	 * 根据id删除订单
	 * @param id
	 * @return
	 */
	int deleteBillById(int id);
	/**
	 * 新增订单
	 * @param bill
	 * @return
	 */
	int addBill(Bill bill);
	/**
	 * 获取单个订单
	 * @param id
	 * @return
	 */
	Bill getBillById(@Param("id")int id);
	/**
	 * 修改订单
	 * @param id
	 * @return
	 */
	int updateBillById(Bill bill);
}
