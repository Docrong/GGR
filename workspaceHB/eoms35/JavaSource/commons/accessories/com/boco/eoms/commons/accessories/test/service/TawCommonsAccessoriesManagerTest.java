package com.boco.eoms.commons.accessories.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.accessories.service.impl.TawCommonsAccessoriesManagerFileuploadImpl;
import com.boco.eoms.commons.accessories.util.AccessoriesMgrLocator;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>Title:
 * </p>
 * <p>Description:附件管理service测试类，主要测试TawCommonsAccessoriesManagerImpl类中的各种方法
 * </p>
 * <p>Apr 10, 2007 11:05:14 AM</p> 
 *
 * @author 秦敏
 * @version 1.0
 *
 */
public class TawCommonsAccessoriesManagerTest extends BaseManagerTestCase {
	private TawCommonsAccessoriesManagerFileuploadImpl tawCommonsAccessoriesManager = new TawCommonsAccessoriesManagerFileuploadImpl();

	private Mock tawCommonsAccessoriesDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonsAccessoriesDao = new Mock(TawCommonsAccessoriesDao.class);
		tawCommonsAccessoriesManager
				.setDao((TawCommonsAccessoriesDao) tawCommonsAccessoriesDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonsAccessoriesManager = null;
	}
	/**
     * 测试获取全部附件信息
     * @throws Exception
     * @author 秦敏
     */
	public void testGetTawCommonsAccessoriess() throws Exception {
		List results = new ArrayList();
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		results.add(tawCommonsAccessories);

		// set expected behavior on dao
		tawCommonsAccessoriesDao.expects(once()).method(
				"getTawCommonsAccessoriess").will(returnValue(results));

		List tawCommonsAccessoriess = tawCommonsAccessoriesManager
				.getTawCommonsAccessoriess();
		assertTrue(tawCommonsAccessoriess.size() == 1);
		tawCommonsAccessoriesDao.verify();
	}
	/**
     * 测试根据ID获取附件信息
     * @throws Exception
     * @author 秦敏
     */
	public void testGetTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessoriesTemp = new TawCommonsAccessories();
		java.lang.String id = "000000";
		tawCommonsAccessoriesTemp.setId(id);
		// set expected behavior on dao
		tawCommonsAccessoriesDao.expects(once()).method(
				"getTawCommonsAccessories").with(eq(id)).will(
				returnValue(tawCommonsAccessoriesTemp));
		TawCommonsAccessories tawCommonsAccessories = tawCommonsAccessoriesManager
				.getTawCommonsAccessories(id);
		assertTrue(tawCommonsAccessories != null);
		assertEquals(id, tawCommonsAccessories.getId());
		tawCommonsAccessoriesDao.verify();
	}
	/**
     * 测试附件信息修改
     * @throws Exception
     * @author 秦敏
     */
	public void testSaveTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		java.lang.String id = "111111";
		tawCommonsAccessories.setId(id);
		tawCommonsAccessories.setAccessoriesName("12345");
		tawCommonsAccessoriesDao.expects(once()).method(
				"getTawCommonsAccessories").with(eq(id)).will(
				returnValue(tawCommonsAccessories));
		tawCommonsAccessories = tawCommonsAccessoriesManager
				.getTawCommonsAccessories(id);
		assertEquals("12345", tawCommonsAccessories.getAccessoriesName());

		// set expected behavior on dao
		tawCommonsAccessoriesDao.expects(once()).method(
				"saveTawCommonsAccessories").with(same(tawCommonsAccessories))
				.isVoid();
		tawCommonsAccessories.setAccessoriesName("67890");
		tawCommonsAccessoriesManager
				.saveTawCommonsAccessories(tawCommonsAccessories);

		tawCommonsAccessoriesDao.expects(once()).method(
				"getTawCommonsAccessories").with(eq(id)).will(
				returnValue(tawCommonsAccessories));
		tawCommonsAccessories = tawCommonsAccessoriesManager
				.getTawCommonsAccessories(id);
		assertEquals("67890", tawCommonsAccessories.getAccessoriesName());
		tawCommonsAccessoriesDao.verify();
	}
	/**
     * 增加与删除附件信息
     * @throws Exception
     * @author 秦敏
     */
	public void testAddAndRemoveTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		java.lang.String id = "111111";
		tawCommonsAccessories.setId(id);
		// set required fields

		// set expected behavior on dao
		tawCommonsAccessoriesDao.expects(once()).method(
				"saveTawCommonsAccessories").with(same(tawCommonsAccessories))
				.isVoid();
		tawCommonsAccessoriesManager
				.saveTawCommonsAccessories(tawCommonsAccessories);
		tawCommonsAccessoriesDao.verify();

		// reset expectations
		tawCommonsAccessoriesDao.reset();

		tawCommonsAccessoriesDao.expects(once()).method(
				"removeTawCommonsAccessories").with(eq(new String(id)));
		tawCommonsAccessoriesManager.removeTawCommonsAccessories(id);
		tawCommonsAccessoriesDao.verify();

		// reset expectations
		tawCommonsAccessoriesDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawCommonsAccessories.class, tawCommonsAccessories.getId());
		tawCommonsAccessoriesDao.expects(once()).method(
				"removeTawCommonsAccessories").isVoid();
		tawCommonsAccessoriesDao.expects(once()).method(
				"getTawCommonsAccessories").will(throwException(ex));
		tawCommonsAccessoriesManager.removeTawCommonsAccessories(id);
		try {
			tawCommonsAccessoriesManager.getTawCommonsAccessories(id);
			fail("TawCommonsAccessories with identifier '" + id
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawCommonsAccessoriesDao.verify();
	}
	/**
	 * 测试根据附件号获取附件信息（多个ID）
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetAllFileById() throws Exception {
		List list = new ArrayList();
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		java.lang.String id = "111111";
		tawCommonsAccessories.setId(id);
		list.add(tawCommonsAccessories);

		tawCommonsAccessoriesDao.expects(once()).method(
				"saveTawCommonsAccessories").with(same(tawCommonsAccessories))
				.isVoid();
		tawCommonsAccessoriesManager
				.saveTawCommonsAccessories(tawCommonsAccessories);

		tawCommonsAccessoriesDao.expects(once()).method("getAllFileById").with(
				eq("'" + id + "'")).will(returnValue(list));
		List results = tawCommonsAccessoriesManager.getAllFileById("'" + id
				+ "'");
		assertNotNull(results);
		assertTrue(results.size() == 1);
	}
	/**
	 * 测试根据附件号获取附件信息（多个ID）
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetFilePathByName() throws Exception {
		List list = new ArrayList();
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		java.lang.String fileName = "test.xls";
		java.lang.String filePath="D:\test";
		tawCommonsAccessories.setAccessoriesPath(filePath);
		tawCommonsAccessories.setAccessoriesName(fileName);
		list.add(tawCommonsAccessories);

		tawCommonsAccessoriesDao.expects(once()).method(
				"saveTawCommonsAccessories").with(same(tawCommonsAccessories))
				.isVoid();
		tawCommonsAccessoriesManager
				.saveTawCommonsAccessories(tawCommonsAccessories);

		tawCommonsAccessoriesDao.expects(once()).method("getAllFileById").with(
				eq("'" + fileName + "'")).will(returnValue(list));
		String[] results = tawCommonsAccessoriesManager.getFilePathByName("'" + fileName
				+ "'");
		assertNotNull(results);		
		assertTrue(results.length == 1);
		assertEquals(results[0],filePath+"\\"+fileName);
	}
	
	public void testGetUrlById() {
		String id="8a1581b716808d5f0116808fb9180002";
		String url = AccessoriesMgrLocator.getTawCommonsAccessoriesManagerCOS().getUrlById(id);
		System.out.println(url);
	}

}
