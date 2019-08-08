package com.boco.eoms.sheet.groupcheck.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.groupcheck.dao.IGroupCheckMainDAO;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:38 GMT+08:00 2017
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class GroupCheckMainDAOHibernate extends MainDAO implements IGroupCheckMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "GroupCheckMain";
    }

}