package cn.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;

/**
 *  ��ɫdao�ӿ�
 */
public interface RoleMapper {
	/**
	 * ��ȡ��ɫ�б�
	 * @return
	 */
	List<Role> getList(@Param("pageSize")int pageSize,@Param("limit")int limit);
	/**
	 * ����
	 * @return
	 */
	int saveRole(Role role);
	/**
	 * ɾ����ɫ
	 * @param id
	 * @return
	 */
	int deleteRole(int id);
	/**
	 * ��ȡһ����ɫȫ����Ϣ
	 * @param id
	 * @return
	 */
	Role getRole(int id);
	/**
	 * ����id�޸Ľ�ɫ������
	 * @param roleName
	 * @param id
	 * @return
	 */
	int updateRoleName(@Param("roleName") String roleName,@Param("id")int id);
	/**
	 * ���ݽ�ɫid�����ɫ��������ӵ�иý�ɫ���û�
	 * @param rid
	 * @return
	 */
	Role getRoleAndUserInfoByRID(@Param("rid")int rid);
	
}
