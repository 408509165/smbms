package cn.smbms.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *  mybatis������ ����ȡSqlSession,�ر�SqlSession 
 */
public class MybatisUtil {
	//sqlSessionFactory
	private static SqlSessionFactory sessionFactory ;
	//����static����������� ���ò���ȡSqlSessionFactoryBuilder
	static{
		 String resource = "mybatis-config.xml";
		 InputStream in ;
    	//��ȡjava��Ŀ��src�µ��ļ���������   
		try {
			in = Resources.getResourceAsStream(resource);
			//2)���� SqlSessionFactoryBuilder
			SqlSessionFactoryBuilder sfb = 
					new SqlSessionFactoryBuilder();
			//3)����SqlSessionFactory
			sessionFactory = sfb.build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
   //��ȡSqlSession
	public static SqlSession currentSession(){
		//4)����SqlSession
		 SqlSession ss = sessionFactory.openSession(true);
		return ss;
	}
   //�ر�SqlSession
	public static void colseSession(SqlSession session){
		if(session != null){
			session.close();
		}
	}
}
