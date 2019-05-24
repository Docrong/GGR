package com.boco.eoms.commons.statistic.netchange.vo;
import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
public class StatDetailVONetchange extends StatDetailVO {
	
	
	private String overtimeDept="及时";
	private String deptlevel;
	private String sendroleid;
	
	
	

	

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


	

	

	

	

}
