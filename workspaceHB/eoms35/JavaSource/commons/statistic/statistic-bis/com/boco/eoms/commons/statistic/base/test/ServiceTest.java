package com.boco.eoms.commons.statistic.base.test;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.util.StatUtil;




/*
 * Created on 2008-1-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceTest  {
	
	 public static void main(String a[]){
		 
		 String s = "1234567where123123where鹅头";
		 System.out.println( s.replaceAll("where2", "where ok "));
		 System.out.println( s);
		 
    	System.out.println(
				"<eoms:id2nameDB id="
					+""+" beanId="+""+"/>" );
    	
    	
    	Map map = new HashMap();
    	map.put("a", "10");
    	map.put("b", "5");
    	try {
			System.out.println(
					
			StatUtil.getExpressionResult(map, "new Integer(a)-new Integer(b)", null)
			);
			
			
			System.out.println(
					
					StatUtil.getExpressionResult(map, "@java.lang.Math@round(new Double(b)*100 /a)+new String(\"%\")", null)
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 	
    }

}
