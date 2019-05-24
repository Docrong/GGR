/*
 * Created on 2007-11-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.test.dao;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.sheet.base.dao.IMainDAO;

/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MaindaoTest  extends ConsoleTestCase {

	    private IMainDAO dao;

	    public void testQuerySheet() {
        try {
        	Integer i = dao.count(" from ApplyMain");
        	
        	System.out.println("count = "+ i);
	        int[] aTotal = new int[1];
        	List list = dao.getQuerySheetByCondition("from ApplyMain order by sendTime",
        			 new Integer(0), new Integer(3), aTotal,"");
        	System.out.println("test list count = "+list.size());
        	System.out.println("test total = " + aTotal[0]);
        	
        } catch (Exception e) {
	            fail("添加资源策略表失败!!!");
	        }
	    }
}
