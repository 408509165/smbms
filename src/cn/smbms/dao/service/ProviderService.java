package cn.smbms.dao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.util.Page;

@Service
public class ProviderService {
	private ProviderMapper mapper;
	
	
	public ProviderMapper getMapper() {
		return mapper;
	}
	@Resource(name="providerMapper")
	public void setMapper(ProviderMapper mapper) {
		this.mapper = mapper;
	}

	public List<Provider> getProviderList(String proCode,String proName,Page page){
		List<Provider> list=null;
		list=mapper.getProviderList(proCode,proName,page.getPageSize(),page.getLimit());
		return list;
	}
	
	public JSONArray getProviderIdAndProName(){
		JSONArray jArray=new JSONArray();
		Provider pro=null;
		List<Provider> list=mapper.getProviderIdAndProName();
		for (Provider provider : list) {
			int id=provider.getId();
			String proName=provider.getProName();
			pro=new Provider(id, proName);
			jArray.add(pro);
		}
		return jArray;
	}
	/**
	 * 获取单个供应商信息
	 * @param id
	 * @return
	 */
	public Provider getProviderById(int id){
		Provider provider=null;
		provider=mapper.getProviderById(id);
		return provider;
	}
	/**
	 * 修改供应商信息
	 * @param id
	 * @return
	 */
	public int updateProviderById(Provider p){
		int row=0;
		row=mapper.updateProviderById(p);
		return row;
	}
	/**
	 * 删除供应商信息
	 * @param id
	 * @return
	 */
	public int deleteProviderById(int id){
		int row=0;
		row=mapper.deleteProviderById(id);
		return row;
	}
	/**
	 * 添加供应商
	 * @param provider
	 * @return
	 */
	public int addProvider(Provider provider){
		int row=0;
		row=mapper.addProvider(provider);
		return row;
	}
}
