package com.boco.eoms.commons.statistic.businessoperation.vo;

import java.util.Date;

import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
public class BusinessoperationStatDetailVO extends StatDetailVO {
	private String mainproductcode;
	
	private Date mainendtime;
	
	private Date endtime;

	public String getMainproductcode() {
		return mainproductcode;
	}

	public void setMainproductcode(String mainProductCode) {
		this.mainproductcode = mainProductCode;
	}

	public Date getMainendtime() {
		return mainendtime;
	}

	public void setMainendtime(Date mainEndTime) {
		this.mainendtime = mainEndTime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endTime) {
		this.endtime = endTime;
	}
}
