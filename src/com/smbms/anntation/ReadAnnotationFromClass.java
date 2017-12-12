package com.smbms.anntation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 *  ��ָ�����ж��������������е�ע���value
 * @author SunShine
 * 
 */
public class ReadAnnotationFromClass {
	
	/**
	 * 
	 * @param baseClass ��Ҫ��������
	 * @param methodName ��Ҫ�����ķ�������
	 * @param annotationClass ��Ҫ������ע������
	 * @return
	 */
	public static String getValue(Class baseClass,String methodName,Class annotationClass){
		//��ȡ��������ڰ�
		Package package1=baseClass.getPackage();
		//System.out.println(package1.getName());
		//��ȡ����������ַ
		String className=baseClass.getName();
		
		try {
			//ͨ������ؼ��ظ���
			Class c=Class.forName(className);
			//��ȡ��������з�����ʶ�����Ҫ����ע��ķ�����
			Method [] methods = c.getMethods();
			for (Method method : methods) {
				if(method.getName().equals(methodName)){
					//�ҵ������еĸ÷��� System.out.println(method.getName());
					//�����÷����Ƿ���Ŀ��ע�� 
					boolean isExits = method.isAnnotationPresent(annotationClass);
					if(isExits){
						//��ȡ��ע��
						Annotation annotation =method.getAnnotation(annotationClass);
						Class c1= annotation.annotationType(); //�õ���ע������� 
						//�õ�����İ�·�� System.out.println(c1.getName());
						String annotationPlace=c1.getName();
						//���ظ��࣬��ø���Ķ���
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
