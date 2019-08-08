
package com.boco.eoms.sheet.itrequirement.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.itrequirement.dao.IITRequirementMainDAO;
import com.boco.eoms.sheet.itrequirement.model.ITRequirementMain;

public class ITRequirementMainDAOHibernate extends MainDAO implements IITRequirementMainDAO {


    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }


}

