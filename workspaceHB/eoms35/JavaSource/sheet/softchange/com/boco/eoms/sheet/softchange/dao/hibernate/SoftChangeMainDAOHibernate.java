
package com.boco.eoms.sheet.softchange.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.softchange.dao.ISoftChangeMainDAO;
import com.boco.eoms.sheet.softchange.model.SoftChangeMain;

public class SoftChangeMainDAOHibernate extends MainDAO implements ISoftChangeMainDAO {


    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
//	public String getMainName() {
//		// TODO Auto-generated method stub
//		return "SoftChangeMain";
//	}

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

