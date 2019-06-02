package com.boco.eoms.commons.statistic.circuitdispatch.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.circuitdispatch.vo.StatDetailVOCircuitdispatch;


public class CircuitdispatchListDisplayHelper extends TableDecorator {
	
public String getSenduser(){
		
	StatDetailVOCircuitdispatch vo = (StatDetailVOCircuitdispatch) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
		
	}
	
	public String getSenddept() {
		StatDetailVOCircuitdispatch vo = (StatDetailVOCircuitdispatch) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenddeptid(), "statBaseDeptId2name_v35");

	}

	public String getStatus() {
		StatDetailVOCircuitdispatch vo = (StatDetailVOCircuitdispatch) getCurrentRowObject();
		return StatUtil.id2Name(vo.getStatus(),"statSubRoleId2name_v35");

	}

}
