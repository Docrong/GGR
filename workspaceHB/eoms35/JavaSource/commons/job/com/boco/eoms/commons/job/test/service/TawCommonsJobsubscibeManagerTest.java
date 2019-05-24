package com.boco.eoms.commons.job.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.service.impl.TawCommonsJobsubscibeManagerImpl;

import org.jmock.Mock;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:任务订阅serivce测试类
 * </p>
 * <p>Apr 10, 2007 10:54:46 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobsubscibeManagerTest extends BaseManagerTestCase {
	private TawCommonsJobsubscibeManagerImpl tawCommonsJobsubscibeManager = new TawCommonsJobsubscibeManagerImpl();

	private Mock tawCommonsJobsubscibeDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonsJobsubscibeDao = new Mock(TawCommonsJobsubscibeDao.class);
		tawCommonsJobsubscibeManager
				.setTawCommonsJobsubscibeDao((TawCommonsJobsubscibeDao) tawCommonsJobsubscibeDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonsJobsubscibeManager = null;
	}
	  /**
	    * 测试获取全部任务订阅信息
	    * @throws Exception
	    * @author 秦敏
	    */

	public void testGetTawCommonsJobsubscibes() throws Exception {
		List results = new ArrayList();
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		results.add(tawCommonsJobsubscibe);

		// set expected behavior on dao
		tawCommonsJobsubscibeDao.expects(once()).method(
				"getTawCommonsJobsubscibes").will(returnValue(results));

		List tawCommonsJobsubscibes = tawCommonsJobsubscibeManager
				.getTawCommonsJobsubscibes();
		assertTrue(tawCommonsJobsubscibes.size() == 1);
		tawCommonsJobsubscibeDao.verify();
	}
	   /**
	    * 测试根据ID获取任务订阅信息
	    * @throws Exception
	    * @author 秦敏
	    */

	public void testGetTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibeTemp = new TawCommonsJobsubscibe();
		tawCommonsJobsubscibeTemp.setId("00000");
		// set expected behavior on dao
		tawCommonsJobsubscibeDao.expects(once()).method(
				"getTawCommonsJobsubscibe").will(
				returnValue(tawCommonsJobsubscibeTemp));
		TawCommonsJobsubscibe tawCommonsJobsubscibe = tawCommonsJobsubscibeManager
				.getTawCommonsJobsubscibe("00000");
		assertTrue(tawCommonsJobsubscibe != null);
		assertEquals("00000", tawCommonsJobsubscibe.getId());
		tawCommonsJobsubscibeDao.verify();
	}
    /**
     * 保存任务订阅信息
     * @throws Exception
     * @author 秦敏
     */

	public void testSaveTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		tawCommonsJobsubscibe.setId("1234");
		tawCommonsJobsubscibe.setJobCycle("300000");
		tawCommonsJobsubscibe.setJobSortId(new Integer(3));
		tawCommonsJobsubscibe.setSubId("000000");
		tawCommonsJobsubscibe.setJobType("simple");
		tawCommonsJobsubscibeDao.expects(once()).method(
				"getTawCommonsJobsubscibe").will(
				returnValue(tawCommonsJobsubscibe));
		tawCommonsJobsubscibe = tawCommonsJobsubscibeManager
				.getTawCommonsJobsubscibe("1234");
		assertEquals("300000", tawCommonsJobsubscibe.getJobCycle());

		// set expected behavior on dao
		tawCommonsJobsubscibeDao.expects(once()).method(
				"saveTawCommonsJobsubscibe").with(same(tawCommonsJobsubscibe))
				.isVoid();
		tawCommonsJobsubscibe.setJobCycle("2222");
		tawCommonsJobsubscibeManager.saveTawCommonsJobsubscibe(
				tawCommonsJobsubscibe, false);

		tawCommonsJobsubscibeDao.expects(once()).method(
				"getTawCommonsJobsubscibe").will(
				returnValue(tawCommonsJobsubscibe));
		tawCommonsJobsubscibe = tawCommonsJobsubscibeManager
				.getTawCommonsJobsubscibe("1234");
		assertEquals("2222", tawCommonsJobsubscibe.getJobCycle());
		tawCommonsJobsubscibeDao.verify();
	}

}
