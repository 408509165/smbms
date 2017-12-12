package com.smbms.anntation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 *  从指定类中读检索该类所含有的注解的value
 * @author SunShine
 * 
 */
public class ReadAnnotationFromClass {
	
	/**
	 * 
	 * @param baseClass 需要检索的类
	 * @param methodName 需要检索的方法名称
	 * @param annotationClass 需要检索的注解名称
	 * @return
	 */
	public static String getValue(Class baseClass,String methodName,Class annotationClass){
		//获取该类的所在包
		Package package1=baseClass.getPackage();
		//System.out.println(package1.getName());
		//获取该类的物理地址
		String className=baseClass.getName();
		
		try {
			//通过类加载加载该类
			Class c=Class.forName(className);
			//获取该类的所有方法，识别出需要检索注解的方法。
			Method [] methods = c.getMethods();
			for (Method method : methods) {
				if(method.getName().equals(methodName)){
					//找到该类中的该方法 System.out.println(method.getName());
					//检索该方法是否含有目标注解 
					boolean isExits = method.isAnnotationPresent(annotationClass);
					if(isExits){
						//获取该注解
						Annotation annotation =method.getAnnotation(annotationClass);
						Class c1= annotation.annotationType(); //得到该注解的类型 
						//得到该类的包路径 System.out.println(c1.getName());
						String annotationPlace=c1.getName();
						//加载该类，获得该类的对象
						Class c2=Class.forName(annotationPlace);
						
						System.out.println(annotation.toString());
						
						Method[] valueMethod=c2.getMethods();
						for (Method method2 : valueMethod) {
							//System.out.println(method2.getName());
						}
						
					}
					
				}
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "";
	}
}
