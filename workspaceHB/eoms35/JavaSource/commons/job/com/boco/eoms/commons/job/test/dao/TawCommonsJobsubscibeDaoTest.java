package com.boco.eoms.commons.job.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.job.model.TawCommonsJobsubscibe;
import com.boco.eoms.commons.job.dao.TawCommonsJobsubscibeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:任务订阅DAO测试类，主要测试TawCommonsJobsubscibeDao类中的各种方法
 * </p>
 * <p>
 * Apr 10, 2007 10:53:39 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsJobsubscibeDaoTest extends BaseDaoTestCase {
	private TawCommonsJobsubscibeDao dao = null;

	public void setTawCommonsJobsubscibeDao(TawCommonsJobsubscibeDao dao) {
		this.dao = dao;
	}

	/**
	 * 测试任务订阅信息的添加
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testAddTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();

		// set required fields

		java.lang.Integer deleted = new Integer(255526545);
		tawCommonsJobsubscibe.setDeleted(deleted);

		java.lang.Integer jobSortId = new Integer(1654964768);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);

		java.lang.String jobCycle = "XkLnEaOpGdXqWhFuCoXbCqXkJpXyRlYtKxLbXaCdFqRxRoXnHd";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);

		java.lang.String jobType = "MuKkSfEgJuEjHfTjAoLwCvEkVrEzXlMyXpFlWiPtYzMcHbBfDl";
		tawCommonsJobsubscibe.setJobType(jobType);

		java.lang.String subId = "YfOrUcMsRoPmVaTgMsQsVuLdWcJvEqNxCeBjBqCeDtQtIhIxFd";
		tawCommonsJobsubscibe.setSubId(subId);

		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);

		// verify a primary key was assigned
		assertNotNull(tawCommonsJobsubscibe.getId());

		// verify set fields are same after save
		assertEquals(deleted, tawCommonsJobsubscibe.getDeleted());
		assertEquals(jobSortId, tawCommonsJobsubscibe.getJobSortId());
		assertEquals(jobCycle, tawCommonsJobsubscibe.getJobCycle());
		assertEquals(jobType, tawCommonsJobsubscibe.getJobType());
		assertEquals(subId, tawCommonsJobsubscibe.getSubId());
	}

	/**
	 * 测试根据ID获取任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		java.lang.String id = "11111111111";
		tawCommonsJobsubscibe.setId(id);
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
		TawCommonsJobsubscibe tawCommonsJobsubscibeOther = dao
				.getTawCommonsJobsubscibe(tawCommonsJobsubscibe.getId());
		assertNotNull(tawCommonsJobsubscibeOther);
	}

	/**
	 * 测试获取全部任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobsubscibes() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		java.lang.Integer jobSortId = new Integer(3);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);

		java.lang.String jobCycle = "XkLnEaOpGdXqWhFuCoXbCqXkJpXyRlYtKxLbXaCdFqRxRoXnHd";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);

		java.lang.String jobType = "MuKkSfEgJuEjHfTjAoLwCvEkVrEzXlMyXpFlWiPtYzMcHbBfDl";
		tawCommonsJobsubscibe.setJobType(jobType);

		java.lang.String subId = "YfOrUcMsRoPmVaTgMsQsVuLdWcJvEqNxCeBjBqCeDtQtIhIxFd";
		tawCommonsJobsubscibe.setSubId(subId);
		tawCommonsJobsubscibe.setDeleted(new Integer(0));
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
		List results = dao.getTawCommonsJobsubscibes();
		assertTrue(results.size() > 0);
	}

	/**
	 * 保存任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testSaveTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibeTemp = new TawCommonsJobsubscibe();
		tawCommonsJobsubscibeTemp.setJobSortId(new Integer(3));
		tawCommonsJobsubscibeTemp.setJobCycle("200");
		tawCommonsJobsubscibeTemp.setJobType("simple");
		tawCommonsJobsubscibeTemp.setSubId("1111");
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibeTemp);
		TawCommonsJobsubscibe tawCommonsJobsubscibe = dao
				.getSubscibeJobById(tawCommonsJobsubscibeTemp.getSubId());
		// update required fields
		java.lang.Integer deleted = new Integer(170618766);
		tawCommonsJobsubscibe.setDeleted(deleted);
		java.lang.Integer jobSortId = new Integer(1908843560);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);
		java.lang.String jobCycle = "KsHfGkVsKxPxHzOvJqKlLwHhHtEmUtPgTcUyYcLsMzEdJyOlAm";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);
		java.lang.String jobType = "VzPcSqXlJqVlMyHxIiTpSbTfWoLqEfKfQpReQfDbTnErBmGpDo";
		tawCommonsJobsubscibe.setJobType(jobType);
		java.lang.String subId = "FfWbUkIaDnDiCdGuRyKlUmLhCqUgMsByFeSzZgEyVjHbVdMnQu";
		tawCommonsJobsubscibe.setSubId(subId);

		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);

		assertEquals(deleted, tawCommonsJobsubscibe.getDeleted());
		assertEquals(jobSortId, tawCommonsJobsubscibe.getJobSortId());
		assertEquals(jobCycle, tawCommonsJobsubscibe.getJobCycle());
		assertEquals(jobType, tawCommonsJobsubscibe.getJobType());
		assertEquals(subId, tawCommonsJobsubscibe.getSubId());
	}

	/**
	 * 测试删除任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testRemoveTawCommonsJobsubscibe() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		java.lang.Integer jobSortId = new Integer(1654964768);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);

		java.lang.String jobCycle = "XkLnEaOpGdXqWhFuCoXbCqXkJpXyRlYtKxLbXaCdFqRxRoXnHd";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);

		java.lang.String jobType = "MuKkSfEgJuEjHfTjAoLwCvEkVrEzXlMyXpFlWiPtYzMcHbBfDl";
		tawCommonsJobsubscibe.setJobType(jobType);

		java.lang.String subId = "YfOrUcMsRoPmVaTgMsQsVuLdWcJvEqNxCeBjBqCeDtQtIhIxFd";
		tawCommonsJobsubscibe.setSubId(subId);

		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
		dao.removeTawCommonsJobsubscibe(tawCommonsJobsubscibe.getId());
		try {
			dao.getTawCommonsJobsubscibe(tawCommonsJobsubscibe.getId());
			fail("tawCommonsJobsubscibe found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	/**
	 * 测试根据订阅号获取任务订阅信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetSubscibeJobById() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		java.lang.String subId = "000000";
		tawCommonsJobsubscibe.setSubId(subId);
		java.lang.Integer jobSortId = new Integer(3);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);

		java.lang.String jobCycle = "XkLnEaOpGdXqWhFuCoXbCqXkJpXyRlYtKxLbXaCdFqRxRoXnHd";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);

		java.lang.String jobType = "MuKkSfEgJuEjHfTjAoLwCvEkVrEzXlMyXpFlWiPtYzMcHbBfDl";
		tawCommonsJobsubscibe.setJobType(jobType);
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
		TawCommonsJobsubscibe results = dao
				.getSubscibeJobById(tawCommonsJobsubscibe.getSubId());
		assertNotNull(results);
	}

	/**
	 * 测试根据订阅号获取与该订阅号雷同的订阅号数目
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetCountNum() throws Exception {
		TawCommonsJobsubscibe tawCommonsJobsubscibe = new TawCommonsJobsubscibe();
		java.lang.String subId = "000000";
		tawCommonsJobsubscibe.setSubId(subId);
		java.lang.Integer jobSortId = new Integer(3);
		tawCommonsJobsubscibe.setJobSortId(jobSortId);

		java.lang.String jobCycle = "XkLnEaOpGdXqWhFuCoXbCqXkJpXyRlYtKxLbXaCdFqRxRoXnHd";
		tawCommonsJobsubscibe.setJobCycle(jobCycle);

		java.lang.String jobType = "MuKkSfEgJuEjHfTjAoLwCvEkVrEzXlMyXpFlWiPtYzMcHbBfDl";
		tawCommonsJobsubscibe.setJobType(jobType);
		dao.saveTawCommonsJobsubscibe(tawCommonsJobsubscibe);
		java.lang.Integer results = new Integer(dao
				.getCountNum(tawCommonsJobsubscibe.getSubId()));
		assertTrue(results.intValue() == 1);
	}
}
