package com.boco.eoms.workplan.test;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.workplan.mgr.ITawwpExecuteMgr;
import com.boco.eoms.workplan.util.Inspection;

/**
 * @author GONGYUFENG
 *
 */
public class TawwpExecuteTest  extends ConsoleTestCase{
	public void testSaveExecuteContent() {
		
	Inspection workplanresult = new Inspection();
	workplanresult.setCmdID("ls");
	workplanresult.setCmdName("ls---");
	workplanresult.setExecuteFTP("ftp://10.0.2.115/111/EOMS_EOS_提供区域人员练习使用_2009_05_21.rar");
	workplanresult.setExecuteResult("hello");
	workplanresult.setExecuteStatus("1");
	ITawwpExecuteMgr mgrr = (ITawwpExecuteMgr)this.getBean("tawwpExecuteMgr");
	mgrr.saveContentByNet("8aa081eb21edd60b0121eddbdcc80007",workplanresult);
	}
}
