package com.boco.eoms.commons.statistic.complaint.util;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.complaint.vo.ComplaintStatDetailVO;

public class ComplaintStatListDisplayHelperIntimeKpi1 extends
	ComplaintStatListDisplayHelper {
	private String senddept;
	public String getComplainttype() {
		ComplaintStatDetailVO vo = (ComplaintStatDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getComplainttype(), "statDictId2name_v35");
	}

	public String getTodeptid() {
		ComplaintStatDetailVO vo = (ComplaintStatDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getTodeptid(), "statBaseDeptId2name_v35");

	}
	public String getSenduserid() {
		ComplaintStatDetailVO vo = (ComplaintStatDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");

	}

}
