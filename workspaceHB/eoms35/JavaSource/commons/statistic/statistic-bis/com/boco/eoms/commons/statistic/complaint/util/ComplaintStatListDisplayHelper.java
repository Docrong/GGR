package com.boco.eoms.commons.statistic.complaint.util;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.commonstat.util.CommonStatListDisplayHelper;
import com.boco.eoms.commons.statistic.complaint.vo.ComplaintResolveKpi3DetailVO;
import com.boco.eoms.commons.statistic.complaint.vo.ComplaintStatDetailVO;

public class ComplaintStatListDisplayHelper extends
		CommonStatListDisplayHelper {
	
	public String getComplainttype1() {
		ComplaintResolveKpi3DetailVO vo = (ComplaintResolveKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getComplainttype1(), "statDictId2name_v35");
	}

	public String getTodeptid() {
		ComplaintResolveKpi3DetailVO vo = (ComplaintResolveKpi3DetailVO) getCurrentRowObject();
		System.err.println(vo.getTodeptid());
		return StatUtil.id2Name(vo.getTodeptid(), "statAreaId2name_v35");
	}
  //��ɫ
	public String getSendroleid() {
		ComplaintResolveKpi3DetailVO vo = (ComplaintResolveKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSendroleid(), "statSubRoleId2name_v35");
	}

//	public String getOperateroleid() {
//		ComplaintResolveKpi3DetailVO vo = (ComplaintResolveKpi3DetailVO) getCurrentRowObject();
//		return StatUtil.id2Name(vo.getOperateroleid(), "statSubRoleId2name_v35");
//	}
  //��Ա
	public String getSenduserid() {
		ComplaintResolveKpi3DetailVO vo = (ComplaintResolveKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
	}

//	public String getOperateuserid() {
//		ComplaintStatDetailVO vo = (ComplaintStatDetailVO) getCurrentRowObject();
//		return StatUtil.id2Name(vo.getOperateuserid(), "statBaseUserId2name_v35");
//	}
}
