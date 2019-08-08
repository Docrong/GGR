/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import com.boco.eoms.sheet.base.adapter.service.IHumanTaskAdapterService;
import com.boco.eoms.sheet.base.service.IHumanTaskService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:46:55
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class HumanTaskServiceImpl implements IHumanTaskService {


    private IHumanTaskAdapterService humanTaskAdapterService;


    /**
     * @return the humanTaskAdapterService
     */
    public IHumanTaskAdapterService getHumanTaskAdapterService() {
        return humanTaskAdapterService;
    }

    /**
     * @param humanTaskAdapterService the humanTaskAdapterService to set
     */
    public void setHumanTaskAdapterService(
            IHumanTaskAdapterService humanTaskAdapterService) {
        this.humanTaskAdapterService = humanTaskAdapterService;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.IHumanTaskService#driveProcess()
     */
    public String driveProcess() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
