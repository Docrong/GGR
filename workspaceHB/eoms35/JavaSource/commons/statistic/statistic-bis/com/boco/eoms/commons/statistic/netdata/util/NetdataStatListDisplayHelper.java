package com.boco.eoms.commons.statistic.netdata.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.commonstat.vo.StatDetailVO;
import com.boco.eoms.commons.statistic.netdata.vo.NetdataDetailVO;

public class NetdataStatListDisplayHelper extends TableDecorator {


	public String getMainNetSortOne() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getMainNetSortOne(), "statDictId2name_v35");
	}

	public String getTodeptid() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getTodeptid(), "statBaseDeptId2name_v35");
	}
  //角色
	public String getSendroleid() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSendroleid(), "statSubRoleId2name_v35");
	}

	public String getOperateroleid() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateroleid(), "statSubRoleId2name_v35");
	}
  //人员
	public String getSenduserid() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "statBaseUserId2name_v35");
	}

	public String getOperateuserid() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getOperateuserid(), "statBaseUserId2name_v35");
	}
	
	//执行部门
	public String getSenddept() {
		NetdataDetailVO vo = (NetdataDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenddeptid(), "statBaseDeptId2name_v35");
	}
}
