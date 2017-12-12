package cn.smbms.dao.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.util.MybatisUtil;
import cn.smbms.util.Page;

public class UserService {
	private static UserService service=null;
	public synchronized static UserService getUserService(){
		if(null==service){
			service=new UserService();
		}
		return service;
	}
	
	
	public User login(String userCode,String userPassword){
		User user=null;
		SqlSession sqlsession=null;
		sqlsession=MybatisUtil.currentSession();
		UserMapper mapper=sqlsession.getMapper(UserMapper.class);
		user=mapper.login(userCode, userPassword);
		sqlsession.close();
		return user;
	}
	public List<User> getUserList(String name,int rid,Page page){
		List<User> list=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		list=mapper.getUserList(name, rid,page.getPageSize(),page.getLimit());
		sqlSession.close();
		return list;
	}
	public User getUserById(int id){
		User user=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		user=mapper.getUserById(id);
		sqlSession.close();
		return user;
	}
	
	public int deleteUser(int id){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		row=mapper.deleteUser(id);
		sqlSession.close();
		return row;
	}
	public int addUser(User user){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		row=mapper.addUser(user);
		sqlSession.close();
		return row;
	}
	public int updateUser(User user){
		int row=0;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		row=mapper.updateUser(user);
		sqlSession.close();
		return row;
	}
	public List<User> getUserList1(int [] rids){
		List<User> list=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		list=mapper.getUserList1(rids);
		sqlSession.close();
		return list;
	}
	public List<User> getUserList2(List rids){
		List<User> list=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		list=mapper.getUserList2(rids);
		sqlSession.close();
		return list;
	}
	public List<User> getUserList3(Map map){
		List<User> list=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		list=mapper.getUserList3(map);
		sqlSession.close();
		return list;
	}
	public String getPwd(int id){
		String pwd="";
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		pwd=mapper.getPwd(id).toString();
		sqlSession.close();
		return pwd;
	}
	public int modifyPwd(String pwd,int id){
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		int row=mapper.modifyPwd(pwd,id);
		sqlSession.close();
		
		return row;
	}
	
	/*public User login1(User user){
		User u=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		u=mapper.login1(user);
		sqlSession.close();
		return u;
	}
	public User login2(Map<String,String> map){
		User u=null;
		SqlSession sqlSession=MybatisUtil.currentSession();
		UserMapper mapper=sqlSession.getMapper(UserMapper.class);
		u=mapper.login2(map);
		sqlSession.close();
		return u;
	}*/
	
}
