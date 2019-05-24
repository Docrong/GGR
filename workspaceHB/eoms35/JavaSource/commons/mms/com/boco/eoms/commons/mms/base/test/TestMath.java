package com.boco.eoms.commons.mms.base.test;

import java.text.DecimalFormat;

public class TestMath {
	
	public static void main(String[] dsss)
	{
		double dd = 0.09090909090909091;
		java.text.DecimalFormat   df   =   new   DecimalFormat("#.####");   
//	    float   d   =   5002.412162162162;   
	    
	    String newStr = df.format(dd);   
	    System.out.println(newStr);
		System.out.print(Math.rint(dd));
	}
}
