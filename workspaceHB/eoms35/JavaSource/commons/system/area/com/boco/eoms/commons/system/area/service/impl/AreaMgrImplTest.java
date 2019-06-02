package com.boco.eoms.commons.system.area.service.impl;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.model.util.AreaConstants;
import com.boco.eoms.commons.system.area.service.IAreaMgr;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 19, 2008 10:19:16 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public class AreaMgrImplTest extends ConsoleTestCase {
	/**
	 * 区域API
	 */
	private IAreaMgr areaMgr;

	/**
	 * 区域mgr
	 */
	private ITawSystemAreaManager tawSystemAreaManager;

	/**
	 * 部门mgr
	 */
	private ITawSystemDeptManager tawSystemDeptManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
	 */
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		areaMgr = (IAreaMgr) applicationContext.getBean("AreaMgrImpl");
		tawSystemAreaManager = (ITawSystemAreaManager) applicationContext
				.getBean("ItawSystemAreaManager");
		this.tawSystemDeptManager = (ITawSystemDeptManager) getBean("ItawSystemDeptSaveManagerFlush");
	}

	public void testGetArea() {
		TawSystemArea area = new TawSystemArea();
		area.setAreacode("111111");
		area.setAreaid("111111");
		area.setAreaname("Peking");
		area.setCapital(AreaConstants.AREA_TYPE_CAPITAL);
		area.setOldAreaName("fasd");
		area.setParentAreaid("-1");
		tawSystemAreaManager.saveTawSystemArea(area);

		TawSystemArea assertArea = areaMgr.getArea(area.getAreaid());
		assertNotNull(assertArea);
		assertEquals(assertArea.getAreaid(), area.getAreaid());

	}

	public void testGetAreaOfDept() {
		TawSystemArea area = new TawSystemArea();
		area.setAreacode("111111");
		area.setAreaid("111111");
		area.setAreaname("Peking");
		area.setCapital(AreaConstants.AREA_TYPE_CAPITAL);
		area.setOldAreaName("fasd");
		area.setParentAreaid("-1");
		tawSystemAreaManager.saveTawSystemArea(area);
		
		TawSystemDept dept = new TawSystemDept();
		dept.setDeleted(Constants.NOT_DELETED_FLAG);
		dept.setDeptemail("aa@boco.com.cn");
		dept.setDeptfax("123");
		dept.setDeptId("deptid");
		dept.setDeptmanager("admin");
		dept.setDeptmobile("13810005432");
		dept.setDeptName("admin");
		dept.setDeptphone("88157289");
		dept.setDeptType("1");
		dept.setLeaf("0");
		dept.setAreaid(area.getAreaid());
		tawSystemDeptManager.saveTawSystemDept(dept);
		
		
		TawSystemArea assertArea=areaMgr.getAreaOfDept(dept.getDeptId());
		assertNotNull(assertArea);
		assertEquals(assertArea.getAreaid(), dept.getAreaid());
	
	}

	public void testGetAreaOfUser() {
		fail("Not yet implemented");
	}

	public void testGetAreaType() {
		fail("Not yet implemented");
	}

	public void testGetParentArea() {
		fail("Not yet implemented");
	}

	public void testListAllChildArea() {
		fail("Not yet implemented");
	}

	public void testListChildArea() {
		fail("Not yet implemented");
	}

	public void testListParentAreas() {
		fail("Not yet implemented");
	}

}
