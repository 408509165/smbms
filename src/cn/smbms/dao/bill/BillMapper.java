package cn.smbms.dao.bill;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Role;

/**
 *  ��ɫdao�ӿ�
 */
public interface BillMapper {
	/**
	 * ��ѯ���ж���
	 * @return
	 */
	List<Bill> getBillList(@Param("proName")String proName,@Param("proId")int proId,@Param("isPay")int isPay,@Param("pageSize")int pageSize,@Param("limit")int limit);
	
	List<Bill> getBillList1(String billCode);
	/**
	 * ����idɾ������
	 * @param id
	 * @return
	 */
	int deleteBillById(int id);
	/**
	 * ��������
	 * @param bill
	 * @return
	 */
	int addBill(Bill bill);
	/**
	 * ��ȡ��������
	 * @param id
	 * @return
	 */
	Bill getBillById(@Param("id")int id);
	/**
	 * �޸Ķ���
	 * @param id
	 * @return
	 */
	int updateBillById(Bill bill);
}
