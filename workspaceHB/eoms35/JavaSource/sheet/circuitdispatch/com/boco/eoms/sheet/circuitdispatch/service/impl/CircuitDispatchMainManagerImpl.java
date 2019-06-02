/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.circuitdispatch.dao.ICircuitDispatchMainDAO;
import com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchMain;
import com.boco.eoms.sheet.circuitdispatch.service.ICircuitDispatchMainManager;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CircuitDispatchMainManagerImpl extends MainService implements
        ICircuitDispatchMainManager {


	public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
		ICircuitDispatchMainDAO iCircuitDispatchMainDAO = (ICircuitDispatchMainDAO)this.getMainDAO();
		return iCircuitDispatchMainDAO.getTawSystemWorkflowByFlowTemplateName(flowTemplateName);
	}

	public BaseMain loadSinglePO(String id) {
		ICircuitDispatchMainDAO iCircuitDispatchMainDAO = (ICircuitDispatchMainDAO)this.getMainDAO();
		return iCircuitDispatchMainDAO.loadSinglePO(id, this.getMainObject());
    }
	
//	/**
//	 * 保存网元信息
//	 * @param sheetId
//	 * @param cellInfo
//	 */
//	public void saveCellInfo(String sheetId,String cellInfo) throws Exception{
//		List list = this.getMainListByParentSheetId(sheetId);
//		if(list.size()>0){
//			CircuitDispatchMain main = (CircuitDispatchMain)list.get(0);
//			
////			main.setMainNetAffectArea(cellInfo);
//			this.saveOrUpdateMain(main);
//		}else{
//			throw new Exception("没找到sheetId="+sheetId+"对应的工单");
//		}
//	}
	/**
	 * 保存方案号
	 * @param sheetId
	 * @param cellInfo
	 */
	public void saveDesignId(String sheetId,String designId) throws Exception{

		CircuitDispatchMain main = (CircuitDispatchMain)this.getMainBySheetId(sheetId);
		if(main!=null||main.getId()!=null){
			main.setMainResourceNo(designId);
			this.saveOrUpdateMain(main);
		}else{
			throw new Exception("没找到sheetId="+sheetId+"对应的工单");
		}
	}

}
