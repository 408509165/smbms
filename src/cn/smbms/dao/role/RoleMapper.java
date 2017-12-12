package cn.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;

/**
 *  角色dao接口
 */
public interface RoleMapper {
	/**
	 * 获取角色列表
	 * @return
	 */
	List<Role> getList(@Param("pageSize")int pageSize,@Param("limit")int limit);
	/**
	 * 新增
	 * @return
	 */
	int saveRole(Role role);
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	int deleteRole(int id);
	/**
	 * 获取一个角色全部信息
	 * @param id
	 * @return
	 */
	Role getRole(int id);
	/**
	 * 根据id修改角色的名称
	 * @param roleName
	 * @param id
	 * @return
	 */
	int updateRoleName(@Param("roleName") String roleName,@Param("id")int id);
	/**
	 * 根据角色id查出角色，并返回拥有该角色的用户
	 * @param rid
	 * @return
	 */
	Role getRoleAndUserInfoByRID(@Param("rid")int rid);
	
}
