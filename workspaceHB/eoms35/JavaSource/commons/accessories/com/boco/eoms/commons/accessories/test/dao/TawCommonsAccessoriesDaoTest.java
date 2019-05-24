package com.boco.eoms.commons.accessories.test.dao;

import java.util.Date;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.dao.TawCommonsAccessoriesDao;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:附件管理DAO测试类，主要测试TawCommonsAccessoriesDao类中的各种方法
 * </p>
 * <p>
 * Apr 10, 2007 11:04:31 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsAccessoriesDaoTest extends BaseDaoTestCase {
	private TawCommonsAccessoriesDao dao = null;

	public void setTawCommonsAccessoriesDao(TawCommonsAccessoriesDao dao) {
		this.dao = dao;
	}

	/**
	 * 测试添加附件（附件信息入库）
	 * 
	 * @param dao
	 * @author 秦敏
	 */
	public void testAddTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		// set required fields
		java.lang.String accessoriesCnName = "000000000";
		tawCommonsAccessories.setAccessoriesCnName(accessoriesCnName);

		java.lang.String accessoriesName = "11111111";
		tawCommonsAccessories.setAccessoriesName(accessoriesName);

		java.lang.String accessoriesPath = "222222222";
		tawCommonsAccessories.setAccessoriesPath(accessoriesPath);

		Integer accessoriesSize = new Integer(12345);
		tawCommonsAccessories.setAccessoriesSize(accessoriesSize.intValue());

		java.lang.String accessoriesUploader = "admin";
		tawCommonsAccessories.setAccessoriesUploader(accessoriesUploader);

		java.util.Date accessoriesUploadTime = new Date();
		tawCommonsAccessories.setAccessoriesUploadTime(accessoriesUploadTime);

		java.lang.String appCode = "2222";
		tawCommonsAccessories.setAppCode(appCode);

		dao.saveTawCommonsAccessories(tawCommonsAccessories);

		// verify a primary key was assigned
		assertNotNull(tawCommonsAccessories.getId());

		// verify set fields are same after save
		assertEquals(accessoriesSize.intValue(), tawCommonsAccessories
				.getAccessoriesSize());
		assertEquals(accessoriesCnName, tawCommonsAccessories
				.getAccessoriesCnName());
		assertEquals(accessoriesName, tawCommonsAccessories
				.getAccessoriesName());
		assertEquals(accessoriesPath, tawCommonsAccessories
				.getAccessoriesPath());
		assertEquals(accessoriesUploader, tawCommonsAccessories
				.getAccessoriesUploader());
		assertEquals(accessoriesUploadTime, tawCommonsAccessories
				.getAccessoriesUploadTime());
		assertEquals(appCode, tawCommonsAccessories.getAppCode());
	}

	/**
	 * 测试获取全部附件信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		tawCommonsAccessories.setId("00000");
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		TawCommonsAccessories tawCommonsAccessoriesOther = dao
				.getTawCommonsAccessories(tawCommonsAccessories.getId());
		assertNotNull(tawCommonsAccessoriesOther);
	}

	/**
	 * 测试根据ID获取附件信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetTawCommonsAccessoriess() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		List results = dao.getTawCommonsAccessoriess();
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试附件信息修改
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testSaveTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessoriesTemp = new TawCommonsAccessories();
		dao.saveTawCommonsAccessories(tawCommonsAccessoriesTemp);
		TawCommonsAccessories tawCommonsAccessories = dao
				.getTawCommonsAccessories(tawCommonsAccessoriesTemp.getId());

		// update required fields
		java.lang.String accessoriesCnName = "000000000";
		tawCommonsAccessories.setAccessoriesCnName(accessoriesCnName);

		java.lang.String accessoriesName = "11111111";
		tawCommonsAccessories.setAccessoriesName(accessoriesName);

		java.lang.String accessoriesPath = "222222222";
		tawCommonsAccessories.setAccessoriesPath(accessoriesPath);

		Integer accessoriesSize = new Integer(12345);
		tawCommonsAccessories.setAccessoriesSize(accessoriesSize.intValue());

		java.lang.String accessoriesUploader = "admin";
		tawCommonsAccessories.setAccessoriesUploader(accessoriesUploader);

		java.util.Date accessoriesUploadTime = new Date();
		tawCommonsAccessories.setAccessoriesUploadTime(accessoriesUploadTime);

		java.lang.String appCode = "2222";
		tawCommonsAccessories.setAppCode(appCode);
		dao.saveTawCommonsAccessories(tawCommonsAccessories);

		// verify a primary key was assigned
		assertNotNull(tawCommonsAccessories.getId());

		// verify set fields are same after save
		assertEquals(accessoriesSize.intValue(), tawCommonsAccessories
				.getAccessoriesSize());
		assertEquals(accessoriesCnName, tawCommonsAccessories
				.getAccessoriesCnName());
		assertEquals(accessoriesName, tawCommonsAccessories
				.getAccessoriesName());
		assertEquals(accessoriesPath, tawCommonsAccessories
				.getAccessoriesPath());
		assertEquals(accessoriesUploader, tawCommonsAccessories
				.getAccessoriesUploader());
		assertEquals(accessoriesUploadTime, tawCommonsAccessories
				.getAccessoriesUploadTime());
		assertEquals(appCode, tawCommonsAccessories.getAppCode());

	}

	/**
	 * 删除附件信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testRemoveTawCommonsAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		dao.removeTawCommonsAccessories(tawCommonsAccessories.getId());
		try {
			dao.getTawCommonsAccessories(tawCommonsAccessories.getId());
			fail("tawCommonsAccessories found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	/**
	 * 测试获取最新上传的附件信息
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetLastAccessories() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		tawCommonsAccessories.setAccessoriesName("1111");
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		List results = dao.getAllFileByName("'"
				+ tawCommonsAccessories.getAccessoriesName() + "'");
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试根据附件号获取附件信息（多个ID）
	 * 
	 * @throws Exception
	 * @author 秦敏
	 */
	public void testGetAllFileById() throws Exception {
		TawCommonsAccessories tawCommonsAccessories = new TawCommonsAccessories();
		dao.saveTawCommonsAccessories(tawCommonsAccessories);
		List results = dao.getAllFileByName("'" + tawCommonsAccessories.getId()
				+ "'");
		assertTrue(results.size() > 0);
	}
}
