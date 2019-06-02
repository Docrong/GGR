package com.boco.eoms.im.adaptor.util;

import com.boco.eoms.base.util.ApplicationContextHolder;

public class Imlocator {
	
	public static Imconfig ImConfigInstance(){
		return (Imconfig) ApplicationContextHolder.getInstance().getBean("imconfig");
		
	}

}
