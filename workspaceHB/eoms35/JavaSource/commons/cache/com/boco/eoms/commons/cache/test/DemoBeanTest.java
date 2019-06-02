package com.boco.eoms.commons.cache.test;

import java.util.Date;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.cache.application.ApplicationCacheMgr;
import com.boco.eoms.commons.cache.sample.DemoBean;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 1, 2007 9:21:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DemoBeanTest extends ConsoleTestCase {
	ApplicationCacheMgr mgr = null;

	DemoBean bean = null;

	DemoBean flushBean = null;

	private static final String CACHE_NAME = "com.boco.eoms.DEMO_CACHE";

	protected void onSetUpInTransaction() throws Exception {
		super.setUp();
		mgr = (ApplicationCacheMgr) this.getBean("ApplicationCacheMgr");
		bean = (DemoBean) this.getBean("DemoBean");
		flushBean = (DemoBean) this.getBean("DemoFlushBean");
	}



	public void testGetDate() throws Exception {
		Date date1 = bean.getDate();
		Thread.sleep(1000);
		Date date2 = bean.getDate();
		assertSame(date1, date2);
		mgr.flush();
		Date date3 = bean.getDate();
		assertNotSame(date1, date3);
	}

	public void testConfigXml() throws Exception {
		assertEquals(10001, mgr.getMaxElementsInMemory(CACHE_NAME));
		assertEquals(true, mgr.isEternal(CACHE_NAME));
	}

	public void testGetRandom() throws Exception {
		int random1 = bean.getRandom();
		int random2 = bean.getRandom();
		assertEquals(random1, random2);
		mgr.flush();
		int random3 = bean.getRandom();
		assertNotSame(new Integer(random1), new Integer(random3));
	}

	public void testAddDate() throws Exception {
		// 根据applicationContext-ehcache.xml配置调用addDate方法时需更新getDate()方法缓存。
		Date date1 = bean.getDate();
		Date date2 = bean.getDate();
		// 前两方法应使用缓存
		assertSame(date1, date2);
		// this.assertEquals(date1, date2);
		// 调用addDate()方法需刷新getDate()缓存
		flushBean.addDate();
		Date date3 = bean.getDate();
		assertNotSame(date2, date3);
	}

}
