package com.boco.eoms.commons.system.dept.test;

import org.jmock.Mock;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.dept.dao.TawPartnersDeptDao;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.service.impl.TawPartnersDeptManagerImpl;
import com.boco.eoms.commons.system.dept.service.impl.TawSystemDeptManagerImpl;

public class TawPartnersDeptManager extends BaseManagerTestCase{
	private TawPartnersDeptManagerImpl mgr = new TawPartnersDeptManagerImpl();
	private Mock tawPartnersDeptDao = null;
	protected void setUp() throws Exception {
		super.setUp();
		tawPartnersDeptDao = new Mock(TawSystemDeptDao.class);
		mgr.setTawPartnersDeptDao((TawPartnersDeptDao) tawPartnersDeptDao.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		mgr = null;
	}
	public void testSaveTawPartnersDept() throws Exception {
		TawPartnersDept tawPartnersDept = new TawPartnersDept();
		tawPartnersDept.setDeptId("2");
		tawPartnersDept.setWorkers("4");
		tawPartnersDept.setLines("4");

		// set expected behavior on dao
		//-----------保存----------
		tawPartnersDeptDao.expects(once()).method("saveTawPartnersDept").with(same(tawPartnersDept)).isVoid();
		mgr.saveTawPartnersDept(tawPartnersDept);
		//-----------修改----------
		tawPartnersDept.setDeptId("10");
		tawPartnersDept.setWorkers("10");
		tawPartnersDept.setLines("10");
		tawPartnersDeptDao.expects(once()).method("saveTawPartnersDept").with(same(tawPartnersDept)).isVoid();
		mgr.saveTawPartnersDept(tawPartnersDept);
		//-----------删除----------
		tawPartnersDeptDao.expects(once()).method("removeTawPartnersDept").with(same(tawPartnersDept)).isVoid();
		mgr.removeTawPartnersDept(tawPartnersDept);
		
		tawPartnersDeptDao.verify();
	}
}
