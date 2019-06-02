/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import com.boco.eoms.sheet.base.dao.ITemplateDAO;
import com.boco.eoms.sheet.base.service.ITemplateService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:32:40
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class TemplateServiceImpl implements ITemplateService {
    private ITemplateDAO templateDAO;

    /**
     * @return the templateDAO
     */
    public ITemplateDAO getTemplateDAO() {
        return templateDAO;
    }

    /**
     * @param templateDAO
     *            the templateDAO to set
     */
    public void setTemplateDAO(ITemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }
}
