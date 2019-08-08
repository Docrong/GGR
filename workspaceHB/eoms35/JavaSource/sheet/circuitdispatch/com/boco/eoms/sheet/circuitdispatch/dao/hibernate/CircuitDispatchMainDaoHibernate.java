/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.circuitdispatch.dao.ICircuitDispatchMainDAO;
import com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchMain;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CircuitDispatchMainDaoHibernate extends MainDAO implements
        ICircuitDispatchMainDAO {


    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }


    public TawSystemWorkflow getTawSystemWorkflowByFlowTemplateName(String flowTemplateName) throws SheetException {
        String sql = "from TawSystemWorkflow as t where t.name ='" + flowTemplateName + "'";
        List list = getHibernateTemplate().find(sql);
        return list.size() > 0 ? (TawSystemWorkflow) list.iterator().next() : null;
    }

    public void DeleteEarlyEmptyMain(Object mainObject) {
        long DAY = 24L * 60L * 60L * 1000L;
        try {
            String sql = "from " + mainObject.getClass().getName() + " as main where main.title is null";
            List sheetList = getHibernateTemplate().find(sql);
            if (sheetList != null) {
                int i = sheetList.size();
                while (i > 0) {
                    i--;
                    BaseMain main = (BaseMain) sheetList.get(i);
                    System.out.println("new Date: " + (new Date()).getTime() / DAY);
                    System.out.println("main Date:" + main.getSendTime().getTime() / DAY);
                    if (((new Date()).getTime() / DAY - main.getSendTime().getTime() / DAY) > 0) {
                        System.out.println("delete early main");
                        getHibernateTemplate().delete(main);
                        getHibernateTemplate().flush();
                        getHibernateTemplate().clear();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
