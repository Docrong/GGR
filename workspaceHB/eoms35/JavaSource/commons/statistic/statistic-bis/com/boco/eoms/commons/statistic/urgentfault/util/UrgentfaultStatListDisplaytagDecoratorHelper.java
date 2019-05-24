package com.boco.eoms.commons.statistic.urgentfault.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.commonfault.vo.CommonfaultDetailVO;
import com.boco.eoms.commons.statistic.urgentfault.vo.UrgentfaultIntimeDetailVO;

public class UrgentfaultStatListDisplaytagDecoratorHelper extends
		TableDecorator {
	
	public String getSenduserid() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
	}
	
	public String getOperateuserid() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateuserid(), "statBaseUserId2name_v35");
	}
	
	public String getSendroleid() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSendroleid(), "statSubRoleId2name_v35");
	}
	
	public String getOperateroleid() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateroleid(), "statSubRoleId2name_v35");
	}
	
	public String getMainnetsortone() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getMainnetsortone(), "statDictId2name_v35");
	}
	public String getTodeptid() {
		UrgentfaultIntimeDetailVO vo = (UrgentfaultIntimeDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getTodeptid(), "statAreaId2name_v35");
	}
	
	
}
