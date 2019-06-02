package com.boco.eoms.taskplan.displaytag;

import org.displaytag.decorator.TableDecorator;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.taskplan.dao.DisplayDao;
import com.boco.eoms.taskplan.model.Taskqueryplan;

public class taskplanDispaly extends TableDecorator{
	
	
	
	public String getStakeholders()  {
		Taskqueryplan thread = (Taskqueryplan) this.getCurrentRowObject();
		String username="";
		try {
			
			DisplayDao dao = (DisplayDao) ApplicationContextHolder
			.getInstance().getBean("displayDao");
			 username=dao.getUserNameByuserid(thread.getStakeholders());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取用户名报错：" + ex);
			}
		
		
		return username;
	}
	
	public String getDeptid()  {
		Taskqueryplan thread = (Taskqueryplan) this.getCurrentRowObject();
		String deptname="";
		try {
			
			DisplayDao dao = (DisplayDao) ApplicationContextHolder
			.getInstance().getBean("displayDao");
			deptname=dao.getDeptNameByuserid(thread.getDeptid());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取用户名报错：" + ex);
			}
		
		
		return deptname;
	}
	
	public String getProject_name()  {
		Taskqueryplan thread = (Taskqueryplan) this.getCurrentRowObject();
		String dictname="";
		try {
			
			DisplayDao dao = (DisplayDao) ApplicationContextHolder
			.getInstance().getBean("displayDao");
			dictname=dao.getDictNameBydictid(thread.getProject_name());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取用户名报错：" + ex);
			}
		
		
		return dictname;
	}
	
	public String getProject_decompose()  {
		Taskqueryplan thread = (Taskqueryplan) this.getCurrentRowObject();
		String dictname="";
		try {
			
			DisplayDao dao = (DisplayDao) ApplicationContextHolder
			.getInstance().getBean("displayDao");
			dictname=dao.getDictNameBydictid(thread.getProject_decompose());
			}
			catch(Exception ex){
				BocoLog.error(this, "读取用户名报错：" + ex);
			}
		
		
		return dictname;
	}
	
	

}
