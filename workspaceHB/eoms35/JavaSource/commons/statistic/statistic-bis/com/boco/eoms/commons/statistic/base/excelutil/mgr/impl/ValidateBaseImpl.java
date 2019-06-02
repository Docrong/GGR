package com.boco.eoms.commons.statistic.base.excelutil.mgr.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.boco.eoms.commons.statistic.base.excelutil.mgr.IValidate;

/**
 * 基础验证类
 * @author lizhenyou
 *
 */
public class ValidateBaseImpl implements IValidate {
	/**
	 * 验证obj 中的所有属性不能为null或""
	 * @param obj 验证对象
	 * @return true:验证成功，fasle:失败
	 */
	public boolean validate(Object obj) {
		boolean f = true;
		Method[]  methods = obj.getClass().getDeclaredMethods();
		for(int i=0;i<methods.length;i++)
		{
			String m = methods[i].getName();
			if(m.startsWith("get") || m.startsWith("is"))
			{
				Object o = null;
				try {
					o = methods[i].invoke(obj,null);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				if(o == null || "".equalsIgnoreCase(o.toString()))
				{
					f = false; 
					break;
				}
			}
		}
		
		return f;
	}
	
//测试使用
	private String aaa = "";
	
	private boolean bbb;
	
	private String ccc = null;
	
	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public boolean isBbb() {
		return bbb;
	}

	public void setBbb(boolean bbb) {
		this.bbb = bbb;
	}

	public static void main(String[] args)
	{
		ValidateBaseImpl vbi = new ValidateBaseImpl();
		vbi.validate(vbi);
	}
}
