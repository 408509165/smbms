package cn.smbms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import cn.smbms.pojo.User;

//用户 dao 接口
public interface UserMapper {
	/**
	 * 登录*
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	//多参数登录
	User login(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
	/*//user对象登录
	User login1(User user);
	//map登录
	User login2(Map<String, String> userMap);*/
	
	int modifyPwd(@Param("pwd")String pwd,@Param("id")int id);
	
	List<User> getUserList(@Param("username")String username,@Param("rid")int rid,@Param("pageSize")int pageSize,@Param("limit")int limit);
	//查
	User getUserById(int id);
	//增
	int addUser(User user);
	//改
	int updateUser(User user);
	//删
	int deleteUser(@Param("id")int id);
	
	List<User> getUserList1(int [] rids);//数组
	List<User> getUserList2(List rids);//集合
	List<User> getUserList3(Map rids);//数组查
	
	String getPwd(int id);
	
}
