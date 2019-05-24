package com.boco.eoms.commons.statistic.circuitdispatch.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;


public class StatDetailVOCircuitdispatch extends StatDetailVO{
	
	private String overtimeDept="及时";
	private String deptlevel;
	private String sendroleid;
	private String endtime;
	private Date mainexecuteenddate;
	
	
	
	

	

	/**
	 * @return the deptlevel
	 */
	public String getDeptlevel() {
		return deptlevel;
	}

	/**
	 * @param deptlevel the deptlevel to set
	 */
	public void setDeptlevel(String deptlevel) {
		this.deptlevel = deptlevel;
	}

	/**
	 * @return the overtimeDept
	 */
	public String getOvertimeDept() {
		return overtimeDept;
	}

	
	public void setOvertimeDept(String overtimeDept) {
		this.overtimeDept = overtimeDept;
	}
	
	public String getSendroleid() {
		return sendroleid;
	}

	
	public void setSendroleid(String sendroleid) {
		this.sendroleid =sendroleid;
	}
	
	public String getEndtime() {
		return endtime;
	}

	
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	


	public Date getMainexecuteenddate() {
		return mainexecuteenddate;
	}

	public void setMainexecuteenddate(Date mainexecuteenddate) {
		this.mainexecuteenddate = mainexecuteenddate;
	}


	
	
	



}
