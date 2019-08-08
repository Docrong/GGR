package com.boco.eoms.sheet.industrysms.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.industrysms.dao.IIndustrySmsMainDAO;

/**
 * <p>
 * Title:行业短信开通删除工单
 * </p>
 * <p>
 * Description:行业短信开通删除工单
 * </p>
 * <p>
 * Mon Mar 04 17:27:01 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class IndustrySmsMainDAOHibernate extends MainDAO implements IIndustrySmsMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "IndustrySmsMain";
    }

}