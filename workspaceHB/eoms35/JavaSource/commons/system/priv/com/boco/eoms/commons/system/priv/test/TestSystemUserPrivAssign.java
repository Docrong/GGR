package com.boco.eoms.commons.system.priv.test;

import junit.framework.TestCase;

import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;

public class TestSystemUserPrivAssign extends TestCase {

	public void testGetJSONObjectPriv() {
		TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		assignbo.getJSONObjectPriv("role", "888");
	}
}
