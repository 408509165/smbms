package cn.smbms.dao.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.util.MybatisUtil;
import cn.smbms.util.Page;

public class ProviderService {
	private static ProviderService service=null;
	public synchronized static ProviderService getProviderService(){
		if(null==service){
			service=new ProviderService();
		}
		return service;
	}
	
	public List<Provider> getProviderList(String proCode,String proName,Page page){
		List<Provider> list=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		list=mapper.getProviderList(proCode,proName,page.getPageSize(),page.getLimit());
		sqlSession.close();
		return list;
	}
	
	public JSONArray getProviderIdAndProName(){
		JSONArray jArray=new JSONArray();
		Provider pro=null;
		
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		List<Provider> list=mapper.getProviderIdAndProName();
		for (Provider provider : list) {
			int id=provider.getId();
			String proName=provider.getProName();
			pro=new Provider(id, proName);
			jArray.add(pro);
		}
		sqlSession.close();
		return jArray;
	}
	/**
	 * 获取单个供应商信息
	 * @param id
	 * @return
	 */
	public Provider getProviderById(int id){
		Provider provider=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		provider=mapper.getProviderById(id);
		sqlSession.close();
		return provider;
	}
	/**
	 * 修改供应商信息
	 * @param id
	 * @return
	 */
	public int updateProviderById(Provider p){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		row=mapper.updateProviderById(p);
		sqlSession.close();
		return row;
	}
	/**
	 * 删除供应商信息
	 * @param id
	 * @return
	 */
	public int deleteProviderById(int id){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		row=mapper.deleteProviderById(id);
		sqlSession.close();
		return row;
	}
	/**
	 * 添加供应商
	 * @param provider
	 * @return
	 */
	public int addProvider(Provider provider){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		ProviderMapper mapper=sqlSession.getMapper(ProviderMapper.class);
		row=mapper.addProvider(provider);
		sqlSession.close();
		return row;
	}
}
