package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;


public interface ProviderMapper {
	/**
	 * 查询所有供应商
	 * @return
	 */
	List<Provider> getProviderList(@Param("proCode")String proCode,@Param("proName")String proName,@Param("pageSize")int pageSize,@Param("limit")int limit);
	/**
	 * 获取供应商id和名称
	 * @return
	 */
	List<Provider> getProviderIdAndProName();
	/**
	 * 根据id删除供应商
	 * @param id
	 * @return
	 */
	int deleteProviderById(int id);
	/**
	 * 新增供应商
	 * @param bill
	 * @return
	 */
	int addProvider(Provider provider);
	/**
	 * 获取单个供应商
	 * @param id
	 * @return
	 */
	Provider getProviderById(@Param("id")int id);
	/**
	 * 修改供应商
	 * @param id
	 * @return
	 */
	int updateProviderById(Provider p);
}
