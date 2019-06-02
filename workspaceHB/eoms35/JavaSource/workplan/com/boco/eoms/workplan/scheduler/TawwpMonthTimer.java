package com.boco.eoms.workplan.scheduler;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.workplan.mgr.ITawwpLogMgr;
import com.boco.eoms.workplan.mgr.ITawwpMonthMgr;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;



public class TawwpMonthTimer implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List list = null;
		String userId="";
		Calendar c =  Calendar.getInstance() ; 
		 String year  =  ""+(c.get(Calendar.YEAR));
		 String month =  ""+(c.get(Calendar.MONTH)+1);
		// VO对象
		 //TawwpMonthPlan tawwpMonthPlan = null;
		ITawwpMonthMgr tawwpMonthMgr = (ITawwpMonthMgr) ApplicationContextHolder
		.getInstance().getBean("tawwpMonthMgr");
		// 日志
		ITawwpLogMgr tawwpLogMgr = (ITawwpLogMgr) ApplicationContextHolder
		.getInstance().getBean("tawwpLogMgr");

		try {
			tawwpMonthMgr.passMonthCheckTime(month, year);			
			tawwpMonthMgr.passMonthCheckTime();
			/*// 获取月度作业计划集合
			list = tawwpMonthMgr.listMonthPlan(year, month);			
			list=tawwpMonthMgr.listMonthCheck();
			for (int i = 0; i < list.size(); i++) {
				tawwpMonthPlan = (TawwpMonthPlan) list.get(i);
				if(("0".equals(tawwpMonthPlan.getTawwpYearPlan().getIsApp()))){
					tawwpMonthPlan.setConstituteState("1");		
				userId=tawwpMonthPlanVO.getUserName();
				monthCheckIdStr=tawwpMonthPlanVO.getMonthCheckId();
				// 通过月度作业计划审批
				tawwpMonthMgr.passMonthCheck(monthCheckIdStr, "", userId);*/
				/*tawwpMonthMgr.editMonthPlanSave(tawwpMonthPlan.getId(), tawwpMonthPlan.getExecuteType(),tawwpMonthPlan.getPrincipal());
				*/
				tawwpLogMgr.addLog(userId, "passMonthCheck", "");
				
			
		} catch (Exception e) {
		}
	}

}
