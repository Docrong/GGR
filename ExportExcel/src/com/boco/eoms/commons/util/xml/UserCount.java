package com.boco.eoms.commons.util.xml;
/**
 * 单例类,控制同时下载用户数
 * @author gr
 *
 */
public class UserCount {

	private static UserCount user=new UserCount();

	private static int count=0;//下载用户数
	private static int maxCount=10;//最大下载用户数
	
	public static UserCount getUser(){
		return user;
	}
	
	public static int getCount(){
		return count;
	}
	public static void setCount(){
		count=maxCount;
	}
	public static int getMaxCount(){
		return maxCount;
	}
	
	public synchronized void login(){
		count++;
	}
	
	public synchronized void logout(){
		count--;
	}

}
