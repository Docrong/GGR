package com.boco.eoms.km.train.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.train.dao.TrainSpecialtyDao;
import com.boco.eoms.km.train.model.TrainSpecialty;
import com.boco.eoms.km.train.util.TrainSpecialtyConstants;


/**
 * <p>
 * Title:专业分类 dao的hibernate实现
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class TrainSpecialtyDaoHibernate extends BaseDaoHibernate implements TrainSpecialtyDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.train.TrainSpecialtyDao#getTrainSpecialty(java.lang.String)
     */
    public TrainSpecialty getTrainSpecialty(final String id) {
        TrainSpecialty trainSpecialty = (TrainSpecialty) getHibernateTemplate().get(TrainSpecialty.class, id);
        if (trainSpecialty == null) {
            trainSpecialty = new TrainSpecialty();
        }
        return trainSpecialty;
    }

    /**
     * @see com.boco.eoms.km.train.TrainSpecialtyDao#saveTrainSpecialtys(com.boco.eoms.km.train.TrainSpecialty)
     */
    public void saveTrainSpecialty(final TrainSpecialty trainSpecialty) {
        if ((trainSpecialty.getId() == null) || (trainSpecialty.getId().equals("")))
            getHibernateTemplate().save(trainSpecialty);
        else
            getHibernateTemplate().saveOrUpdate(trainSpecialty);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        TrainSpecialty trainSpecialty = this.getTrainSpecialtyByNodeId(id);
        if (trainSpecialty == null) {
            return "";
        }
        //TODO 请修改代码
        return trainSpecialty.getSpecialtyName();
    }

    public TrainSpecialty getTrainSpecialtyByNodeId(final String nodeId) {
        TrainSpecialty trainSpecialty = new TrainSpecialty();
        String hql = " from TrainSpecialty trainSpecialty where trainSpecialty.nodeId='" + nodeId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list.iterator().hasNext()) {
            trainSpecialty = (TrainSpecialty) list.iterator().next();
        }
        return trainSpecialty;
    }

    public void removeTrainSpecialtyByNodeId(final String nodeId) {
        final String hql = "delete from TrainSpecialty trainSpecialty where trainSpecialty.nodeId like '"
                + nodeId + "%'";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                query.executeUpdate();
                return null;
            }
        };
        getHibernateTemplate().execute(callback);
    }

    public List getNextLevelTrainSpecialtys(final String parentNodeId) {
        String hql = " from TrainSpecialty trainSpecialty where trainSpecialty.parentNodeId='"
                + parentNodeId + "'";
        hql += " order by trainSpecialty.nodeId";
        return getHibernateTemplate().find(hql);
    }

    public String getUsableNodeId(final String parentNodeId, final int length) {
        String minNodeId = getUsableMinNodeId(parentNodeId, length);
        if (null == minNodeId || "".equals(minNodeId)) {
            minNodeId = TrainSpecialtyConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int len) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " select distinct(nodeId) from TrainSpecialty trainSpecialty where trainSpecialty.nodeId like '"
                        + parentNodeId + "%' and length(trainSpecialty.nodeId)='" + len + "'";
                Query query = session.createQuery(hql);
                return query.list();
            }
        };
        List list = (List) getHibernateTemplate().execute(callback);
        HashMap hm = new HashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            String nodeId = it.next().toString();
            hm.put(nodeId, nodeId);
        }
        // 防止下面的while出现死循环,如果所有Id全部被占用则返回一个空值
        if (hm.size() >= Integer.parseInt(TrainSpecialtyConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + TrainSpecialtyConstants.NODEID_NOSON)
                .longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    public List getChildNodes(final String parentNodeId) {
        String hql = " from TrainSpecialty trainSpecialty where trainSpecialty.parentNodeId like '"
                + parentNodeId + "%'";
        hql += " order by trainSpecialty.nodeId";
        return (List) getHibernateTemplate().find(hql);
    }

}