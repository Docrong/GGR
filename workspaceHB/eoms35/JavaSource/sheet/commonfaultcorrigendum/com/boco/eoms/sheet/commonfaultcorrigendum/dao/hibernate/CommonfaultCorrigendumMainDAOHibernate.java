package com.boco.eoms.sheet.commonfaultcorrigendum.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.commonfaultcorrigendum.dao.ICommonfaultCorrigendumMainDAO;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CommonfaultCorrigendumMainDAOHibernate extends MainDAO implements ICommonfaultCorrigendumMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "CommonfaultCorrigendumMain";
    }

    public List getNetTeam(final String netname) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "select s.subroleid,t.netType,t.city,t.county,t.saveTime,t.createUserId,t.createDeptId,t.ifAutotran,t.teamRoleId,t.ccObject from commonfault_net_team t,cityid_to_subrole s where t.cityid = s.cityid and t.deleted='0' and netname ='" + netname + "'";
                Query query = session.createSQLQuery(queryStr);
                ArrayList queryList = new ArrayList();
                queryList = (ArrayList) query.list();
                return queryList;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public void updateNetTeam(final String mainnewTeamRoleId, final String mainnewccObject, final String mainCommonfaultNetName) throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                if (!"".equals(mainnewTeamRoleId) && !"".equals(mainnewccObject)) {
                    String queryStr = "update NetOwnership set teamRoleId = '" + mainnewTeamRoleId + "' , ccObject = '" + mainnewccObject + "' where netName = '" + mainCommonfaultNetName + "'";
                    Query query = session.createQuery(queryStr);
                    return new Integer(query.executeUpdate());
                } else if (!"".equals(mainnewTeamRoleId)) {
                    String queryStr = "update NetOwnership set teamRoleId = '" + mainnewTeamRoleId + "' where netName = '" + mainCommonfaultNetName + "'";
                    Query query = session.createQuery(queryStr);
                    return new Integer(query.executeUpdate());
                } else if (!"".equals(mainnewccObject)) {
                    String queryStr = "update NetOwnership set ccObject = '" + mainnewccObject + "' where netName = '" + mainCommonfaultNetName + "'";
                    Query query = session.createQuery(queryStr);
                    return new Integer(query.executeUpdate());
                } else {
                    return new Integer(-1);
                }
            }
        };
        getHibernateTemplate().execute(callback);
    }
}