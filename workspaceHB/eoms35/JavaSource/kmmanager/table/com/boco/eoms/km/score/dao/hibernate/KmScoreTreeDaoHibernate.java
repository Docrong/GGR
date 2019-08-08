package com.boco.eoms.km.score.dao.hibernate;

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
import com.boco.eoms.km.score.dao.KmScoreTreeDao;
import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.util.KmScoreTreeConstants;


/**
 * <p>
 * Title:积分设定树 dao的hibernate实现
 * </p>
 * <p>
 * Description:积分设定树
 * </p>
 * <p>
 * Mon Aug 17 14:42:36 CST 2009
 * </p>
 *
 * @author me
 * @version 1.0
 */
public class KmScoreTreeDaoHibernate extends BaseDaoHibernate implements KmScoreTreeDao,
        ID2NameDAO {

    /**
     * @see com.boco.eoms.km.score.KmScoreTreeDao#getKmScoreTree(java.lang.String)
     */
    public KmScoreTree getKmScoreTree(final String id) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String queryStr = "from KmScoreTree kmScoreTree where kmScoreTree.id=:id";
                Query query = session.createQuery(queryStr);
                query.setString("id", id);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List result = query.list();
                if (result != null && !result.isEmpty()) {
                    return (KmScoreTree) result.iterator().next();
                } else {
                    return new KmScoreTree();
                }
            }
        };
        return (KmScoreTree) getHibernateTemplate().execute(callback);
    }

    /**
     * @see com.boco.eoms.km.score.KmScoreTreeDao#saveKmScoreTrees(com.boco.eoms.km.score.KmScoreTree)
     */
    public void saveKmScoreTree(final KmScoreTree kmScoreTree) {
        if ((kmScoreTree.getId() == null) || (kmScoreTree.getId().equals("")))
            getHibernateTemplate().save(kmScoreTree);
        else
            getHibernateTemplate().saveOrUpdate(kmScoreTree);
    }

    /**
     * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
     */
    public String id2Name(String id) throws DictDAOException {
        KmScoreTree kmScoreTree = this.getKmScoreTreeByNodeId(id);
        if (kmScoreTree == null) {
            return "";
        }
        //TODO 请修改代码
        return "";
    }

    public KmScoreTree getKmScoreTreeByNodeId(final String nodeId) {
        KmScoreTree kmScoreTree = new KmScoreTree();
        String hql = " from KmScoreTree kmScoreTree where kmScoreTree.nodeId='" + nodeId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list.iterator().hasNext()) {
            kmScoreTree = (KmScoreTree) list.iterator().next();
        }
        return kmScoreTree;
    }

    public void removeKmScoreTreeByNodeId(final String nodeId) {
        final String hql = "delete from KmScoreTree kmScoreTree where kmScoreTree.nodeId like '"
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

    public List getNextLevelKmScoreTrees(final String parentNodeId) {
        String hql = " from KmScoreTree kmScoreTree where kmScoreTree.parentNodeId='"
                + parentNodeId + "'";
        hql += " order by kmScoreTree.nodeId";
        return getHibernateTemplate().find(hql);
    }

    public String getUsableNodeId(final String parentNodeId, final int length) {
        String minNodeId = getUsableMinNodeId(parentNodeId, length);
        if (null == minNodeId || "".equals(minNodeId)) {
            minNodeId = KmScoreTreeConstants.NODEID_DEFAULT_VALUE;
        }
        return minNodeId;
    }

    private String getUsableMinNodeId(final String parentNodeId, final int len) {
        String minUsableNodeId = "";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String hql = " select distinct(nodeId) from KmScoreTree kmScoreTree where kmScoreTree.nodeId like '"
                        + parentNodeId + "%' and length(kmScoreTree.nodeId)='" + len + "'";
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
        if (hm.size() >= Integer.parseInt(KmScoreTreeConstants.NODEID_IF_MAXID)) {
            return minUsableNodeId;
        }
        long nodeIdVar = Long.valueOf(parentNodeId + KmScoreTreeConstants.NODEID_NOSON)
                .longValue();
        while (null != hm.get(String.valueOf(nodeIdVar))) {
            nodeIdVar = nodeIdVar + 1;
        }
        return String.valueOf(nodeIdVar);
    }

    public List getChildNodes(final String parentNodeId) {
        String hql = " from KmScoreTree kmScoreTree where kmScoreTree.parentNodeId like '"
                + parentNodeId + "%'";
        hql += " order by kmScoreTree.nodeId";
        return (List) getHibernateTemplate().find(hql);
    }

}