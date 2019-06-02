package com.boco.eoms.commons.statistic.netchange.util;



import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.netchange.vo.StatDetailVONetchange;


public class NetchangeListDisplayHelper extends TableDecorator {
	
	public String getSenduser(){
		
		StatDetailVONetchange vo = (StatDetailVONetchange) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
		
	}
	
	public String getSenddept() {
		StatDetailVONetchange vo = (StatDetailVONetchange) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenddeptid(), "statBaseDeptId2name_v35");

	}

	public String getStatus() {
		StatDetailVONetchange vo = (StatDetailVONetchange) getCurrentRowObject();
		return StatUtil.id2Name(vo.getStatus(),"statSubRoleId2name_v35");

	}

}
