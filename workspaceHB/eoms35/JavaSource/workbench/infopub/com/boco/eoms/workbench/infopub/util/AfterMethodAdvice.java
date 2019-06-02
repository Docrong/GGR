package com.boco.eoms.workbench.infopub.util;

import org.springframework.aop.AfterReturningAdvice;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.workbench.infopub.test.aop.Customer;

import java.lang.reflect.Method;
import java.util.List;

public class AfterMethodAdvice implements AfterReturningAdvice {
	/**
	 * 不进行记录的方法
	 */
	private List exclude;

	
    public void setExclude(List exclude) {
		this.exclude = exclude;
	}


	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println(exclude.size());
		System.out.println(StaticMethod.method2class(method));
    	if (exclude.contains(StaticMethod.method2class(method))) {
            Customer customer = (Customer) args[0];
            System.out.println("店员:Thank you " + customer.getName() + " . Come again! " );
    	}
    	else return ;
     
   }

}
