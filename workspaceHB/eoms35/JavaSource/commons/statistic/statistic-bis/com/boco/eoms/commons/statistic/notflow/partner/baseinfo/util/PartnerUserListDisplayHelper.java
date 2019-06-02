package com.boco.eoms.commons.statistic.notflow.partner.baseinfo.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.notflow.partner.baseinfo.vo.StatDetailVOSPartnerUser;

public class PartnerUserListDisplayHelper extends TableDecorator{
	public String getAreaName(){
		StatDetailVOSPartnerUser vo = (StatDetailVOSPartnerUser) getCurrentRowObject();
		return StatUtil.id2Name(vo.getAreaId(), "statBaseUserId2name_v35");
	}

	public String getDeptName() {
		StatDetailVOSPartnerUser vo = (StatDetailVOSPartnerUser) getCurrentRowObject();
		return StatUtil.id2Name(vo.getDeptId(), "statBaseDeptId2name_v35");

	}

}
