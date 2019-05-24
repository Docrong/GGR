package com.boco.eoms.db.test.hibernate;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;

import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.model.TawwpAddonsTable;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 10, 2008 5:07:34 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class HibernateUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUtil() {
		Session s = HibernateUtil.currentSession();
		String hSql = "";
		hSql = "from TawwpAddonsTable";
		Query query = s.createQuery(hSql);		
		List list=query.list();
		for(Iterator it=list.iterator();it.hasNext();){
			TawwpAddonsTable table=(TawwpAddonsTable)it.next();
			System.out.println(table.getId());
			System.out.println(table.getName());
		}
		assertNotNull(list);
	}

	/*
	 * public void testCurrentSession() { fail("Not yet implemented"); }
	 * 
	 * public void testCurrentSessionBoolean() { fail("Not yet implemented"); }
	 * 
	 * public void testCurrentCommitSession() { fail("Not yet implemented"); }
	 * 
	 * public void testGetCurrentSession() { fail("Not yet implemented"); }
	 * 
	 * public void testCurrentTransaction() { fail("Not yet implemented"); }
	 * 
	 * public void testCommitTransaction() { fail("Not yet implemented"); }
	 * 
	 * public void testRollbackTransaction() { fail("Not yet implemented"); }
	 * 
	 * public void testCloseSession() { fail("Not yet implemented"); }
	 * 
	 * public void testCloseCommitSession() { fail("Not yet implemented"); }
	 * 
	 */
}
