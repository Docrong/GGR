package com.boco.eoms.sheet.commonchangedeploy.dao.hibernate;


import java.util.List;

import org.hibernate.HibernateException;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.base.model.BaseLink;

import com.boco.eoms.sheet.commonchangedeploy.dao.ICommonChangeDeployLinkDAO;
import com.boco.eoms.sheet.commonchangedeploy.model.CommonChangeDeployLink;

/**
 * <p>
 * Title:通用变更配置工单
 * </p>
 * <p>
 * Description:通用变更配置工单
 * </p>
 * <p>
 * Fri Jun 12 09:08:01 CST 2009
 * </p>
 *
 * @author yangwei
 * @version 3.5
 */

public class CommonChangeDeployLinkDAOHibernate extends LinkDAO implements ICommonChangeDeployLinkDAO {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadSinglePO(java.lang.String)
     */
    public BaseLink loadSinglePO(String id) throws HibernateException {
        CommonChangeDeployLink link = (CommonChangeDeployLink) getHibernateTemplate().get(CommonChangeDeployLink.class, id);
        return link;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#listAllLinkOfWorkSheet(java.lang.String)
     */
    public List listAllLinkOfWorkSheet(String id) throws HibernateException {
        String sql = " from CommonChangeDeployLink  as link "
                + " where link.mainId ='" + id + "' order by link.operateTime";
        List list = getHibernateTemplate().find(sql);
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.ILinkDAO#loadLinkOfStep(java.lang.String,
     *      java.lang.String)
     */
    public List loadLinkOfStep(String processId, String stepId) throws HibernateException {
        String sql = " from CommonChangeDeployLink  as link "
                + " where link.piid ='" + processId + "' and link.activeTempleteId = '" + stepId + "'";
        List list = getHibernateTemplate().find(sql);
        return list;
    }

}
 



