package com.boco.eoms.commons.job.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.job.model.TawCommonsJobmonitor;
import com.boco.eoms.commons.job.dao.TawCommonsJobmonitorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 任务执行DAO测试类，主要测试TawCommonsJobmonitorDao类中的各种方法
 * </p>
 * <p>
 * Apr 10, 2007 10:51:18 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsJobmonitorDaoTest extends BaseDaoTestCase {
	private TawCommonsJobmonitorDao dao = null;

	public void setTawCommonsJobmonitorDao(TawCommonsJobmonitorDao dao) {
		this.dao = dao;
	}

	/**
	 * 测试任务执行情况的增加
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testAddTawCommonsJobmonitor() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		// set required fields
		java.lang.Integer jobSortId = new Integer(88888);
		tawCommonsJobmonitor.setJobSortId(jobSortId);

		java.lang.String jobSubId = "000000000000";
		tawCommonsJobmonitor.setJobSubId(jobSubId);

		java.lang.String executeEndTime = "11111111111";
		tawCommonsJobmonitor.setExecuteEndTime(executeEndTime);

		java.lang.String executeStartTime = "2222222222222";
		tawCommonsJobmonitor.setExecuteStartTime(executeStartTime);

		java.lang.Integer maxExecuteTime = new Integer(123456);
		tawCommonsJobmonitor.setMaxExecuteTime(maxExecuteTime);

		java.lang.Integer status = new Integer(88888);
		tawCommonsJobmonitor.setStatus(status);

		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		// verify a primary key was assigned
		assertNotNull(tawCommonsJobmonitor.getId());
		// verify set fields are same after save
		assertEquals(jobSortId, tawCommonsJobmonitor.getJobSortId());
		assertEquals(jobSubId, tawCommonsJobmonitor.getJobSubId());
		assertEquals(executeEndTime, tawCommonsJobmonitor.getExecuteEndTime());
		assertEquals(executeStartTime, tawCommonsJobmonitor
				.getExecuteStartTime());
		assertEquals(maxExecuteTime, tawCommonsJobmonitor.getMaxExecuteTime());
		assertEquals(status, tawCommonsJobmonitor.getStatus());
	}

	/**
	 * 测试根据ID获取任务执行信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobmonitor() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		TawCommonsJobmonitor tawCommonsJobmonitorOther = dao
				.getTawCommonsJobmonitor(tawCommonsJobmonitor.getId());
		assertNotNull(tawCommonsJobmonitorOther);
	}

	/**
	 * 测试任务信息获取
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobmonitors() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		tawCommonsJobmonitor.setStatus(new Integer(1));
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		List results = dao.getTawCommonsJobmonitors(tawCommonsJobmonitor);
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试任务执行信息保存
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testSaveTawCommonsJobmonitor() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitorTemp = new TawCommonsJobmonitor();
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitorTemp);
		TawCommonsJobmonitor tawCommonsJobmonitor = dao
				.getTawCommonsJobmonitor(tawCommonsJobmonitorTemp.getId());
		// update required fields
		java.lang.Integer jobSortId = new Integer(88888);
		tawCommonsJobmonitor.setJobSortId(jobSortId);

		java.lang.String jobSubId = "000000000000";
		tawCommonsJobmonitor.setJobSubId(jobSubId);

		java.lang.String executeEndTime = "11111111111";
		tawCommonsJobmonitor.setExecuteEndTime(executeEndTime);

		java.lang.String executeStartTime = "2222222222222";
		tawCommonsJobmonitor.setExecuteStartTime(executeStartTime);

		java.lang.Integer maxExecuteTime = new Integer(123456);
		tawCommonsJobmonitor.setMaxExecuteTime(maxExecuteTime);

		java.lang.Integer status = new Integer(88888);
		tawCommonsJobmonitor.setStatus(status);

		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);

		assertEquals(jobSortId, tawCommonsJobmonitor.getJobSortId());
		assertEquals(jobSubId, tawCommonsJobmonitor.getJobSubId());
		assertEquals(executeEndTime, tawCommonsJobmonitor.getExecuteEndTime());
		assertEquals(executeStartTime, tawCommonsJobmonitor
				.getExecuteStartTime());
		assertEquals(maxExecuteTime, tawCommonsJobmonitor.getMaxExecuteTime());
		assertEquals(status, tawCommonsJobmonitor.getStatus());
	}

	/**
	 * 测试删除任务执行信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testRemoveTawCommonsJobmonitor() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		dao.removeTawCommonsJobmonitor(tawCommonsJobmonitor.getId());
		try {
			dao.getTawCommonsJobmonitor(tawCommonsJobmonitor.getId());
			fail("tawCommonsJobmonitor found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	/**
	 * 测试根据ID查询任务执行信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetJobMonitorBySubId() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		tawCommonsJobmonitor.setStatus(new Integer(1));
		tawCommonsJobmonitor.setJobSubId("001");
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		TawCommonsJobmonitor tawCommonsJobmonitorOther = dao
				.getJobMonitorBySubId("001");
		assertNotNull(tawCommonsJobmonitorOther);
		assertTrue(tawCommonsJobmonitorOther.getJobSubId().equals(
				tawCommonsJobmonitor.getJobSubId()));
	}

	/**
	 * 测试获取正在执行的任务信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetRunningJob() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		tawCommonsJobmonitor.setStatus(new Integer(1));
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		List results = dao.getRunningJob();
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试任务执行信息的删除
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testRemoveRunningJob() throws Exception {
		TawCommonsJobmonitor tawCommonsJobmonitor = new TawCommonsJobmonitor();
		tawCommonsJobmonitor.setStatus(new Integer(1));
		dao.saveTawCommonsJobmonitor(tawCommonsJobmonitor);
		dao.removeRunningJob();
		try {
			dao.getTawCommonsJobmonitor(tawCommonsJobmonitor.getId());
			fail("tawCommonsJobmonitor found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
