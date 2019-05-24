package com.boco.eoms.sheet.base.sms;

import junit.framework.TestCase;

public class WorkSheetSmsServicesTest extends TestCase {

	public WorkSheetSmsServicesTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testworkSM_T() throws Exception {
		WorkSheetSmsServices service=new WorkSheetSmsServices();
		String workflowName="BigActivityProcess";
		String sheetKey="111111";
		String sheetId="SC-01-00000-00000";
		String title="测试短信派发";
		int  receiveType=3;
		String receiverId="8a1581b7168e67bc01168e6efce00044";
		String acceptLimitTime="2008-08-05 20:00:00";
		String dealLimitTime="2008-08-08 20:00:00";
		service.workSM_T(null,workflowName, sheetKey, sheetId, title, receiveType,
                 receiverId, acceptLimitTime, dealLimitTime);
	}
	
//	public void testWorkSM_NON_T() throws Exception {
//		WorkSheetSmsServices service=new WorkSheetSmsServices();
//		String workflowName="BigActivityProcess";
//		String sheetKey="111111";
//		String sheetId="SC-01-00000-00000";
//		String title="测试短信派发";
//		int  receiveType=3;
//		String receiverId="8a1581b7168e67bc01168e6efce00044";
//		service.workSM_NON_T(workflowName, sheetKey, sheetId, title, receiveType,
//                             receiverId);
//	}
//	
//	public void testCloseMsgInstantSend() throws Exception {
//		WorkSheetSmsServices service=new WorkSheetSmsServices();
//		String workflowName="BigActivityProcess";
//		String sheetKey="111111";
//		service.closeMsgInstantSend(sheetKey, workflowName);
//	}
//	
//	public void testCloseMsgPreOvertime() throws Exception {
//		WorkSheetSmsServices service=new WorkSheetSmsServices();
//		String workflowName="BigActivityProcess";
//		String sheetKey="111111";
//		service.closeMsgPreOvertime(sheetKey, workflowName);
//	}
//	
//	public void testCloseMsgPostOvertime() throws Exception {
//		WorkSheetSmsServices service=new WorkSheetSmsServices();
//		String workflowName="BigActivityProcess";
//		String sheetKey="111111";
//		service.closeMsgPostOvertime(sheetKey, workflowName);
//	}
	
}
