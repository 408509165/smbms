package cn.smbms.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *  mybatis工具类 ，获取SqlSession,关闭SqlSession 
 */
public class MybatisUtil {
	//sqlSessionFactory
	private static SqlSessionFactory sessionFactory ;
	//利用static代码块来加载 配置并获取SqlSessionFactoryBuilder
	static{
		 String resource = "mybatis-config.xml";
		 InputStream in ;
    	//读取java项目中src下的文件成输入流   
		try {
			in = Resources.getResourceAsStream(resource);
			//2)创建 SqlSessionFactoryBuilder
			SqlSessionFactoryBuilder sfb = 
					new SqlSessionFactoryBuilder();
			//3)创建SqlSessionFactory
			sessionFactory = sfb.build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
   //获取SqlSession
	public static SqlSession currentSession(){
		//4)创建SqlSession
		 SqlSession ss = sessionFactory.openSession(true);
		return ss;
	}
   //关闭SqlSession
	public static void colseSession(SqlSession session){
		if(session != null){
			session.close();
		}
	}
}
