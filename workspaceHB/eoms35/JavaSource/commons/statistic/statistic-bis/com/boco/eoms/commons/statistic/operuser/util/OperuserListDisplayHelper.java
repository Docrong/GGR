package com.boco.eoms.commons.statistic.operuser.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.notflow.partner.baseinfo.vo.StatDetailVOSPartnerUser;
import com.boco.eoms.commons.statistic.operuser.vo.StatDetailVOSOperuser;

public class OperuserListDisplayHelper extends TableDecorator{
	public String getSubarea(){
		StatDetailVOSOperuser vo = (StatDetailVOSOperuser) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSubarea(), "statDictId2name_v35");
	}

	public String getDeptid() {
		StatDetailVOSOperuser vo = (StatDetailVOSOperuser) getCurrentRowObject();
		return StatUtil.id2Name(vo.getDeptid(), "statBaseDeptId2name_v35");

	}
	
	public String getMajortype() {
		StatDetailVOSOperuser vo = (StatDetailVOSOperuser) getCurrentRowObject();
		return StatUtil.id2Name(vo.getMajortype(), "statDictId2name_v35");

	}

}
