package cn.smbms.dao.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.util.Page;
/**
 * ҵ������
 * @author SunShine
 *
 */
@Service
public class RoleService{
	private RoleMapper mapper;

	public RoleMapper getMapper() {
		return mapper;
	}
	@Resource(name="roleMapper")
	public void setMapper(RoleMapper mapper) {
		this.mapper = mapper;
	}
	/**
	 * ��ȡ��ɫ�б�
	 */
	public List<Role> getList(Page page) {
		List<Role> roleList=null;
		roleList=mapper.getList(page.getPageSize(),page.getLimit());
		return roleList;
	}
	/**
	 * ����
	 * @return
	 */
	public int saveRole(Role role){
		int row=0;
		row=mapper.saveRole(role);
		return row;
	}
	public int deleteRole(int id){
		int row=0;
		row =mapper.deleteRole(id);
		return row;
	}
	public Role getRole(int id){
		Role role=null;
		role =mapper.getRole(id);
		return role;
	}
	public int updateRoleName(String roleName,int id){
		int row=0;
		row=mapper.updateRoleName(roleName, id);
		return row;
	}

	/**
	 * ��ȡ�����б�
	 */
	public JSONArray getJsonList() {
		JSONArray jArray=new JSONArray();
		Role role=null;
		List<Role> list=mapper.getList(0,0);
		for (Role item : list) {
			role=new Role();
			int id=item.getId();
			String roleName=item.getRoleName();
			role.setId(id);
			role.setRoleName(roleName);
			jArray.add(role);
		}
		return jArray;
	}
	public Role getRoleAndUserInfoByRID(int rid){
		Role role=null;
		role=mapper.getRoleAndUserInfoByRID(rid);
		return role;
	}
}
