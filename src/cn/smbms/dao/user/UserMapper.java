package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import cn.smbms.pojo.User;

//�û� dao �ӿ�
public interface UserMapper {
	/**
	 * ��¼*
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	//�������¼
	User login(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
	/*//user�����¼
	User login1(User user);
	//map��¼
	User login2(Map<String, String> userMap);*/
	
	int modifyPwd(@Param("pwd")String pwd,@Param("id")int id);
	
	List<User> getUserList(@Param("username")String username,@Param("rid")int rid,@Param("pageSize")int pageSize,@Param("limit")int limit);
	//��
	User getUserById(int id);
	//��
	int addUser(User user);
	//��
	int updateUser(User user);
	//ɾ
	int deleteUser(@Param("id")int id);
	
	List<User> getUserList1(int [] rids);//����
	List<User> getUserList2(List rids);//����
	List<User> getUserList3(Map rids);//�����
	
	String getPwd(int id);
	
}
