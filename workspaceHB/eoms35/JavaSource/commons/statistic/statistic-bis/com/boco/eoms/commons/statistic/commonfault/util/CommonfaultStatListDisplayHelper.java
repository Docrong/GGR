package com.boco.eoms.commons.statistic.commonfault.util;

import com.boco.eoms.commons.statistic.commonstat.util.CommonStatListDisplayHelper;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.commonfault.vo.CommonfaultDeplyKpi3DetailVO;

public class CommonfaultStatListDisplayHelper extends CommonStatListDisplayHelper {

	public String getMainnetsortone() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getMainnetsortone(), "statDictId2name_v35");
	}

	public String getTodeptid() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getTodeptid(), "statAreaId2name_v35");
	}
  //角色
	public String getSendroleid() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSendroleid(), "statSubRoleId2name_v35");
	}

	public String getOperateroleid() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateroleid(), "statSubRoleId2name_v35");
	}
  //人员
	public String getSenduserid() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
	}

	public String getOperateuserid() {
		CommonfaultDeplyKpi3DetailVO vo = (CommonfaultDeplyKpi3DetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateuserid(), "statBaseUserId2name_v35");
	}
}
