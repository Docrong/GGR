/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.groupcomplaint.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.impl.MainService;
import com.boco.eoms.sheet.groupcomplaint.dao.IGroupComplaintMainDAO;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintMainManager;

/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupComplaintMainManagerImpl extends MainService implements
        IGroupComplaintMainManager {

    /**
     * 通过告警号获取工单
     *
     * @param alarmId 告警号
     * @return
     * @throws HibernateException
     */
    public BaseMain getMainByAlarmId(String alarmId) {
        IGroupComplaintMainDAO iGroupComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        return iGroupComplaintMainDAO.getMainByAlarmId(alarmId, this.getMainObject());
    }

    public BaseMain loadSinglePO(String id) {
        IGroupComplaintMainDAO iGroupComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        return iGroupComplaintMainDAO.loadSinglePO(id, this.getMainObject());
    }

    public List getMainByLink(Map map, Object linkObject) {
        IGroupComplaintMainDAO iGroupComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        return iGroupComplaintMainDAO.getMainByLink(map, this.getMainObject(), linkObject);
    }

    public List getMainList(Map map) {
        IGroupComplaintMainDAO iGroupComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        return iGroupComplaintMainDAO.getMainList(map, this.getMainObject());
    }

    public List getListUndoForCheck(Integer curPage, Integer pageSize)
            throws SheetException {
        IGroupComplaintMainDAO iComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        List list = null;
        try {
            list = iComplaintMainDAO.getListUndoForCheck(curPage, pageSize, this.getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }


    public Integer getCountUndoForCheck() throws SheetException {
        IGroupComplaintMainDAO iComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iComplaintMainDAO.getCountUndoForCheck(this.getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public List getListDoneForCheck(Integer curPage, Integer pageSize) throws SheetException {
        IGroupComplaintMainDAO iComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        List list = null;
        try {
            list = iComplaintMainDAO.getListDoneForCheck(curPage, pageSize, this.getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return list;
    }


    public Integer getCountDoneForCheck() throws SheetException {
        IGroupComplaintMainDAO iComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        Integer count = new Integer(0);
        try {
            count = iComplaintMainDAO.getCountDoneForCheck(this.getMainObject());
        } catch (Exception e) {
            throw new SheetException(e);
        }
        return count;
    }

    public Map getListQuery(final String sql, final Integer num1, Integer num2) throws HibernateException {
        IGroupComplaintMainDAO iGroupComplaintMainDAO = (IGroupComplaintMainDAO) this.getMainDAO();
        return (Map) iGroupComplaintMainDAO.getListQuery(sql, num1, num2);
    }

}
