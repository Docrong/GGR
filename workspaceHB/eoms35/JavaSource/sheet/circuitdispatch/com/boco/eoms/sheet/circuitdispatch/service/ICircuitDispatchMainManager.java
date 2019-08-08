/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IMainService;

/**
 * @author jialei
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICircuitDispatchMainManager extends IMainService {

    /**
     * 取流程的名称
     *
     * @return
     * @throws SheetException
     */
    public abstract TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException;

    public BaseMain loadSinglePO(String id);

    /**
     * 保存网元信息
     * @param sheetId
     * @param cellInfo
     */
//	public void saveCellInfo(String sheetId,String cellInfo) throws Exception;

    /**
     * 保存方案号
     *
     * @param sheetId
     * @param cellInfo
     */
    public void saveDesignId(String sheetId, String designId) throws Exception;
}
