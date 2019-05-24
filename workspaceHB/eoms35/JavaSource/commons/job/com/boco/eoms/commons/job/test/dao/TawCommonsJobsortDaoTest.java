package com.boco.eoms.commons.job.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.job.model.TawCommonsJobsort;
import com.boco.eoms.commons.job.dao.TawCommonsJobsortDao;

import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>Title: 
 * </p>
 * <p>Description: 任务类型DAO测试类，主要测试TawCommonsJobsortDao类的各种方法
 * </p>
 * <p>Apr 10, 2007 10:51:47 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsJobsortDaoTest extends BaseDaoTestCase {
	private TawCommonsJobsortDao dao = null;

	public void setTawCommonsJobsortDao(TawCommonsJobsortDao dao) {
		this.dao = dao;
	}
	
    /**
     * 测试任务类型的增加
     * @throws Exception
     * @author 秦敏
     */
	public void testAddTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();

		// set required fields
		java.lang.Integer deleted = new Integer(854658881);
		tawCommonsJobsort.setDeleted(deleted);

		java.lang.String jobClassName = "SzZtNwVpTeIcDmCzUuWxLvBkGtPaCfXdAaFeVqVbArXvJdEtDk";
		tawCommonsJobsort.setJobClassName(jobClassName);

		java.lang.String jobSortName = "UeDdNdYfQmSyReVfDqFrAhQaVjLkVuYoFnIaYvJgShDpQuUsUm";
		tawCommonsJobsort.setJobSortName(jobSortName);

		dao.saveTawCommonsJobsort(tawCommonsJobsort);

		// verify a primary key was assigned
		assertNotNull(tawCommonsJobsort.getId());

		// verify set fields are same after save
		assertEquals(deleted, tawCommonsJobsort.getDeleted());
		assertEquals(jobClassName, tawCommonsJobsort.getJobClassName());
		assertEquals(jobSortName, tawCommonsJobsort.getJobSortName());
	}
   /**
    * 测试根据ID任务类型的获取
    * @throws Exception
    * @author 秦敏
    */
	public void testGetTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		tawCommonsJobsort.setId(new Integer(12345));
		dao.saveTawCommonsJobsort(tawCommonsJobsort);
		TawCommonsJobsort tawCommonsJobsortOther = dao
				.getTawCommonsJobsort(tawCommonsJobsort.getId());
		assertNotNull(tawCommonsJobsortOther);
	}
	/**
	 * 测试任务类型的获取
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsJobsorts() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		tawCommonsJobsort.setId(new Integer(12345));
		tawCommonsJobsort.setJobClassName("1111");
		tawCommonsJobsort.setJobSortName("222");
		dao.saveTawCommonsJobsort(tawCommonsJobsort);
		List results = dao.getTawCommonsJobsorts(tawCommonsJobsort);
		assertTrue(results.size() > 0);
	}
	/**
	 * 测试任务类型的保存
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testSaveTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsortTremp = new TawCommonsJobsort();
		tawCommonsJobsortTremp.setId(new Integer(12345));
		tawCommonsJobsortTremp.setJobClassName("1111");
		tawCommonsJobsortTremp.setJobSortName("222");
		dao.saveTawCommonsJobsort(tawCommonsJobsortTremp);
		TawCommonsJobsort tawCommonsJobsort = dao
				.getTawCommonsJobsort(tawCommonsJobsortTremp.getId());
		// update required fields
		java.lang.Integer deleted = new Integer(623769209);
		tawCommonsJobsort.setDeleted(deleted);
		java.lang.String jobClassName = "SiVcViCzFnXjEsKrQzKyUjJlAsWxQcXlNyCuUcZjTrKfUiIjJz";
		tawCommonsJobsort.setJobClassName(jobClassName);
		java.lang.String jobSortName = "WoLeOoCyUaXzOzVyYeAeSsUaSwFlFrOdHoCiLwAsAgHsTrNyFf";
		tawCommonsJobsort.setJobSortName(jobSortName);

		dao.saveTawCommonsJobsort(tawCommonsJobsort);

		assertEquals(deleted, tawCommonsJobsort.getDeleted());
		assertEquals(jobClassName, tawCommonsJobsort.getJobClassName());
		assertEquals(jobSortName, tawCommonsJobsort.getJobSortName());
	}
    /**
     * 测试任务类型的删除
     * @throws Exception
     * @author 秦敏
     */
  public void testRemoveTawCommonsJobsort() throws Exception {
		TawCommonsJobsort tawCommonsJobsort = new TawCommonsJobsort();
		tawCommonsJobsort.setId(new Integer(12345));
		tawCommonsJobsort.setJobClassName("1111");
		tawCommonsJobsort.setJobSortName("222");
		dao.saveTawCommonsJobsort(tawCommonsJobsort);
		dao.removeTawCommonsJobsort(tawCommonsJobsort.getId());
		try {
			dao.getTawCommonsJobsort(tawCommonsJobsort.getId());
			fail("tawCommonsJobsort found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
