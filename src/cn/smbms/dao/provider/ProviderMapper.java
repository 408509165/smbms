package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;


public interface ProviderMapper {
	/**
	 * ��ѯ���й�Ӧ��
	 * @return
	 */
	List<Provider> getProviderList(@Param("proCode")String proCode,@Param("proName")String proName,@Param("pageSize")int pageSize,@Param("limit")int limit);
	/**
	 * ��ȡ��Ӧ��id������
	 * @return
	 */
	List<Provider> getProviderIdAndProName();
	/**
	 * ����idɾ����Ӧ��
	 * @param id
	 * @return
	 */
	int deleteProviderById(int id);
	/**
	 * ������Ӧ��
	 * @param bill
	 * @return
	 */
	int addProvider(Provider provider);
	/**
	 * ��ȡ������Ӧ��
	 * @param id
	 * @return
	 */
	Provider getProviderById(@Param("id")int id);
	/**
	 * �޸Ĺ�Ӧ��
	 * @param id
	 * @return
	 */
	int updateProviderById(Provider p);
}
