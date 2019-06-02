package com.boco.eoms.commons.job.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;
import com.boco.eoms.commons.job.exception.ScheduleRunException;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.service.ITawCommonsJobmonitorManager;
import com.boco.eoms.commons.job.service.impl.TawCommonsJobmonitorManagerImpl;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:任务执行serivce测试类
 * </p>
 * <p>
 * Apr 10, 2007 10:53:59 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 * 
 */
public class TawCommonsJobmonitorManagerTest extends BaseManagerTestCase {

	private TawCommonsJobmonitorManagerImpl tawCommonsJobmonitorManager = new TawCommonsJobmonitorManagerImpl();

	private Mock tawCommonsJobmonitorDao = null;

	private Mock tawCommonsJobsubscibeDao = null;

	private Mock tawCommonsJobsortDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonsJobmonitorDao = new Mock(TawCommonsJobmonitorDao.class);
		tawCommonsJobsubscibeDao = new Mock(TawCommonsJobsubscibeDao.class);
		tawCommonsJobsortDao = new Mock(TawCommonsJobsortDao.class);
		tawCommonsJobmonitorManager
				.setTawCommonsJobmonitorDao((TawCommonsJobmonitorDao) tawCommonsJobmonitorDao
						.proxy());
		tawCommonsJobmonitorManager
				.setTawCommonsJobsortDao((TawCommonsJobsortDao) tawCommonsJobsortDao
						.proxy());
		tawCommonsJobmonitorManager
				.setTawCommonsJobsubscibeDao((TawCommonsJobsubscibeDao) tawCommonsJobsubscibeDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonsJobmonitorManager = null;
	}

	/**
	 * 测试获取全部任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobmonitors() throws Exception {
		List results = new ArrayList();
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		results.add(tawCommonsJobmonitor);

		// set expected behavior on dao
		tawCommonsJobmonitorDao.expects(once()).method(
				"getTawCommonsJobmonitors").will(returnValue(results));

		List tawCommonsJobmonitors = tawCommonsJobmonitorManager
				.getTawCommonsJobmonitors(null);
		assertTrue(tawCommonsJobmonitors.size() == 1);
		tawCommonsJobmonitorDao.verify();
	}

	/**
	 * 测试根据ID获取任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobmonitor() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitorTemp = new TawCommonsJobmonitor();
		tawCommonsJobmonitorTemp.setId("000");
		// set expected behavior on dao
		tawCommonsJobmonitorDao.expects(once()).method(
				"getTawCommonsJobmonitor").with(eq("000")).will(
				returnValue(tawCommonsJobmonitorTemp));
		TawCommonsJobmonitor tawCommonsJobmonitor = tawCommonsJobmonitorManager
				.getTawCommonsJobmonitor("000");
		assertTrue(tawCommonsJobmonitor != null);
		assertEquals("000", tawCommonsJobmonitor.getId());
		tawCommonsJobmonitorDao.verify();
	}

	public void testRun() throws Exception {
		ITawCommonsJobmonitorManager mgr = (ITawCommonsJobmonitorManager) ApplicationContextHolder
				.getInstance().getBean("ItawCommonsJobmonitorManager");
		try {
			// 任务调度器初始化
			mgr.instance();
			// 系统启动时装载任务
			mgr.run();
		} catch (ScheduleRunException e) {
			e.printStackTrace();
		}
	}
}