package com.boco.eoms.commons.statistic.base.test;

import com.boco.eoms.commons.statistic.base.mgr.IStatID2Name;
import com.boco.eoms.commons.statistic.base.reference.ConsoleTestCase;

public class V35Id2nameImplTest extends ConsoleTestCase{
	private IStatID2Name role;
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	
	public void testRole(){
		try {
			role = (IStatID2Name) getBean("statRoleId2name_v35");
			assertEquals(role.id2Name("184"), "电路调度数据制作员");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
