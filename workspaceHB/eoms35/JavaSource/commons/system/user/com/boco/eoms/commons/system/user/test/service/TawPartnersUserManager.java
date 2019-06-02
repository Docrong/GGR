package com.boco.eoms.commons.system.user.test.service;

import org.jmock.Mock;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.user.dao.TawPartnersUserDao;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.service.impl.TawPartnersUserManagerImpl;
import com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl;

public class TawPartnersUserManager extends BaseManagerTestCase{
	private TawPartnersUserManagerImpl mgr = new TawPartnersUserManagerImpl();
	private Mock tawPartnersUserDao = null;
	protected void setUp() throws Exception {
		super.setUp();
		tawPartnersUserDao = new Mock(TawSystemUserDao.class);
		mgr.setTawPartnersUserDao((TawPartnersUserDao) tawPartnersUserDao.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		mgr = null;
	}
	public void testSaveTawPartnersDept() throws Exception {
		TawPartnersUser tawPartnersUser = new TawPartnersUser();
		tawPartnersUser.setUserid("2");
		tawPartnersUser.setIsMarried("1");
		tawPartnersUser.setJobType("ere");

		// set expected behavior on dao
		//-----------保存----------
		tawPartnersUserDao.expects(once()).method("saveTawPartnersUser").with(same(tawPartnersUser)).isVoid();
		mgr.saveTawPartnersUser(tawPartnersUser);
		//-----------修改----------
		tawPartnersUser.setUserid("fddf");
		tawPartnersUser.setIsMarried("rr");
		tawPartnersUser.setJobType("ererr");
		tawPartnersUserDao.expects(once()).method("saveTawPartnersUser").with(same(tawPartnersUser)).isVoid();
		mgr.saveTawPartnersUser(tawPartnersUser);
		//-----------删除----------
		tawPartnersUserDao.expects(once()).method("removeTawPartnersUser").with(same(tawPartnersUser)).isVoid();
		mgr.removeTawPartnersUser(tawPartnersUser);
		
		tawPartnersUserDao.verify();
	}

}
