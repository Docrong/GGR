/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.commonfault.util;

import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.commonfault.vo.CommonfaultDetailVO;

// import com.boco.eoms.sheet.base.model.BaseCollect;
// import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TaskStatListDisplaytagDecoratorHelperOnetimepass extends TaskStatListDisplaytagDecoratorHelper {

	public String getMainnetsortone() {
		CommonfaultDetailVO vo = (CommonfaultDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getMainnetsortone(), "statDictId2name_v35");
	}

	public String getSendroleid() {
		CommonfaultDetailVO vo = (CommonfaultDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSendroleid(), "statSubRoleId2name_v35");
	}
	public String getTodeptid() {
		CommonfaultDetailVO vo = (CommonfaultDetailVO) getCurrentRowObject();
		return StatUtil.id2Name(vo.getTodeptid(), "statAreaId2name_v35");
	}
}
