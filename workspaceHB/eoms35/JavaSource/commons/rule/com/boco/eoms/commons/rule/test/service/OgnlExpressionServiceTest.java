package com.boco.eoms.commons.rule.test.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.exception.ExpressionComputeException;
import com.boco.eoms.commons.rule.sample.Rule1InputParameter1Sample;
import com.boco.eoms.commons.rule.service.OgnlExpressionService;

import junit.framework.TestCase;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 22, 2007 2:26:25 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class OgnlExpressionServiceTest extends TestCase {

	private Logger logger = Logger.getLogger(OgnlExpressionServiceTest.class);

	private Object root;

	private OgnlExpressionService service;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetValue() {
		this.testCreate();
		// #fact = :[#this<=1? 1 : #this*#fact(#this-1)], #fact(30H)
		// String expression =
		// "#a=:[#this.name='2',#this.age=3,#this],#a(#param1)";
		String expression = "#a=:[#this.name='2',#this.age=3,#this],#param1.name.equals('qjb')?#a(#param1):#param1";
		Map inputMap = new HashMap();
		inputMap.put("param1", new Rule1InputParameter1Sample("qjb",
				new Integer(10)));
		inputMap.put("param2", new Double(8));

		try {
			Rule1InputParameter1Sample rps = (Rule1InputParameter1Sample) service
					.getValue(expression, inputMap);
			// System.out.println(rps.getName());
			// System.out.println(rps.getAge());
			assertEquals(new Integer(3), rps.getAge());
			assertEquals("2", rps.getName());
			// assertEquals("qjb", service.getValue(expression, inputMap));
		} catch (ExpressionComputeException e) {
			logger.error(e);
			fail();
		}
	}

	public void testGetRoot() {
		this.testCreate();
		assertEquals(root, service.getRoot());
	}

	public void testCreate() {
		this.service = OgnlExpressionService.create(root);
	}
}
