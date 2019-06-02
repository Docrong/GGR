/*
 * Created on 2008-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.task.util;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
import com.boco.eoms.commons.statistic.task.vo.StatDetailVOTask;

// import com.boco.eoms.sheet.base.model.BaseCollect;
// import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author Administrator
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TaskStatListDisplaytagDecoratorHelper extends TableDecorator {
/*	public String getTitle() {

		StatDetailVOTask vo = (StatDetailVOTask) getCurrentRowObject();
		// String url = (String) this.getPageContext().getAttribute("url");

		return "<a onclick=window.open('" + "http://localhost:8080/"
				+ "tasksheet.pr.prShowQueryInfo.do?detail/isDetail=1&sheetid="
				+ vo.getSheetid() + "'); href='#' target='_blank'>" + vo.getTitle() + "</a>";

		// http://localhost:8080/tasksheet.pr.prShowQueryInfo.do?sheetid=null-null-080721-004-00007&detail/isDetail=1
	}
*/
	/*
	public String getSheetid(){
		StatDetailVOTask vo = (StatDetailVOTask) getCurrentRowObject();
		// String url = (String) this.getPageContext().getAttribute("url");
		String url = UtilMgrLocator.getEOMSAttributes().getLoginEOSOutUrl(); 

		return "<a href='"+"http://localhost:8080/"
				+ "tasksheet.pr.prShowQueryInfo.do?detail/isDetail=1&sheetid="
				+ vo.getSheetid() + "'  target='_blank'>" + vo.getSheetid() + "</a>";
		// http://localhost:8080/tasksheet.pr.prShowQueryInfo.do?sheetid=null-null-080721-004-00007&detail/isDetail=1
		
	}*/

	public String getSenduser() {
		StatDetailVOTask vo = (StatDetailVOTask) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenduserid(), "tawSystemUserDao");
	}

	public String getSenddept() {
		StatDetailVOTask vo = (StatDetailVOTask) getCurrentRowObject();
		return StatUtil.id2Name(vo.getSenddeptid(), "tawSystemDeptDao");

	}

	public String getStatus() {
		StatDetailVOTask vo = (StatDetailVOTask) getCurrentRowObject();
		return StatUtil.getStatusName(vo.getStatus());

	}
}
