package com.boco.eoms.commons.system.org.test.mgr;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.org.mgr.util.OrgMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleConstants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Oct 22, 2008 8:48:26 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class OrgMgrImplTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSubrolesOfDept() {
		List subroles = OrgMgrLocator.getOrgMgr().getSubrolesOfDept("1564",
				RoleConstants.systemRole, Constants.NOT_DELETED_FLAG);
		assertNotNull(subroles);
		for (Iterator it = subroles.iterator(); it.hasNext();) {
			TawSystemSubRole subrole = (TawSystemSubRole) it.next();
			System.out.println(subrole.getSubRoleName());
		}
	}

	public void testGetSubrolesOfUser() {
		List subroles = OrgMgrLocator.getOrgMgr().getSubrolesOfUser("infopub",
				RoleConstants.systemRole, Constants.NOT_DELETED_FLAG);
		assertNotNull(subroles);
		assertTrue(!subroles.isEmpty());
		assertEquals(subroles.size(), 1);
		TawSystemSubRole subrole = (TawSystemSubRole) subroles.iterator()
				.next();
		assertEquals(subrole.getId(), "8aa081281c31b8f4011c31bf03a90012");
	}

}
