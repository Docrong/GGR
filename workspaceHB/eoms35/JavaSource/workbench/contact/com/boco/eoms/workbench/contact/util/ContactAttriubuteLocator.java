package com.boco.eoms.workbench.contact.util;

import java.io.File;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workbench.contact.util.ContactAttributes;;
/**
 * 
 * <p>
 * Title:个人通讯录
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
public class ContactAttriubuteLocator {
	
	public static ContactAttributes getNetDiskAttributes() {
		ContactAttributes attributes = (ContactAttributes) ApplicationContextHolder
				.getInstance().getBean("contactAttributes");
		return attributes;
	}
 
}
