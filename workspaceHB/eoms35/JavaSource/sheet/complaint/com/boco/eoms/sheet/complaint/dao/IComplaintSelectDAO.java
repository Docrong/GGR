/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.complaint.model.ComplaintSel;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IComplaintSelectDAO extends Dao {


    public String getPhoneBySheetId(String sheetId) throws HibernateException;

    public String getSheetIdsByPhone(String customPhone) throws HibernateException;

    public void saveSelComplaint(ComplaintSel complaintSel) throws HibernateException;
}
