
package com.boco.eoms.sheet.arithmeticmodify.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.arithmeticmodify.dao.IArithmeticModifyMainDAO;

public class ArithmeticModifyMainDAOHibernate extends MainDAO implements IArithmeticModifyMainDAO {

    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }

}

