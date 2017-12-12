package cn.smbms.dao.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSONArray;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.util.MybatisUtil;
import cn.smbms.util.Page;
/**
 * 业务处理类
 * @author SunShine
 *
 */
public class RoleService{
	private static RoleService service=null;
	public synchronized static RoleService getRoleService(){
		if(null==service){
			service=new RoleService();
		}
		return service;
	}
	/**
	 * 获取角色列表
	 */
	public List<Role> getList(Page page) {
		List<Role> roleList=null;
		SqlSession sqlsession=null;
		sqlsession=MybatisUtil.currentSession();
		RoleMapper mapper=sqlsession.getMapper(RoleMapper.class);
		roleList=mapper.getList(page.getPageSize(),page.getLimit());
		sqlsession.close();
		return roleList;
	}
	/**
	 * 新增
	 * @return
	 */
	public int saveRole(Role role){
		int row=0;
		SqlSession sqlsession=null;
		try {
			sqlsession=MybatisUtil.currentSession();
			RoleMapper mapper=sqlsession.getMapper(RoleMapper.class);
			row=mapper.saveRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlsession.close();
		}
		return row;
	}
	public int deleteRole(int id){
		int row=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.currentSession();
			RoleMapper mapper=sqlSession.getMapper(RoleMapper.class);
			row =mapper.deleteRole(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}

		return row;
	}
	public Role getRole(int id){
		Role role=null;
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.currentSession();
			RoleMapper mapper=sqlSession.getMapper(RoleMapper.class);
			role =mapper.getRole(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}

		return role;
	}
	public int updateRoleName(String roleName,int id){
		int row=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=MybatisUtil.currentSession();
			RoleMapper mapper=sqlSession.getMapper(RoleMapper.class);
			row=mapper.updateRoleName(roleName, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return row;
	}

	/**
	 * 获取下拉列表
	 */
	public JSONArray getJsonList() {
		JSONArray jArray=new JSONArray();
		Role role=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		RoleMapper mapper=sqlSession.getMapper(RoleMapper.class);
		List<Role> list=mapper.getList(0,0);
		for (Role item : list) {
			role=new Role();
			int id=item.getId();
			String roleName=item.getRoleName();
			role.setId(id);
			role.setRoleName(roleName);
			jArray.add(role);
		}
		sqlSession.close();
		return jArray;
	}
	public Role getRoleAndUserInfoByRID(int rid){
		Role role=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		RoleMapper mapper=sqlSession.getMapper(RoleMapper.class);
		role=mapper.getRoleAndUserInfoByRID(rid);
		sqlSession.close();
		return role;
	}

	public static void main(String[] args) {
		Role role=RoleService.getRoleService().getRoleAndUserInfoByRID(2);
		List<User> user=role.getUserList();
		for (User user2 : user) {
			System.out.println(user2.getUserName());
		}

		//	RoleService service=new RoleService();
		//	for (Object obj : service.getJsonList()) {
		//		System.out.println(obj);
		//	}

		/*
		List<Role> roleList=service.getList();
		for (Role role : roleList) {
			System.out.println(role.toString());
		}*/
		/*Role role=new Role();
		role.setRoleCode("1");
		role.setRoleName("离职员工");
		System.out.println(service.saveRole(role));*/
		/*System.out.println(service.deleteRole(13));*/
		/*System.out.println(service.getRole(1).toString());*/
		/*System.out.println(service.updateRoleName("孙国强22", 22));*/
	}

}
