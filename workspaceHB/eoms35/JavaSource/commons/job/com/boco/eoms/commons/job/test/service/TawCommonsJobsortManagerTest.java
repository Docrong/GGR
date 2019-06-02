package com.boco.eoms.commons.job.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.service.impl.TawCommonsJobsortManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:任务类型serivce测试类
 * </p>
 * <p>
 * Apr 10, 2007 10:54:22 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsJobsortManagerTest extends BaseManagerTestCase {
	private TawCommonsJobsortManagerImpl tawCommonsJobsortManager = new TawCommonsJobsortManagerImpl();

	private Mock tawCommonsJobsortDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonsJobsortDao = new Mock(TawCommonsJobsortDao.class);
		tawCommonsJobsortManager
				.setTawCommonsJobsortDao((TawCommonsJobsortDao) tawCommonsJobsortDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonsJobsortManager = null;
	}

	/**
	 * 测试任务类型的获取
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobsorts() throws Exception {
		List results = new ArrayList();
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		results.add(tawCommonsJobsort);

		// set expected behavior on dao
		tawCommonsJobsortDao.expects(once()).method("getTawCommonsJobsorts")
				.will(returnValue(results));

		List tawCommonsJobsorts = tawCommonsJobsortManager
				.getTawCommonsJobsorts(null);
		assertTrue(tawCommonsJobsorts.size() == 1);
		tawCommonsJobsortDao.verify();
	}

	/**
	 * 测试根据ID任务类型的获取
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */

	public void testGetTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsortTemp = new TawCommonsJobsort();
		java.lang.Integer id = new Integer(1234);
		tawCommonsJobsortTemp.setId(id);
		// set expected behavior on dao
		tawCommonsJobsortDao.expects(once()).method("getTawCommonsJobsort")
				.with(eq(id)).will(returnValue(tawCommonsJobsortTemp));
		TawCommonsJobsort tawCommonsJobsort = tawCommonsJobsortManager
				.getTawCommonsJobsort(String.valueOf(id));
		assertTrue(tawCommonsJobsort != null);
		assertEquals(id, tawCommonsJobsort.getId());
		tawCommonsJobsortDao.verify();
	}

	/**
	 * 测试任务类型的保存
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testSaveTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		java.lang.Integer id = new Integer(1234);
		tawCommonsJobsort.setId(id);
		tawCommonsJobsort.setMaxExecuteTime(new Integer(1111));
		// set expected behavior on dao
		tawCommonsJobsortDao.expects(once()).method("getTawCommonsJobsort")
				.with(eq(id)).will(returnValue(tawCommonsJobsort));
		tawCommonsJobsort = tawCommonsJobsortManager
				.getTawCommonsJobsort(String.valueOf(id));
		assertEquals(new Integer(1111), tawCommonsJobsort.getMaxExecuteTime());

		tawCommonsJobsortDao.expects(once()).method("saveTawCommonsJobsort")
				.with(same(tawCommonsJobsort)).isVoid();
		tawCommonsJobsort.setMaxExecuteTime(new Integer(5678));
		tawCommonsJobsortManager.saveTawCommonsJobsort(tawCommonsJobsort);

		tawCommonsJobsortDao.expects(once()).method("getTawCommonsJobsort")
				.with(eq(id)).will(returnValue(tawCommonsJobsort));
		tawCommonsJobsort = tawCommonsJobsortManager
				.getTawCommonsJobsort(String.valueOf(id));
		assertEquals(new Integer(5678), tawCommonsJobsort.getMaxExecuteTime());
		tawCommonsJobsortDao.verify();

	}

	/**
	 * 测试任务类型的增加与删除
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */

	public void testAddAndRemoveTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		java.lang.Integer id = new Integer(1234);
		tawCommonsJobsort.setId(id);
		// set required fields
		tawCommonsJobsort.setDeleted(new Integer(678456884));
		tawCommonsJobsort
				.setJobClassName("IaFbInQbRzMgWcRdJhGdUrVuPoJeHrYvGhPhDtZwGoEnNnGmMh");
		tawCommonsJobsort
				.setJobSortName("BrBiDgXnCtIkHzPhBzZkIcLbRoGqRgQwRdAfEzYiEsXyLnPkBy");

		// set expected behavior on dao
		tawCommonsJobsortDao.expects(once()).method("saveTawCommonsJobsort")
				.with(same(tawCommonsJobsort)).isVoid();
		tawCommonsJobsortManager.saveTawCommonsJobsort(tawCommonsJobsort);
		tawCommonsJobsortDao.verify();

		// reset expectations
		tawCommonsJobsortDao.reset();

		tawCommonsJobsortDao.expects(once()).method("removeTawCommonsJobsort")
				.with(eq(id));
		tawCommonsJobsortManager.removeTawCommonsJobsort(String.valueOf(id));
		tawCommonsJobsortDao.verify();

		// reset expectations
		tawCommonsJobsortDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawCommonsJobsort.class, tawCommonsJobsort.getId());
		tawCommonsJobsortDao.expects(once()).method("removeTawCommonsJobsort")
				.isVoid();
		tawCommonsJobsortDao.expects(once()).method("getTawCommonsJobsort")
				.will(throwException(ex));
		tawCommonsJobsortManager.removeTawCommonsJobsort(String.valueOf(id));
		try {
			tawCommonsJobsortManager.getTawCommonsJobsort(String.valueOf(id));
			fail("TawCommonsJobsort with identifier '" + String.valueOf(id)
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawCommonsJobsortDao.verify();
	}
}
