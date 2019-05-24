package com.boco.eoms.workbench.memo.util;

import java.io.File;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workbench.contact.util.ContactAttributes;;
/**
 * 
 * <p>
 * Title:  
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-5-14 16:39:25
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
public class MemoAttributeLocator {
	
	public static MemoAttributes getMemoAttributes() {
		MemoAttributes memoAttributes = (MemoAttributes) ApplicationContextHolder
				.getInstance().getBean("memoAttributes");
		return memoAttributes;
	}
 
}
