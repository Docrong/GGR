/*
 * Created on 2007-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.util.flowdefine.xml;

import com.boco.eoms.base.test.console.ConsoleTestCase;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FlowDefineSchemaTest extends ConsoleTestCase {


	public void testGetInstance() {
		try {
			FlowDefineSchema flowDefineSchema = FlowDefineSchema.getInstance();
			flowDefineSchema.loadXml();
			System.out.println(flowDefineSchema.loadXml().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}