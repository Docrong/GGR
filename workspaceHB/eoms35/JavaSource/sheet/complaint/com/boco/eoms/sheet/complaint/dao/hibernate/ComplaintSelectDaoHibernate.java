/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.complaint.dao.IComplaintSelectDAO;
import com.boco.eoms.sheet.complaint.model.ComplaintMain;
import com.boco.eoms.sheet.complaint.model.ComplaintSel;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComplaintSelectDaoHibernate extends BaseDaoHibernate implements
        IComplaintSelectDAO {


    public String getPhoneBySheetId(final String sheetId)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                ComplaintMain main = new ComplaintMain();
                String customPhone = "";
                String queryStr = "from ComplaintMain where sheetid = '" + sheetId + "'";

                Query query = session.createQuery(queryStr);
                List list = query.list();
                if (list.size() != 0) {
                    main = (ComplaintMain) list.get(0);
                    customPhone = main.getCustomPhone();
                }
                return customPhone;
            }
        };
        return (String) getHibernateTemplate().execute(callback);
    }


    public String getSheetIdsByPhone(final String customPhone)
            throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                ComplaintMain main = new ComplaintMain();
                String sheetids = "";
                String queryStr = "from ComplaintMain where customphone = '" + customPhone + "'";
                Query query = session.createQuery(queryStr);
                List list = query.list();
                if (list.size() > 100) {
                    return "查询工单号过多";
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        main = (ComplaintMain) list.get(i);
                        if ("".equals(sheetids)) {
                            sheetids = main.getSheetId();
                        } else {
                            sheetids = sheetids + "," + main.getSheetId();
                        }
                    }
                    return sheetids;
                }
            }
        };
        return (String) getHibernateTemplate().execute(callback);
    }

    public void saveSelComplaint(ComplaintSel complaintSel) throws HibernateException {
        getHibernateTemplate().save(complaintSel);
    }

    ;
}
