package cn.smbms.dao.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.util.Page;
@Service
public class UserService {
	private UserMapper mapper;
	
	public UserMapper getMapper() {
		return mapper;
	}
	@Resource(name="userMapper")
	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public User login(String userCode,String userPassword){
		User user=null;
		user=mapper.login(userCode, userPassword);
		return user;
	}
	public List<User> getUserList(String name,int rid,Page page){
		List<User> list=null;
		list=mapper.getUserList(name, rid,page.getPageSize(),page.getLimit());
		return list;
	}
	public User getUserById(int id){
		User user=null;
		user=mapper.getUserById(id);
		return user;
	}
	
	public int deleteUser(int id){
		int row=0;
		row=mapper.deleteUser(id);
		return row;
	}
	public int addUser(User user){
		int row=0;
		row=mapper.addUser(user);
		return row;
	}
	public int updateUser(User user){
		int row=0;
		row=mapper.updateUser(user);
		return row;
	}
	public List<User> getUserList1(int [] rids){
		List<User> list=null;
		list=mapper.getUserList1(rids);
		return list;
	}
	public List<User> getUserList2(List rids){
		List<User> list=null;
		list=mapper.getUserList2(rids);
		return list;
	}
	public List<User> getUserList3(Map map){
		List<User> list=null;
		list=mapper.getUserList3(map);
		return list;
	}
	public String getPwd(int id){
		String pwd="";
		pwd=mapper.getPwd(id).toString();
		return pwd;
	}
	public int modifyPwd(String pwd,int id){
		int row=mapper.modifyPwd(pwd,id);
		
		return row;
	}
}
