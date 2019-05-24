package com.boco.eoms.commons.mms.commonfault.foot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.base.foot.FootInfo;

public class CommonfaultT4FootInfo implements FootInfo {

	/**
	 * 算法得出来的list，在MMSchedulerV35 中设置list初始化值
	 */
	private List list = null;
	
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Map getInfo() {
		Map map = new HashMap();
		
		map.put("todaytime", "2009-2-18");
		map.put("starttime", "2009-3-19");
		map.put("endtime", "2009-4-20");
		
		return map;
	}

}
